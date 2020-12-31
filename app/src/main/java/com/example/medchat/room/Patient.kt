package com.example.medchat.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patient_table")
data class Patient(
    val patientName: String,
    val mobile: Long,
    val age: Int,
    val address: String,
    val problem: String = "",
    @PrimaryKey(autoGenerate = true) val patientId: Int = 0
)

