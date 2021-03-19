package com.example.medchat.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BloodSugarDao {

    @Insert
    suspend fun insertBloodSugarRecord(bloodSugarRecord: BloodSugarRecord)

    @Query("Select * from blood_sugar_record_table where patientId = :patientId order by timestamp desc")
    fun historyBloodSugar(patientId : Int) : LiveData<List<BloodSugarRecord>>

    @Query("Select result from blood_sugar_record_table where timestamp = (Select max(timestamp) from blood_sugar_record_table where patientId = :patientId)")
    fun latestBloodSugarRecord(patientId: Int) : LiveData<Int>
}