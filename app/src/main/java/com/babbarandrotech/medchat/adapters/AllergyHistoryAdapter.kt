package com.babbarandrotech.medchat.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.babbarandrotech.medchat.R
import com.babbarandrotech.medchat.room.AllergyRecord
import kotlinx.android.synthetic.main.item_allergy_history.view.*
import java.text.SimpleDateFormat
import java.util.*

class AllergyHistoryAdapter : RecyclerView.Adapter<AllergyHistoryAdapter.AllergyViewHolder>() {

    var allergyList : List<AllergyRecord> = emptyList()

    inner class AllergyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val tvAllergy = itemView.tv_allergy
        val tvAllergyNotes = itemView.tv_notes_allergy
        val dateTime = itemView.tv_allergy_date_time

        fun bind(item: AllergyRecord) {
            tvAllergy.text = item.txtAllergy
            tvAllergyNotes.text = item.notes
            dateTime.text = SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.US).format(Date(item.timestamp))
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllergyViewHolder {
       val v = LayoutInflater.from(parent.context).inflate(R.layout.item_allergy_history,parent,false)
        return AllergyViewHolder(v)
    }

    override fun onBindViewHolder(holder: AllergyViewHolder, position: Int) {
        val item = allergyList[position]
        holder.bind(item)
    }

    override fun getItemCount() = allergyList.size
}