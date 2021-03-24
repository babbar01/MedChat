package com.example.medchat.room

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPatient(patient: Patient)

    @Query("Delete from patient_table where patientId = :patientId")
    suspend fun deletePatient(patientId: Int)

    @Query("Select patientId,patientName from patient_table order by createdAt desc")
    fun allPatients(): LiveData<List<PatientItem>>

    @Query("Select * from patient_table where patientId = :patientId")
    fun returnPatient(patientId: Int): LiveData<Patient>

    @Query("Update patient_table set age = :age, mobile = :contact, address = :address where patientId = :patientId")
    suspend fun updateMustPatientDetail(age : Int,contact: Long,address : String,patientId: Int)

    @Query("Update patient_table set problem = :problem where patientId = :patientId")
    suspend fun updatePatientProblem(problem : String,patientId: Int)

    @Query("Update patient_table set bloodGroup = :bloodGroup where patientId = :patientId")
    suspend fun updateBloodGroup(bloodGroup : String,patientId: Int)

    // messages queries

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createMessage(message: Message)

    @Query("Delete from message_table where messageId = :messageId")
    suspend fun deleteMessage(messageId: Int)

    @Query("Select table2.recieverId,patient_table.patientName,table2.message,table2.time from patient_table join (Select recieverId,message,max(timestamp) as time from message_table group by recieverId) as table2 where table2.recieverId = patient_table.patientId order by table2.time desc;")
    fun allLastMessagesList(): LiveData<List<LastMessage>>

    @Query("Select * from message_table where recieverId = :patientId")
    fun listChatHistory(patientId: Int) : LiveData<List<Message>>


}