package com.babbarandrotech.medchat.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patient_table")
data class Patient(
    val patientName: String,
    val mobile: Long,
    val age: Int,
    val address: String,
    val createdAt : Long,
    val problem: String = "",
    val bloodGroup : String? = null,
    @PrimaryKey(autoGenerate = true) val patientId: Int = 0
)

