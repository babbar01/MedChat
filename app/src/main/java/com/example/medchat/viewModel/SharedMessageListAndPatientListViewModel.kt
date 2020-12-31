package com.example.medchat.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.medchat.repository.Repository
import com.example.medchat.room.PatientRoomDatabase

class SharedMessageListAndPatientListViewModel(application: Application) : AndroidViewModel(application) {

    // TODO: 12/30/2020 run all of this database calls in background thread


    private val dao = PatientRoomDatabase.getDatabase(application).PatientDao()

    private val repository = Repository(dao)

    val allLastMessagesList = repository.allLastMessagesList()

    val allPatientList = repository.allPatients()
}