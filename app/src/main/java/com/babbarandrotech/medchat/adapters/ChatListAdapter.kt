package com.babbarandrotech.medchat.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.babbarandrotech.medchat.R
import com.babbarandrotech.medchat.room.Message
import java.text.SimpleDateFormat
import java.util.*

class ChatListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list : List<Message> = emptyList()


    inner class SentMessageViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        private val messageBody : TextView = itemView.findViewById(R.id.text_message_body)
        private val messageTime : TextView = itemView.findViewById(R.id.text_message_time)

        fun bind(item : Message){
            messageBody.text = item.message

            item.timestamp?.let{
                val datetime = Date(it)
                val formatter =
                    if (!isDateToday(it))
                    {
                        SimpleDateFormat("dd/MM/yyyy", Locale.US)
                    } else {
                        SimpleDateFormat("hh:mm aa", Locale.US)
                    }
                val dateString = formatter.format(datetime)

                messageTime.text = dateString
            }
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

    fun isDateToday(milliSeconds: Long): Boolean {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        val getDate = calendar.time
        calendar.timeInMillis = System.currentTimeMillis()
        calendar[Calendar.HOUR_OF_DAY] = 0
        calendar[Calendar.MINUTE] = 0
        calendar[Calendar.SECOND] = 0
        val startDate = calendar.time
        return getDate > startDate
    }


}