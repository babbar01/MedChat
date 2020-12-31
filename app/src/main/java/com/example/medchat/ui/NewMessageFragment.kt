package com.example.medchat.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.medchat.R
import com.example.medchat.adapters.PatientListAdapter
import com.example.medchat.viewModel.SharedMessageListAndPatientListViewModel


class NewMessageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_new_message, container, false)

        val mAdapter = PatientListAdapter()
        val recyclerView = v.findViewById<RecyclerView>(R.id.recycler_list_Patients_newMsgScreen)
        recyclerView.adapter = mAdapter

        val sharedViewmodel = ViewModelProvider(this).get(SharedMessageListAndPatientListViewModel::class.java)

        sharedViewmodel.allPatientList.observe(viewLifecycleOwner){
            mAdapter.list = it
            mAdapter.notifyDataSetChanged()
        }

        return v
    }


}