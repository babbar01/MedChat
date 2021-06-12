package com.babbarandrotech.medchat.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.babbarandrotech.medchat.R
import com.babbarandrotech.medchat.room.VaccineRecord
import kotlinx.android.synthetic.main.item_vaccine_history_recyclerview.view.*
import java.text.SimpleDateFormat
import java.util.*

class VaccineHistoryAdapter :
    RecyclerView.Adapter<VaccineHistoryAdapter.VaccineHistoryViewHolder>() {

    var vaccineList : List<VaccineRecord> = emptyList()

    inner class VaccineHistoryViewHolder(itemView :View) : RecyclerView.ViewHolder(itemView){

        val tvVaccine = itemView.tv_vaccine
        val tvNotesVaccine = itemView.tv_notes_vaccine
        val dateTime = itemView.tv_vaccine_date_time

        fun bind(item: VaccineRecord) {

            tvVaccine.text = item.txtVaccine
            tvNotesVaccine.text = item.notes
            dateTime.text = SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.US).format(Date(item.timestamp))

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VaccineHistoryViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_vaccine_history_recyclerview,parent,false)
        return VaccineHistoryViewHolder(v)
    }

    override fun onBindViewHolder(holder: VaccineHistoryViewHolder, position: Int) {
        val item = vaccineList[position]
        holder.bind(item)
    }

    override fun getItemCount() = vaccineList.size
}