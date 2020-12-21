package com.example.medchat.room.patient_table

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.medchat.room.message_table.Message

@Dao
interface PatientDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(patient: Patient)

    @Query("Delete from patient_table where patientId = :patientId")
    fun delete(patientId: Int)

    @Query("Select * from patient_table")
    fun allPatients() : LiveData<Patient>

    @Query("Select * from patient_table where patientId = :patientId")
    fun scanPatient(patientId: Int) : Patient

    // messages queries

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun createMessage(message: Message)

    @Query("Delete from message_table where messageId = :messageId")
    fun deleteMessage(messageId : Int)

}