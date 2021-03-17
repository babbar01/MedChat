package com.example.medchat.ui

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


class PatientDetailFragment : Fragment() {

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

        return v

    }
}