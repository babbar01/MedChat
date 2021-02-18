package com.example.medchat.ui

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.medchat.R
import com.example.medchat.adapters.PatientListAdapter
import com.example.medchat.room.PatientItem
import com.example.medchat.viewModel.SharedViewModel


class NewMessageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_new_message, container, false)

        val searchview = v.findViewById<SearchView>(R.id.patient_searchview)
//        searchview.onActionViewExpanded()
        val id = searchview.context.resources.getIdentifier("android:id/search_src_text",null,null)
        val tv = searchview.findViewById<TextView>(id)

        tv.setHintTextColor(Color.GRAY)
        tv.hint = "Search Patients"

//        val mlayout = v.findViewById<ConstraintLayout>(R.id.root_layout_newmessagefragment)
//        mlayout.requestFocus()

        val sharedViewmodel = activity?.run {
            ViewModelProvider(this)[SharedViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        val mAdapter = PatientListAdapter{
            sharedViewmodel.loadChatHistory(it.patientId)
//            Log.d(ChatFragment.TAG, "onCreateView of NewMessageFragment: ${sharedViewmodel.activeChatPatientId == null}")
            findNavController().navigate(R.id.action_newMessage_to_chatFragment)
        }

        val recyclerView = v.findViewById<RecyclerView>(R.id.recycler_list_Patients_newMsgScreen)
        recyclerView.adapter = mAdapter

        sharedViewmodel.allPatientList.observe(viewLifecycleOwner){
            mAdapter.patientList = it
            mAdapter.contactListFiltered = it as MutableList<PatientItem>
            mAdapter.notifyDataSetChanged()
        }

        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mAdapter.filter.filter(newText)
                return false
            }

        })

        return v
    }


}