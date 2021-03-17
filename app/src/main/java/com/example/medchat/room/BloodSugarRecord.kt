package com.example.medchat.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blood_sugar_record_table")
data class BloodSugarRecord(
    val patientId: Int,
    val type: String,
    val result: Int,
    val notes: String?,
    val timestamp: Long,
    @PrimaryKey(autoGenerate = true) val bsRecordId : Int = 0
)