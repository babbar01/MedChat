package com.example.medchat.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Patient::class, Message::class], version = 2, exportSchema = false)
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
                    "patients_database"    /*
    we changed its name because we hadn't studied migration yet so as to cope up up with change in scheme of patient table
    but not recommended because we will lose all of our data
    */
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
