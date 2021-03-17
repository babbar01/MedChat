package com.example.medchat.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medchat.R
import com.example.medchat.room.BpRecord
import com.github.vipulasri.timelineview.TimelineView
import kotlinx.android.synthetic.main.item_bp_timeline.view.*
import java.text.SimpleDateFormat
import java.util.*

class BpTimelineViewAdapter : RecyclerView.Adapter<BpTimelineViewAdapter.BpTimeLineViewHolder>() {

    var bpList : List<BpRecord> = emptyList()

    inner class BpTimeLineViewHolder(itemView : View, viewType: Int) : RecyclerView.ViewHolder(itemView){
        private val mtimeLineView : TimelineView = itemView.history_timelineview
        private val tvSystolic = itemView.tv_systolic
        private val tvDiastolic = itemView.tv_diastolic
        private val tvPulse = itemView.tv_pulse
        private val tvBpNotes = itemView.tv_bp_notes
        private val tvDateTime = itemView.tv_bp_date_time

        init {
            mtimeLineView.initLine(viewType)
        }

        fun bind(item: BpRecord) {
            tvSystolic.text = "${item.systolic}"
            tvDiastolic.text = "${item.diastolic}"
            tvPulse.text = "${item.pulse}"
            tvBpNotes.text = "${item.notes}"

            val epochTime = item.timestamp
            val dateTime = Date(epochTime)
            val dateTimeString = SimpleDateFormat("dd/MM/yyyy hh:mm aa",Locale.US).format(dateTime)

            tvDateTime.text = dateTimeString

        }
    }

    override fun getItemViewType(position: Int): Int {
        return TimelineView.getTimeLineViewType(position,itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BpTimeLineViewHolder {
       val v = LayoutInflater.from(parent.context).inflate(R.layout.item_bp_timeline,parent,false)
        return BpTimeLineViewHolder(v,viewType)
    }

    override fun onBindViewHolder(holder: BpTimeLineViewHolder, position: Int) {
        val item = bpList[position]
        holder.bind(item)

    }

    override fun getItemCount(): Int {
        return bpList.size
    }
}