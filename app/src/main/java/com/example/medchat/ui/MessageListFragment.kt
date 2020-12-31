package com.example.medchat.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medchat.R
import com.example.medchat.adapters.MessageListAdapter
import com.example.medchat.viewModel.SharedMessageListAndPatientListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MessageListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_message_list, container, false)

        val viewModel = ViewModelProvider(this).get(SharedMessageListAndPatientListViewModel::class.java)

        val adapter = MessageListAdapter()
        viewModel.allLastMessagesList.observe(viewLifecycleOwner,{
            adapter.list = it
            adapter.notifyDataSetChanged()
        })

        val linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        val recyclerView = v.findViewById<RecyclerView>(R.id.recycler_list_lstMsg)
        recyclerView.apply {
            this.adapter = adapter
            layoutManager = linearLayoutManager
        }

        val btnAddNewPatient = v.findViewById<ImageView>(R.id.add_new_patient_button_home_screen)
        btnAddNewPatient.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addNewPatient)
        }

        val fabnewMessage = v.findViewById<FloatingActionButton>(R.id.fab_new_msg)
        fabnewMessage.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_newMessage)
        }



        return v
    }


}