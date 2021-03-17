package com.example.medchat.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BpDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBpRecord(bpRecord: BpRecord)

    @Query("Select * from bp_record_table where patientId = :patientId order by timestamp desc")
    fun bpHistory(patientId : Int) : LiveData<List<BpRecord>>

}