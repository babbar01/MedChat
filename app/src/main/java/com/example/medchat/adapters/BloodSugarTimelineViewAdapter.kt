package com.example.medchat.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medchat.R
import com.example.medchat.room.BloodSugarRecord
import com.github.vipulasri.timelineview.TimelineView
import kotlinx.android.synthetic.main.item_blood_sugar_timeline.view.*
import java.text.SimpleDateFormat
import java.util.*

class BloodSugarTimelineViewAdapter : RecyclerView.Adapter<BloodSugarTimelineViewAdapter.BloodSugarTimelineViewHolder>() {

    var bloodSugarList : List<BloodSugarRecord> = emptyList()

    inner class BloodSugarTimelineViewHolder(itemView : View, viewType : Int) : RecyclerView.ViewHolder(itemView){

        val bloodSugarType = itemView.tv_type_blood_sugar
        val resultBloodSugar = itemView.tv_result_blood_sugar
        val notesBloodSugar = itemView.tv_notes_blood_sugar
        val dateTime = itemView.tv_blood_sugar_date_time

        fun bind(item: BloodSugarRecord) {
            bloodSugarType.text = item.type
            resultBloodSugar.text = item.result.toString()
            notesBloodSugar.text = item.notes
            dateTime.text = SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.US).format(Date(item.timestamp))
        }

        val timelineView = itemView.blood_sugar_history_timelineview

        init {
            timelineView.initLine(viewType)
        }

    }

    override fun getItemViewType(position: Int) = TimelineView.getTimeLineViewType(position,itemCount)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BloodSugarTimelineViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_blood_sugar_timeline,parent,false)
        return BloodSugarTimelineViewHolder(v,viewType)
    }

    override fun onBindViewHolder(holder: BloodSugarTimelineViewHolder, position: Int) {
        val item = bloodSugarList[position]
        holder.bind(item)
    }

    override fun getItemCount() = bloodSugarList.size
}