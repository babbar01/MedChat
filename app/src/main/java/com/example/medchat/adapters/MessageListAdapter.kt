package com.example.medchat.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medchat.R
import com.example.medchat.room.LastMessage

class MessageListAdapter : RecyclerView.Adapter<MessageListAdapter.MessageViewHolder>() {

    var list: List<LastMessage> = emptyList()

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val patientName: TextView = itemView.findViewById(R.id.list_patient_name)
        val lastMessage: TextView = itemView.findViewById(R.id.list_patient_lastmsg)
        val lastMessageTime: TextView = itemView.findViewById(R.id.list_patient_lastmsg_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.message_list_item, parent, false)
        return MessageViewHolder(v)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.patientName.text = list[position].patientName
        holder.lastMessage.text = list[position].message
        // TODO: 12/24/2020 change to proper date or time
        holder.lastMessageTime.text = list[position].time.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}