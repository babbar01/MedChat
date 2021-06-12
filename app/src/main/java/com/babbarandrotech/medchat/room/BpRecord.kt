package com.babbarandrotech.medchat.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bp_record_table")
data class BpRecord(
    val patientId : Int,
    val systolic : Int,
    val diastolic : Int,
    val pulse : Int,
    val notes :String?,
    val timestamp : Long,
    @PrimaryKey(autoGenerate = true) val bpRecordId : Int = 0
)