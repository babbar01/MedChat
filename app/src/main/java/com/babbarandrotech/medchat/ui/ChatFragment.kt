package com.babbarandrotech.medchat.ui

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.babbarandrotech.medchat.R
import com.babbarandrotech.medchat.adapters.ChatListAdapter
import com.babbarandrotech.medchat.room.Message
import com.babbarandrotech.medchat.scrollToBottomWithoutFocusChange
import com.babbarandrotech.medchat.viewModel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlin.math.abs


class ChatFragment : Fragment() {

    companion object {
        const val TAG = "chatFragment"
    }

    var viewModel: SharedViewModel? = null
    var navController: NavController? = null
    var scrollView: ScrollView? = null
    var recyclerView: RecyclerView? = null
    var chatListSize: Int = 0
    private lateinit var cardViewInformation: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_chat, container, false)
        cardViewInformation = v.findViewById(R.id.cardview_chat_frg_information)

        val backBtn = v.findViewById<ImageView>(R.id.back_btn_chatViewFragment)
        backBtn.setOnClickListener {
            findNavController().navigateUp()
        }

        scrollView = v.findViewById(R.id.root_scrollview_chatFragment)

//        chatboxLayout = v.findViewById<ConstraintLayout>(R.id.layout_chatbox)

        setupKeyboardListener(scrollView!!)

        viewModel = activity?.run {
            ViewModelProvider(this)[SharedViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
        navController = findNavController()

        recyclerView = v.findViewById(R.id.reyclerview_chat_history)
        val chatListAdapter = ChatListAdapter()
        viewModel?.activeChatHistory?.observe(viewLifecycleOwner) {
            chatListSize = it.size
            cardViewInformation.visibility = if (chatListSize <= 3) View.VISIBLE else View.GONE
            chatListAdapter.list = it
            chatListAdapter.notifyDataSetChanged()
        }

        viewModel?.activeChatPatientName?.observe(viewLifecycleOwner) {
            txt_active_chat_patient_name.text = it
        }

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.stackFromEnd = true
        recyclerView?.apply {
            adapter = chatListAdapter
            layoutManager = linearLayoutManager
        }

        val etTypeMessage = v.findViewById<EditText>(R.id.edittext_chatbox)
        val btnSend = v.findViewById<Button>(R.id.button_chatbox_send)

        btnSend.setOnClickListener {
            viewModel?.activeIntChatPatientId?.let {

                if (etTypeMessage.text.isEmpty()) {
                    Toast.makeText(
                        context,
                        "Please type anything and then press Save!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {

                    val messageBody = etTypeMessage.text.trim().toString()
                    etTypeMessage.text.clear()

                    val newMessage = Message(it, messageBody, System.currentTimeMillis())
                    viewModel?.insertMessage(newMessage)
                }
            }
        }

        val chatScreenTopPanel = v.findViewById<ConstraintLayout>(R.id.chat_screen_top_panel)
        chatScreenTopPanel.setOnClickListener {
            navController?.navigate(R.id.action_chatFragment_to_patientDetailFragment)
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