package com.example.medchat.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.medchat.room.*
import com.example.medchat.ui.ChatFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class Repository(val patientdao : PatientDao) {

    suspend fun insertPatient(patient : Patient) = patientdao.insertPatient(patient)

    suspend fun deletePatient(patientId : Int) = patientdao.deletePatient(patientId)

    suspend fun allPatients() =
         withContext(Dispatchers.IO){
             Log.d(ChatFragment.TAG, "allPatientListOnThread: ${Thread.currentThread().name}")
             patientdao.allPatients()
        }


    suspend fun scanPatient(patientId: Int) = patientdao.scanPatient(patientId)

    suspend fun createMessage(message: Message) = patientdao.createMessage(message)

    suspend fun deleteMessage(messageId : Int) = patientdao.deleteMessage(messageId)

    suspend fun allLastMessagesList() =
         withContext(Dispatchers.IO){
             Log.d(ChatFragment.TAG, "allLastMessagesListOnThread: ${Thread.currentThread().name}")
             patientdao.allLastMessagesList()
        }


    suspend fun listChatHistory(patientId: Int) =
        withContext(Dispatchers.IO){
            Log.d(ChatFragment.TAG, "listChatHistoryOnThread: ${Thread.currentThread().name}")
            patientdao.listChatHistory(patientId)
        }

}