package com.babbarandrotech.medchat.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.babbarandrotech.medchat.R
import com.babbarandrotech.medchat.room.LastMessage
import java.text.SimpleDateFormat
import java.util.*

class MessageListAdapter(private val onItemClickListener: (LastMessage) -> Unit) : RecyclerView.Adapter<MessageListAdapter.MessageViewHolder>() {

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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val item = list[position]

        holder.patientName.text = item.patientName
        holder.lastMessage.text = item.message

        val datetime = Date(item.time)
        val formatter = if(!isDateToday(item.time))
        {
            SimpleDateFormat("dd/MM/yyyy", Locale.US)
        } else {
            SimpleDateFormat("hh:mm aa", Locale.US)
        }
        val dateString = formatter.format(datetime)

        holder.lastMessageTime.text = dateString

        // JAVA 8 style
//        val date : LocalDate = Instant.ofEpochMilli(item.time)
//            .atZone(ZoneId.systemDefault())
//            .toLocalDate()
//
//        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
//        val formattedDate: String = now.format(formatter)



        holder.itemView.setOnClickListener{
            onItemClickListener(item)
        }
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