package com.example.medchat.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PatientDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPatient(patient: Patient)

    @Query("Delete from patient_table where patientId = :patientId")
    fun deletePatient(patientId: Int)

    @Query("Select patientId,patientName from patient_table")
    fun allPatients(): LiveData<List<PatientItem>>

    @Query("Select * from patient_table where patientId = :patientId")
    fun scanPatient(patientId: Int): Patient

    // messages queries

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun createMessage(message: Message)

    @Query("Delete from message_table where messageId = :messageId")
    fun deleteMessage(messageId: Int)

    // TODO: 12/23/2020 not right query yet
    @Query("Select table2.recieverId,patient_table.patientName,table2.message,table2.time from patient_table join (Select recieverId,message,max(timestamp) as time from message_table group by recieverId) as table2")
    fun allLastMessagesList(): LiveData<List<LastMessage>>


}