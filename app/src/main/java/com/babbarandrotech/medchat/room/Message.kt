package com.babbarandrotech.medchat.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

//todo see warning of compiler about foreign key in build tab
@Entity(
    tableName = "message_table", foreignKeys = [ForeignKey(
        entity = Patient::class, parentColumns = ["patientId"],
        onDelete = ForeignKey.CASCADE, childColumns = ["recieverId"]
    )]
)
data class Message(
    val recieverId: Int,
    val message: String,
    val timestamp: Long?,  // because we will put this value in the function of dao not in viewmodel
    @PrimaryKey(autoGenerate = true) val messageId: Int = 0
)