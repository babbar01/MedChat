package com.example.medchat.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AllergyDao {

    @Insert
    suspend fun insertAllergyRecord(allergyRecord: AllergyRecord)

    @Query("Select * from allergy_record_table where patientId = :patientId order by timestamp desc")
    fun historyAllergy(patientId : Int) : LiveData<List<AllergyRecord>>

    @Query("Select txtAllergy from allergy_record_table where timestamp = (Select max(timestamp) from allergy_record_table where patientId = :patientId)")
    fun latestAllergyRecord(patientId: Int) : LiveData<String>

}