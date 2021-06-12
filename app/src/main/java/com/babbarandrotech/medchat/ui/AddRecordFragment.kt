package com.babbarandrotech.medchat.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.babbarandrotech.medchat.R
import com.babbarandrotech.medchat.room.AllergyRecord
import com.babbarandrotech.medchat.room.BloodSugarRecord
import com.babbarandrotech.medchat.room.BpRecord
import com.babbarandrotech.medchat.room.VaccineRecord
import com.babbarandrotech.medchat.viewModel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_add_record.view.*


class AddRecordFragment : Fragment() {

    private var sharedViewModel: SharedViewModel? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_add_record, container, false)

        sharedViewModel = activity?.run {
            ViewModelProvider(this)[SharedViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        val bpAddRecordLayout = v.findViewById<ConstraintLayout>(R.id.bp_add_record_layout)
        val bloodSugarAddRecordLayout =
            v.findViewById<LinearLayout>(R.id.blood_sugar_add_record_layout)
        val allergyAddRecordLayout =
            v.findViewById<LinearLayout>(R.id.allergy_add_record_layout)
        val vaccineAddRecordLayout =
            v.findViewById<LinearLayout>(R.id.vaccine_add_record_layout)

        when (sharedViewModel?.activeRecord) {
            0 -> bpAddRecordLayout.visibility = View.VISIBLE
            1 -> bloodSugarAddRecordLayout.visibility = View.VISIBLE
            2 -> allergyAddRecordLayout.visibility = View.VISIBLE
            3 -> vaccineAddRecordLayout.visibility = View.VISIBLE
        }

        when {
            bpAddRecordLayout.isVisible -> setClickListenerOnBtnBpAddRecordLayout(v)
            bloodSugarAddRecordLayout.isVisible -> setClickListenerOnBtnBloodSugarAddRecordLayout(v)
            allergyAddRecordLayout.isVisible -> setClickListenerOnBtnAllergyAddRecordLayout(v)
            vaccineAddRecordLayout.isVisible -> setClickListenerOnBtnVaccineAddRecordLayout(v)
        }

        return v

    }

    private fun setClickListenerOnBtnVaccineAddRecordLayout(v: View) {

        v.btn_save_vaccine_record.setOnClickListener {

            if (v.et_vaccine_add_record.text.isEmpty()) {
                Toast.makeText(context, "Add Some Record!", Toast.LENGTH_SHORT).show()
            } else {

                val vaccineString = v.et_vaccine_add_record.text.toString()
                val vaccineNotes = v.et_vaccine_notes_add_record.text.toString()

                val newVaccineRecord = VaccineRecord(
                    sharedViewModel?.activeIntChatPatientId!!,
                    vaccineString,
                    vaccineNotes,
                    System.currentTimeMillis()
                )

                v.et_vaccine_add_record.text.clear()
                v.et_vaccine_notes_add_record.text.clear()

                sharedViewModel?.insertVaccineRecord(newVaccineRecord)

                Toast.makeText(context, "Record Inserted", Toast.LENGTH_SHORT).show()
            }


        }

    }

    private fun setClickListenerOnBtnAllergyAddRecordLayout(v: View) {

        v.btn_save_allergy_record.setOnClickListener {

            if (v.et_allergies_add_record.text.isEmpty()) {
                Toast.makeText(context, "Add Some Record!", Toast.LENGTH_SHORT).show()
            } else {

                val allergyString = v.et_allergies_add_record.text.toString()
                val allergyNotes = v.et_allergies_notes_add_record.text.toString()

                val newAllergyRecord = AllergyRecord(
                    sharedViewModel?.activeIntChatPatientId!!,
                    allergyString,
                    allergyNotes,
                    System.currentTimeMillis()
                )

                sharedViewModel?.insertAllergyRecord(newAllergyRecord)

                v.et_allergies_add_record.text.clear()
                v.et_allergies_notes_add_record.text.clear()

                Toast.makeText(context, "Record Inserted", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun setClickListenerOnBtnBloodSugarAddRecordLayout(v: View) {
        val radioGroup = v.radio_group_blood_sugar_reading_type

        v.btn_save_blood_sugar_record.setOnClickListener {

            val typeString = when (radioGroup.checkedRadioButtonId) {
                v.radio_fasting.id -> "Fasting"
                v.radio_postprandium.id -> "Postprandial"
                v.radio_random.id -> "Random"
                else -> "Not specified"
            }

            if (v.et_blood_sugar_result.text.isEmpty()) {
                Toast.makeText(context, "Please add Result value!", Toast.LENGTH_SHORT).show()
            } else {

                val resultString = v.et_blood_sugar_result.text.toString().toInt()
                val bloodSugarNotes = v.et_blood_sugar_notes.text.toString()

                val newBloodSugarRecord = BloodSugarRecord(
                    sharedViewModel?.activeIntChatPatientId!!,
                    typeString,
                    resultString,
                    bloodSugarNotes,
                    System.currentTimeMillis()
                )

                v.et_blood_sugar_result.text.clear()
                v.et_blood_sugar_notes.text.clear()
                radioGroup.clearCheck()

                sharedViewModel?.insertBloodSugarRecord(newBloodSugarRecord)

                Toast.makeText(context, "Record Inserted", Toast.LENGTH_SHORT).show()
            }


        }


    }

    private fun setClickListenerOnBtnBpAddRecordLayout(v: View) {
        val btnAddNewBpRecord = v.btn_add_new_bp_record
        val systolic = v.et_systolic
        val diastolic = v.et_diastolic
        val pulse = v.et_pulse
        val bpNotes = v.et_bp_notes

        btnAddNewBpRecord.setOnClickListener {

            if (systolic.text.isEmpty() ||
                diastolic.text.isEmpty() ||
                pulse.text.isEmpty()
            ) {
                Toast.makeText(
                    context,
                    "Please add all the values excluding notes",
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                val bpRecord = BpRecord(
                    sharedViewModel?.activeIntChatPatientId!!,
                    systolic.text.toString().toInt(),
                    diastolic.text.toString().toInt(),
                    pulse.text.toString().toInt(),
                    bpNotes.text.toString(),
                    System.currentTimeMillis()
                )

                systolic.text.clear()
                diastolic.text.clear()
                pulse.text.clear()
                bpNotes.text.clear()
                sharedViewModel?.insertBpRecord(bpRecord)

                Toast.makeText(context, "record inserted", Toast.LENGTH_SHORT).show()
            }


        }
    }
}