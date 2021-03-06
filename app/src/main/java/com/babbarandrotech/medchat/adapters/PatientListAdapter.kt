package com.babbarandrotech.medchat.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.babbarandrotech.medchat.R
import com.babbarandrotech.medchat.room.PatientItem

class PatientListAdapter(private val onItemClickListener: (PatientItem) -> Unit) :
    RecyclerView.Adapter<PatientListAdapter.MyViewHolder>(){

    var patientList: List<PatientItem> = emptyList()
//    var contactListFiltered : MutableList<PatientItem> = mutableListOf()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val patientName: TextView = itemView.findViewById(R.id.list_patient_name_newMsgScreen)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.patient_list_item, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = patientList[position]
        holder.patientName.text = item.patientName

        holder.itemView.setOnClickListener {
            onItemClickListener(item)
        }

    }

    override fun getItemCount(): Int {
        return patientList.size
    }

//    override fun getFilter(): Filter1 {
//        return object : Filter1() {
//            override fun performFiltering(charSequence: CharSequence?): FilterResults {
//
//                val charString = charSequence.toString()
////                var patientListFiltered : MutableList<PatientItem>
////
////                if (charString.isNullOrEmpty()) patientListFiltered = patientList as MutableList<PatientItem>
////                else{
////                    patientListFiltered = mutableListOf()
////
////                    for (patientrow in patientList){
////                        if (patientrow.patientName.toLowerCase().contains(charString.toLowerCase())
////                            || patientrow.patientId.toString().contains(charString))
////                                patientListFiltered.add(patientrow)
////                    }
////                }
//
//                Log.d(ChatFragment.TAG, "performFiltering: ")
//
//
//                val filterResults = FilterResults()
//                return filterResults
//            }
//
//            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults?) {
//                Log.d(ChatFragment.TAG, "publishResults: called in filtering")
//                contactListFiltered = filterResults?.values as MutableList<PatientItem>
//                notifyDataSetChanged()
//            }
//
//        }
//    }


}