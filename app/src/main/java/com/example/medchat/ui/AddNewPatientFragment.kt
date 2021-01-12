package com.example.medchat.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.medchat.R
import com.example.medchat.hideKeyboard
import com.example.medchat.repository.Repository
import com.example.medchat.room.Patient
import com.example.medchat.room.PatientRoomDatabase
import com.example.medchat.viewModel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_add_new_patient.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AddNewPatientFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_add_new_patient, container, false)

//        val etName = v.findViewById<EditText>(R.id.et_patient_name)
//        val etAge = v.findViewById<EditText>(R.id.et_patient_age)
//        val etAddress = v.findViewById<EditText>(R.id.et_patient_address)
//        val etMobile = v.findViewById<EditText>(R.id.et_patient_mobile)

        val viewmodelScope = ViewModelProvider(this)[SharedViewModel::class.java].viewModelScope


        val btnAddPatient = v.findViewById<Button>(R.id.add_new_patient_button_form_screen)
        btnAddPatient.setOnClickListener{
            val name = et_patient_name.text.toString()
            val age = et_patient_age.text.toString().toInt()
            val address = et_patient_address.text.toString()
            val mobile = et_patient_mobile.text.toString().toLong()

            val newPatient = Patient(name,mobile,age,address)




            viewmodelScope.launch{
                withContext(Dispatchers.IO){
                    container?.context?.let {
                    val dao = PatientRoomDatabase.getDatabase(it).PatientDao()
                    val repo = Repository(dao)

                    repo.insertPatient(newPatient)
                    }
                }

                if(lifecycle.currentState >= Lifecycle.State.STARTED) {
                    hideKeyboard()
                    findNavController().navigateUp()
                }


            }

        }


        return v
    }

}