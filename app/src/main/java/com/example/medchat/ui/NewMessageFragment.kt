package com.example.medchat.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.medchat.R
import com.example.medchat.adapters.PatientListAdapter
import com.example.medchat.viewModel.SharedViewModel


class NewMessageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_new_message, container, false)

        val sharedViewmodel = activity?.run {
            ViewModelProvider(this)[SharedViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        val mAdapter = PatientListAdapter{
            sharedViewmodel.loadChatHistory(it.patientId)
            Log.d(ChatFragment.TAG, "onCreateView of NewMessageFragment: ${sharedViewmodel.activeChatPatientId == null}")
            findNavController().navigate(R.id.action_newMessage_to_chatFragment)
        }

        val recyclerView = v.findViewById<RecyclerView>(R.id.recycler_list_Patients_newMsgScreen)
        recyclerView.adapter = mAdapter

        sharedViewmodel.allPatientList.observe(viewLifecycleOwner){
            mAdapter.list = it
            mAdapter.notifyDataSetChanged()
        }

        return v
    }


}