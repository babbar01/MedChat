package com.example.medchat.room.message_table

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.medchat.room.patient_table.Patient

@Entity(
    tableName = "message_table", foreignKeys = [ForeignKey(
        entity = Patient::class, parentColumns = ["patientId"],
        onDelete = ForeignKey.CASCADE, childColumns = ["recieverId"]
    )]
)
data class Message(
    @PrimaryKey(autoGenerate = true) val messageId: Int,
    val recieverId: Int,
    val message: String
)