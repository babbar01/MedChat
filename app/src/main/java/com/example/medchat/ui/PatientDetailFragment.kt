package com.example.medchat.ui

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.medchat.R
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.appbar.MaterialToolbar


class PatientDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_patient_detail, container, false)

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

        val clickableCardview = v.findViewById<CardView>(R.id.cardview_blood_pressure_sugar_etc)
        clickableCardview.setOnClickListener{
            findNavController().navigate(R.id.action_patientDetailFragment_to_timelineViewFragment)
        }

        return v

    }
}