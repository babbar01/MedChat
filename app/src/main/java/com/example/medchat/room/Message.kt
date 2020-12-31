package com.example.medchat.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "message_table", foreignKeys = [ForeignKey(
        entity = Patient::class, parentColumns = ["patientId"],
        onDelete = ForeignKey.CASCADE, childColumns = ["recieverId"]
    )]
)
data class Message(
    @PrimaryKey(autoGenerate = true) val messageId: Int,
    val recieverId: Int,
    val message: String,
    val timestamp: Long?  // because we will put this value in the function of dao not in viewmodel
)