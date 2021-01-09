package com.example.medchat.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [Patient::class, Message::class], version = 1, exportSchema = false)
abstract class PatientRoomDatabase : RoomDatabase() {

    abstract fun PatientDao(): PatientDao

    companion object {

        @Volatile
        private var INSTANCE: PatientRoomDatabase? = null

        fun getDatabase(context: Context): PatientRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PatientRoomDatabase::class.java,
                    "patient_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
