package com.babbarandrotech.medchat.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.babbarandrotech.medchat.repository.Repository
import com.babbarandrotech.medchat.room.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    // todo can use flow instead of livedata

    private val patientRoomDatabase = PatientRoomDatabase.getDatabase(application)
    private val patientDao = patientRoomDatabase.PatientDao()
    private val bpDao = patientRoomDatabase.BpDao()
    private val bloodSugarDao = patientRoomDatabase.BloodSugarDao()
    private val allergyDao = patientRoomDatabase.AllergyDao()
    private val vaccineDao = patientRoomDatabase.VaccineDao()


    private val repository = Repository(patientDao, bpDao, bloodSugarDao, allergyDao, vaccineDao)

    val allLastMessagesList: LiveData<List<LastMessage>> =
        liveData { emitSource(repository.allLastMessagesList()) }

    val allPatientList: LiveData<List<PatientItem>> =
        liveData { emitSource(repository.allPatients()) }

    val activeSearchQuery = MutableLiveData<String>()
    /* is updated every time search query changes and initially set to ""(empty) in
     onCreate of NewMessageFragment */

    val filteredPatientList: LiveData<List<PatientItem>> =
        activeSearchQuery.switchMap { charString ->
            allPatientList.map {
                var patientListFiltered: MutableList<PatientItem>

                if (charString.isNullOrEmpty()) patientListFiltered = it as MutableList<PatientItem>
                else {
                    patientListFiltered = mutableListOf()

                    for (patientrow in it) {
                        if (patientrow.patientName.toLowerCase().contains(charString.toLowerCase())
                            || patientrow.patientId.toString().contains(charString)
                        )
                            patientListFiltered.add(patientrow)
                    }
                }
                // last line is returned
                patientListFiltered
            }
        }


    val activeChatPatientId = MutableLiveData<Int>()
    var activeIntChatPatientId: Int? = null

    val activeChatHistory: LiveData<List<Message>> =
        activeChatPatientId.switchMap { value ->
            liveData { emitSource(repository.listChatHistory(value)) }
        }

    val activeChatPatientDetails = activeChatPatientId.switchMap {
        liveData { emitSource(repository.returnPatient(it)) }
    }

    val activeChatPatientName = activeChatPatientDetails.map { it.patientName }

    fun loadChatHistory(patientId: Int) {
        activeChatPatientId.value = patientId
        activeIntChatPatientId = patientId
//        viewModelScope.launch(Dispatchers.IO) { activeChatHistory = repository.listChatHistory(patientId) }
    }

    fun insertMessage(message: Message) {
        viewModelScope.launch(Dispatchers.IO) { repository.createMessage(message) }

    }

    fun insertPatient(newPatient: Patient) {
        viewModelScope.launch(Dispatchers.IO) { repository.insertPatient(newPatient) }
    }

    fun updatePatientProblem(problem: String, patientId: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePatientProblem(
                problem,
                patientId
            )
        }

    fun updatePatientBloodGroup(bloodGroup: String, patientId: Int) = viewModelScope.launch {
        repository.updateBloodGroup(bloodGroup, patientId)
    }

    fun updateMustPatientDetail(age : Int,contact: Long,address : String,patientId: Int) = viewModelScope.launch {
        repository.updateMustPatientDetail(age,contact,address,patientId)
    }

    var activeRecord: Int = 0   // 0 for bp, 1 for sugar and like this...

    fun insertBpRecord(bpRecord: BpRecord) {
        viewModelScope.launch(Dispatchers.IO) { repository.insertBpRecord(bpRecord) }
    }

    val activePatientBloodPressureHistory = activeChatPatientId.switchMap {
        liveData { emitSource(repository.bpHistory(it)) }
    }

    //

    fun insertBloodSugarRecord(bloodSugarRecord: BloodSugarRecord) {
        viewModelScope.launch(Dispatchers.IO) { repository.insertBloodSugarRecord(bloodSugarRecord) }
    }

    val activePatientBloodSugarHistory = activeChatPatientId.switchMap {
        liveData { emitSource(repository.bloodSugarHistory(it)) }
    }

    //

    fun insertAllergyRecord(allergyRecord: AllergyRecord) {
        viewModelScope.launch(Dispatchers.IO) { repository.insertAllergyRecord(allergyRecord) }
    }

    val activePatientAllergyHistory = activeChatPatientId.switchMap {
        liveData { emitSource(repository.allergyHistory(it)) }
    }

    //

    fun insertVaccineRecord(vaccineRecord: VaccineRecord) {
        viewModelScope.launch(Dispatchers.IO) { repository.insertVaccineRecord(vaccineRecord) }
    }

    val activePatientVaccineHistory = activeChatPatientId.switchMap {
        liveData { emitSource(repository.vaccineHistory(it)) }
    }

    val activePatientLatestBpRecord = activeChatPatientId.switchMap {
        liveData { emitSource(repository.latestBpRecord(it)) }
    }

    val activePatientLatestBloodSugarRecord = activeChatPatientId.switchMap {
        liveData { emitSource(repository.latestBloodSugarRecord(it)) }
    }

    val activePatientLatestAllergyRecordRecord = activeChatPatientId.switchMap {
        liveData { emitSource(repository.latestAllergyRecord(it)) }
    }

    val activePatientLatestVaccineRecordpRecord = activeChatPatientId.switchMap {
        liveData { emitSource(repository.latestVaccineRecord(it)) }
    }


}
