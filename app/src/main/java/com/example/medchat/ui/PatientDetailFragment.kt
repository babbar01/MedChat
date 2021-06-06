package com.example.medchat.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.medchat.R
import com.example.medchat.viewModel.SharedViewModel
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.android.synthetic.main.fragment_patient_detail.*
import kotlinx.android.synthetic.main.fragment_patient_detail.view.*
import java.text.SimpleDateFormat
import java.util.*


class PatientDetailFragment : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_patient_detail, container, false)

        val sharedViewModel = activity?.run {
            ViewModelProvider(this)[SharedViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        val actionbar = v.findViewById<MaterialToolbar>(R.id.action_bar_patientDetailFragment)
        val collapsingToolbar = v.findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar_patientDetailFragment)
        collapsingToolbar.apply {
            setCollapsedTitleTextColor( ContextCompat.getColor(context,R.color.colorBlack))
            setExpandedTitleColor(Color.WHITE)
        }


        actionbar.setNavigationIcon(R.drawable.ic_back)
        actionbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        val layoutBloodPressure = v.findViewById<LinearLayout>(R.id.linear_layout_blood_pressure)
        layoutBloodPressure.setOnClickListener{
            sharedViewModel.activeRecord = 0
            findNavController().navigate(R.id.action_patientDetailFragment_to_timelineViewFragment)
        }

        val layoutBloodSugar = v.findViewById<LinearLayout>(R.id.linear_layout_blood_sugar)
        layoutBloodSugar.setOnClickListener{
            sharedViewModel.activeRecord = 1
            findNavController().navigate(R.id.action_patientDetailFragment_to_timelineViewFragment)
        }

        val layoutAllergy = v.findViewById<LinearLayout>(R.id.linear_layout_allergy)
        layoutAllergy.setOnClickListener{
            sharedViewModel.activeRecord = 2
            findNavController().navigate(R.id.action_patientDetailFragment_to_timelineViewFragment)
        }

        val layoutVaccine = v.findViewById<LinearLayout>(R.id.linear_layout_vaccines)
        layoutVaccine.setOnClickListener{
            sharedViewModel.activeRecord = 3
            findNavController().navigate(R.id.action_patientDetailFragment_to_timelineViewFragment)
        }

        val tvAge = v.tv_age_patient_detail
        val tvContact = v.tv_contact_patient_detail
        val tvAddress = v.tv_address_patient_detail
        val tvBloodGroup = v.tv_blood_group_patient_detail
        val tvLatestBp = v.tv_latest_blood_pressure_patient_detail
        val tvLatestBs = v.tv_latest_blood_sugar_patient_detail
        val tvLatestAllergy = v.tv_latest_allergy_patient_detail
        val tvLatestVaccine = v.tv_latest_vaccine_patient_detail
        val tvCreatedAt = v.tv_created_at_patient_detail
        val tvProblem = v.tv_problem_patient_detail

        sharedViewModel.apply {

            activeChatPatientDetails.observe(viewLifecycleOwner){
                it.apply {

                    tvAge.text = age.toString()
                    tvAddress.text = address
                    tvContact.text = mobile.toString()
                    tvBloodGroup.text = bloodGroup?:"Not Added"
                    tvCreatedAt.text = SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.US).format(Date(createdAt))
                    if(problem != "") tvProblem.text = problem

                    collapsingToolbar.title = patientName
                }
            }


            activePatientLatestBpRecord.observe(viewLifecycleOwner){
                tvLatestBp.text =  if(it != null) "S: ${it.systolic} \nD: ${it.diastolic}" else "Not Added"
            }

            activePatientLatestBloodSugarRecord.observe(viewLifecycleOwner){
                tvLatestBs.text = if(it != null) it.toString() else "Not Added"
            }

            activePatientLatestAllergyRecordRecord.observe(viewLifecycleOwner){
                tvLatestAllergy.text = it?: "Not Added"
            }

            activePatientLatestVaccineRecordpRecord.observe(viewLifecycleOwner){
                tvLatestVaccine.text = it?: "Not Added"
            }

        }

        v.icon_edit_problem.setOnClickListener{
            val fragment = CustomBottomSheetProblemDialogFragment()
            fragment.show(requireActivity().supportFragmentManager,CustomBottomSheetProblemDialogFragment.TAG)
        }

        v.icon_edit_blood_group.setOnClickListener{
            val fragment = CustomBottomSheetBloodGroupDialogFragment()
            fragment.show(requireActivity().supportFragmentManager,CustomBottomSheetBloodGroupDialogFragment.TAG)
        }

        v.icon_edit_patient_details.setOnClickListener{
            val fragment = CustomBottomSheetPatientDetailDialogFragment()
            fragment.show(requireActivity().supportFragmentManager,CustomBottomSheetPatientDetailDialogFragment.TAG)
        }

        return v

    }
}