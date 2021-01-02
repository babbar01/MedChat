package com.example.medchat.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medchat.R
import com.example.medchat.room.Patient
import com.example.medchat.room.PatientItem

class PatientListAdapter(private val onItemClickListener: (PatientItem) -> Unit) :
    RecyclerView.Adapter<PatientListAdapter.MyViewHolder>() {

    var list: List<PatientItem> = emptyList()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val patientName: TextView = itemView.findViewById(R.id.list_patient_name_newMsgScreen)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.patient_list_item, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = list[position]
        holder.patientName.text = item.patientName

        holder.itemView.setOnClickListener {
            onItemClickListener(item)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}