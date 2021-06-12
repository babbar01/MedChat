package com.babbarandrotech.medchat.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [Patient::class, Message::class, BpRecord::class, BloodSugarRecord::class, AllergyRecord::class, VaccineRecord::class],
    version = 4,
    exportSchema = false
)
abstract class PatientRoomDatabase : RoomDatabase() {

    abstract fun PatientDao(): PatientDao

    abstract fun BpDao(): BpDao

    abstract fun BloodSugarDao() : BloodSugarDao

    abstract fun AllergyDao() : AllergyDao

    abstract fun VaccineDao() : VaccineDao



    companion object {

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE bp_record_table(patientId INTEGER not null,systolic INTEGER not null,diastolic INTEGER not null,pulse INTEGER not null,notes TEXT ,timestamp INTEGER not null,bpRecordId INTEGER PRIMARY KEY AUTOINCREMENT not null)")
            }

        }

        private val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {

                database.execSQL("create table blood_sugar_record_table(patientId INTEGER not null,type TEXT not null,result INTEGER not null,notes TEXT,timestamp INTEGER not null,bsRecordId INTEGER PRIMARY KEY AUTOINCREMENT not null)")

                database.execSQL("create table allergy_record_table(patientId INTEGER not null,txtAllergy TEXT not null,notes TEXT,timestamp INTEGER not null,allergyRecordId INTEGER PRIMARY KEY AUTOINCREMENT not null)")

                database.execSQL("create table vaccine_record_table(patientId INTEGER not null,txtVaccine TEXT not null,notes TEXT,timestamp INTEGER not null,vaccineRecordId INTEGER PRIMARY KEY AUTOINCREMENT not null)")
            }

        }

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
                ).addMigrations(MIGRATION_2_3, MIGRATION_3_4).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
