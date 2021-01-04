package com.example.medchat.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medchat.R
import com.example.medchat.room.Message

class ChatListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list : List<Message> = emptyList()


    inner class SentMessageViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        private val messageBody : TextView = itemView.findViewById(R.id.text_message_body)
        private val messageTime : TextView = itemView.findViewById(R.id.text_message_time)

        fun bind(item : Message){
            messageBody.text = item.message
            messageTime.text = item.timestamp.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.sent_message_list_item,parent,false)
        return SentMessageViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val item = list[position]
        (holder as SentMessageViewHolder).bind(item)
    }

    override fun getItemCount(): Int {
       return list.size
    }


}