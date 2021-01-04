package com.example.medchat.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medchat.R
import com.example.medchat.adapters.MessageListAdapter
import com.example.medchat.viewModel.SharedViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MessageListFragment : Fragment() {

    private var viewModel : SharedViewModel?  = null
    private var navController : NavController? = null
    private var messageListAdapter : MessageListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_message_list, container, false)
        navController = findNavController()

        viewModel = activity?.run {
            ViewModelProvider(this)[SharedViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        messageListAdapter = MessageListAdapter{
            viewModel?.loadChatHistory(it.recieverId)
            navController?.navigate(R.id.action_listFragment_to_chatFragment)

        }

        observeAndSetAdapterForChangesInList()  // this is for first time list setting also

        val linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        val recyclerView = v.findViewById<RecyclerView>(R.id.recycler_list_lstMsg)
        recyclerView.apply {
            this.adapter = messageListAdapter
            layoutManager = linearLayoutManager
        }

        val btnAddNewPatient = v.findViewById<ImageView>(R.id.add_new_patient_button_home_screen)
        btnAddNewPatient.setOnClickListener{
            navController?.navigate(R.id.action_listFragment_to_addNewPatient)
        }

        val fabnewMessage = v.findViewById<FloatingActionButton>(R.id.fab_new_msg)
        fabnewMessage.setOnClickListener{
            navController?.navigate(R.id.action_listFragment_to_newMessage)
        }

        return v
    }

    private fun observeAndSetAdapterForChangesInList() {

        viewModel?.allLastMessagesList?.observe(viewLifecycleOwner, {

            Log.d(ChatFragment.TAG, "observeAndSetAdapterForChangesInList: ${it.size}")
            messageListAdapter?.list = it
            messageListAdapter?.notifyDataSetChanged()
        })

    }


}