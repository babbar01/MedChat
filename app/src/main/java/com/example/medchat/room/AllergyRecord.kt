package com.example.medchat.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "allergy_record_table")
data class AllergyRecord(
    val patientId : Int,
    val txtAllergy : String,
    val notes: String?,
    val timestamp: Long,
    @PrimaryKey(autoGenerate = true) val allergyRecordId : Int = 0
)