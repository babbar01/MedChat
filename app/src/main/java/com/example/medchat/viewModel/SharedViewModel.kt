package com.example.medchat.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.medchat.repository.Repository
import com.example.medchat.room.Message
import com.example.medchat.room.PatientRoomDatabase
import com.example.medchat.ui.ChatFragment

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    // TODO: 12/30/2020 run all of this database calls in background thread


    private val dao = PatientRoomDatabase.getDatabase(application).PatientDao()

    private val repository = Repository(dao)

    val allLastMessagesList = repository.allLastMessagesList()

    val allPatientList = repository.allPatients()

    var activeChatPatientId : Int? = null
    var activeChatHistory : LiveData<List<Message>>? = null

    fun loadChatHistory(patientId : Int){
        activeChatPatientId = patientId
        Log.d(ChatFragment.TAG, "onCreateView: ${activeChatPatientId}")
        activeChatHistory = repository.listChatHistory(patientId)
    }

    fun insertMessage(message: Message){
        val newThread = Thread{
            repository.createMessage(message)
        }

        newThread.start()
    }
}