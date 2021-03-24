package com.example.medchat.repository

import android.util.Log
import com.example.medchat.room.*
import com.example.medchat.ui.ChatFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.nio.file.InvalidPathException

class Repository(
    private val patientdao: PatientDao,
    private val bpDao: BpDao,
    private val bloodSugarDao: BloodSugarDao,
    private val allergyDao: AllergyDao,
    private val vaccineDao: VaccineDao
) {

    suspend fun insertBpRecord(bpRecord: BpRecord) = bpDao.insertBpRecord(bpRecord)

    suspend fun bpHistory(patientId: Int) = withContext(Dispatchers.IO) {
        bpDao.bpHistory(patientId)
    }

    suspend fun latestBpRecord(patientId: Int) = withContext(Dispatchers.IO) {
        bpDao.latestBpRecord(patientId)
    }

    //

    suspend fun insertBloodSugarRecord(bloodSugarRecord: BloodSugarRecord) =
        bloodSugarDao.insertBloodSugarRecord(bloodSugarRecord)

    suspend fun bloodSugarHistory(patientId: Int) =
        withContext(Dispatchers.IO) { bloodSugarDao.historyBloodSugar(patientId) }

    suspend fun latestBloodSugarRecord(patientId: Int) = withContext(Dispatchers.IO) {
        bloodSugarDao.latestBloodSugarRecord(patientId)
    }

    //

    suspend fun insertAllergyRecord(allergyRecord: AllergyRecord) =
        allergyDao.insertAllergyRecord(allergyRecord)

    suspend fun allergyHistory(patientId: Int) =
        withContext(Dispatchers.IO) { allergyDao.historyAllergy(patientId) }

    suspend fun latestAllergyRecord(patientId: Int) = withContext(Dispatchers.IO) {
        allergyDao.latestAllergyRecord(patientId)
    }

    //

    suspend fun insertVaccineRecord(vaccineRecord: VaccineRecord) =
        vaccineDao.insertVaccineRecord(vaccineRecord)

    suspend fun vaccineHistory(patientId: Int) =
        withContext(Dispatchers.IO) { vaccineDao.historyVaccine(patientId) }

    suspend fun latestVaccineRecord(patientId: Int) = withContext(Dispatchers.IO) {
        vaccineDao.latestVaccineRecord(patientId)
    }


    //

    suspend fun insertPatient(patient: Patient) = patientdao.insertPatient(patient)

    suspend fun updatePatientProblem(problem: String, patientId: Int) =
        patientdao.updatePatientProblem(problem, patientId)

    suspend fun updateBloodGroup(bloodGroup: String, patientId: Int) =
        patientdao.updateBloodGroup(bloodGroup, patientId)

    suspend fun updateMustPatientDetail(age : Int,contact: Long,address : String,patientId: Int) =
        patientdao.updateMustPatientDetail(age,contact,address,patientId)

    suspend fun deletePatient(patientId: Int) = patientdao.deletePatient(patientId)

    suspend fun allPatients() =
        withContext(Dispatchers.IO) {
            Log.d(ChatFragment.TAG, "allPatientListOnThread: ${Thread.currentThread().name}")
            patientdao.allPatients()
        }


    suspend fun returnPatient(patientId: Int) =
        withContext(Dispatchers.IO) { patientdao.returnPatient(patientId) }

    //

    suspend fun createMessage(message: Message) = patientdao.createMessage(message)

    suspend fun deleteMessage(messageId: Int) = patientdao.deleteMessage(messageId)

    suspend fun allLastMessagesList() =
        withContext(Dispatchers.IO) {
            Log.d(ChatFragment.TAG, "allLastMessagesListOnThread: ${Thread.currentThread().name}")
            patientdao.allLastMessagesList()
        }


    suspend fun listChatHistory(patientId: Int) =
        withContext(Dispatchers.IO) {
            Log.d(ChatFragment.TAG, "listChatHistoryOnThread: ${Thread.currentThread().name}")
            patientdao.listChatHistory(patientId)
        }

}