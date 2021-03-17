package com.example.medchat.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VaccineDao {

    @Insert
    suspend fun insertVaccineRecord(vaccineRecord: VaccineRecord)

    @Query("Select * from vaccine_record_table where patientId = :patientId order by timestamp desc")
    fun historyVaccine(patientId : Int) : LiveData<List<VaccineRecord>>

}