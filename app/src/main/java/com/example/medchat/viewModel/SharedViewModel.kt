package com.example.medchat.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.medchat.repository.Repository
import com.example.medchat.room.LastMessage
import com.example.medchat.room.Message
import com.example.medchat.room.PatientItem
import com.example.medchat.room.PatientRoomDatabase
import com.example.medchat.ui.ChatFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    // todo can use flow instead of livedata


    private val dao = PatientRoomDatabase.getDatabase(application).PatientDao()

    private val repository = Repository(dao)

    val allLastMessagesList: LiveData<List<LastMessage>>  = liveData{emitSource(repository.allLastMessagesList())}

    val allPatientList : LiveData<List<PatientItem>>  = liveData { emitSource(repository.allPatients()) }


    val activeChatPatientId = MutableLiveData<Int>()
    var activeIntChatPatientId : Int? = null

    val activeChatHistory : LiveData<List<Message>> =
        activeChatPatientId.switchMap { value ->
        liveData { emitSource(repository.listChatHistory(value)) }
    }

    fun loadChatHistory(patientId : Int){
        activeChatPatientId.value = patientId
        activeIntChatPatientId = patientId
//        viewModelScope.launch(Dispatchers.IO) { activeChatHistory = repository.listChatHistory(patientId) }
    }

    fun insertMessage(message: Message){
        viewModelScope.launch(Dispatchers.IO) { repository.createMessage(message) }

    }
}