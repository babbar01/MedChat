package com.example.medchat.room.patient_table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patient_table")
data class Patient(@PrimaryKey(autoGenerate = true) val patientId : Int,
                   val patientName : String,
                   val mobile : Int,
                   val age : Int,
                   val address : String,
                   val problem : String
)

