package com.example.medchat.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vaccine_record_table")
data class VaccineRecord(
    val patientId : Int,
    val txtVaccine : String,
    val notes: String?,
    val timestamp: Long,
    @PrimaryKey(autoGenerate = true) val vaccineRecordId : Int = 0
)