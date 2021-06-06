package com.example.medchat.ui

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.medchat.R
import com.example.medchat.adapters.PatientListAdapter
import com.example.medchat.hideKeyboard
import com.example.medchat.viewModel.SharedViewModel


class NewMessageFragment : Fragment() {

    private lateinit var sharedViewmodel : SharedViewModel
    private var allPatientListSize : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_new_message, container, false)

        val searchview = v.findViewById<SearchView>(R.id.patient_searchview)
//        searchview.onActionViewExpanded()  // to expand searchview prior

        val id = searchview.context.resources.getIdentifier("android:id/search_src_text",null,null)
        val tv = searchview.findViewById<TextView>(id)
        tv.setHintTextColor(Color.GRAY)
        tv.hint = "Search Patients"

        sharedViewmodel = activity?.run {
            ViewModelProvider(this)[SharedViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        sharedViewmodel.activeSearchQuery.value = ""

        val mAdapter = PatientListAdapter{
            sharedViewmodel.loadChatHistory(it.patientId)
            hideKeyboard()
            findNavController().navigate(R.id.action_newMessage_to_chatFragment)
        }

        val recyclerView = v.findViewById<RecyclerView>(R.id.recycler_list_Patients_newMsgScreen)
        recyclerView.adapter = mAdapter

        val cardViewEmptyInf = v.findViewById<CardView>(R.id.cardview_new_message_frg_empty_patients_information)
        val textViewInformation = v.findViewById<TextView>(R.id.txt_new_message_frg_inf)

        sharedViewmodel.allPatientList.observe(viewLifecycleOwner){

            cardViewEmptyInf.visibility = if(it.isEmpty()) {
                textViewInformation.text = getString(R.string.no_patients_added)
                View.VISIBLE
            }
            else View.GONE

            allPatientListSize = it.size
        }


        sharedViewmodel.filteredPatientList.observe(viewLifecycleOwner){
            if(allPatientListSize != 0 && it.isEmpty()){
                textViewInformation.text = getString(R.string.no_search_record_found)
                cardViewEmptyInf.visibility = View.VISIBLE
            }
            else if(it.isNotEmpty()) cardViewEmptyInf.visibility = View.GONE
            mAdapter.patientList = it
            mAdapter.notifyDataSetChanged()
        }

        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                sharedViewmodel.activeSearchQuery.value = newText
                return false
            }

        })

        return v
    }

    // one method to loose focus from one thing(also means getting soft keyboard down)
    //            val mlayout = v.findViewById<ConstraintLayout>(R.id.root_layout_newmessagefragment)
//            mlayout.requestFocus()


}