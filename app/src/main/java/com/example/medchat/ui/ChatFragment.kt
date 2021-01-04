package com.example.medchat.ui

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medchat.R
import com.example.medchat.adapters.ChatListAdapter
import com.example.medchat.room.Message
import com.example.medchat.scrollToBottomWithoutFocusChange
import com.example.medchat.viewModel.SharedViewModel
import kotlin.math.abs


class ChatFragment : Fragment() {

    companion object {
        const val TAG = "chatFragment"
    }

    var viewModel : SharedViewModel? = null
    var navController : NavController? = null
    var scrollView : ScrollView? = null
    var recyclerView : RecyclerView? = null
    var chatListSize : Int = 0
//    var chatboxLayout : ConstraintLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_chat, container, false)
        scrollView = v.findViewById(R.id.root_scrollview_chatFragment)

//        chatboxLayout = v.findViewById<ConstraintLayout>(R.id.layout_chatbox)

        setupKeyboardListener(scrollView!!)

        viewModel = activity?.run {
            ViewModelProvider(this)[SharedViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
        navController = findNavController()

        recyclerView = v.findViewById(R.id.reyclerview_chat_history)
        val chatListAdapter = ChatListAdapter()
        viewModel?.activeChatHistory?.observe(viewLifecycleOwner){
            chatListSize = it.size
            chatListAdapter.list = it
            chatListAdapter.notifyDataSetChanged()
        }

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.stackFromEnd = true
        recyclerView?.apply{
            adapter = chatListAdapter
            layoutManager = linearLayoutManager
        }

        val etTypeMessage = v.findViewById<EditText>(R.id.edittext_chatbox)
        val btnSend = v.findViewById<Button>(R.id.button_chatbox_send)

        btnSend.setOnClickListener{
            Log.d(TAG, "onCreateView: hello ${viewModel?.activeChatPatientId == null}")
            viewModel?.activeChatPatientId?.let {

                Log.d(TAG, "onCreateView: ${viewModel?.activeChatPatientId}")

                val messageBody = etTypeMessage.text.trim().toString()
                etTypeMessage.text.clear()

                val newMessage = Message(it,messageBody,System.currentTimeMillis())
                viewModel?.insertMessage(newMessage)
            }
        }

        return v
    }


    private fun setupKeyboardListener(view: ScrollView) {
        view.viewTreeObserver.addOnGlobalLayoutListener {

            val r = Rect()
            view.getWindowVisibleDisplayFrame(r)
            val heightDiff = abs(view.rootView.height - (r.bottom - r.top))
            if (heightDiff > 200) { // if more than 200 pixels, its probably a keyboard...
                onKeyboardShow()
            }
        }
    }


    private fun onKeyboardShow() {
        Log.d(TAG, "onKeyboardShow: called")
        scrollView?.scrollToBottomWithoutFocusChange()
    }


}