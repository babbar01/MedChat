package com.example.medchat.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.medchat.R
import com.example.medchat.adapters.ChatListAdapter
import com.example.medchat.room.Message
import com.example.medchat.viewModel.SharedViewModel


class ChatFragment : Fragment() {

    companion object {
        const val TAG = "chatFragment"
    }

    var viewModel : SharedViewModel? = null
    var navController : NavController? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_chat, container, false)
        viewModel = activity?.run {
            ViewModelProvider(this)[SharedViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
        navController = findNavController()

        val chatListAdapter = ChatListAdapter()
        viewModel?.activeChatHistory?.observe(viewLifecycleOwner){
            chatListAdapter.list = it
            chatListAdapter.notifyDataSetChanged()
        }

        val recyclerView : RecyclerView = v.findViewById(R.id.reyclerview_chat_history)
        recyclerView.adapter = chatListAdapter

        val etTypeMessage = v.findViewById<EditText>(R.id.edittext_chatbox)
        val btnSend = v.findViewById<Button>(R.id.button_chatbox_send)

        btnSend.setOnClickListener{
            Log.d(TAG, "onCreateView: hello ${viewModel?.activeChatPatientId == null}")
            viewModel?.activeChatPatientId?.let {

                Log.d(TAG, "onCreateView: ${viewModel?.activeChatPatientId}")

                val messageBody = etTypeMessage.text.trim().toString()

                val newMessage = Message(it,messageBody,System.currentTimeMillis())
                viewModel?.insertMessage(newMessage)
            }
        }

        return v
    }


}