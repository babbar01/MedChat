package com.babbarandrotech.medchat.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.babbarandrotech.medchat.R
import com.babbarandrotech.medchat.adapters.AllergyHistoryAdapter
import com.babbarandrotech.medchat.adapters.BloodSugarTimelineViewAdapter
import com.babbarandrotech.medchat.adapters.BpTimelineViewAdapter
import com.babbarandrotech.medchat.adapters.VaccineHistoryAdapter
import com.babbarandrotech.medchat.viewModel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_history.view.*


class HistoryFragment : Fragment() {

    private lateinit var bpTimeLineRecyclerView : RecyclerView
    private lateinit var bpTimelineAdapter : BpTimelineViewAdapter
    private lateinit var bloodSugarTimeLineRecyclerView : RecyclerView
    private lateinit var bloodSugarTimelineViewAdapter: BloodSugarTimelineViewAdapter
    private lateinit var allergyTimeLineRecyclerView : RecyclerView
    private lateinit var allergyHistoryAdapter: AllergyHistoryAdapter
    private lateinit var vaccineHistoryRecyclerView : RecyclerView
    private lateinit var vaccineHistoryAdapter: VaccineHistoryAdapter
    private var sharedViewModel: SharedViewModel? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_history, container, false)

        bpTimeLineRecyclerView = v.bp_timeline_recyclerview
        bloodSugarTimeLineRecyclerView = v.blood_sugar_timeline_recyclerview
        allergyTimeLineRecyclerView = v.allergy_timeline_recylerview
        vaccineHistoryRecyclerView = v.vaccine_timeline_recyclerview


        sharedViewModel = activity?.run {
            ViewModelProvider(this)[SharedViewModel::class.java]
        }

        setVisibility()

        return v

    }

    private fun setVisibility() {
        when (sharedViewModel?.activeRecord) {
            0 -> {
                bpTimeLineRecyclerView.visibility = View.VISIBLE
                initBpRecyclerView()

                sharedViewModel?.activePatientBloodPressureHistory?.observe(viewLifecycleOwner){
                    bpTimelineAdapter.bpList = it
                    bpTimelineAdapter.notifyDataSetChanged()
                }
            }
            1 -> {
                bloodSugarTimeLineRecyclerView.visibility = View.VISIBLE
                initBloodSugarRecyclerView()

                sharedViewModel?.activePatientBloodSugarHistory?.observe(viewLifecycleOwner){
                    bloodSugarTimelineViewAdapter.bloodSugarList = it
                    bloodSugarTimelineViewAdapter.notifyDataSetChanged()
                }

            }
            2 -> {
                allergyTimeLineRecyclerView.visibility = View.VISIBLE
                initAllergyRecyclerView()

                sharedViewModel?.activePatientAllergyHistory?.observe(viewLifecycleOwner){
                    allergyHistoryAdapter.allergyList = it
                    allergyHistoryAdapter.notifyDataSetChanged()
                }
            }
            3 -> {
                vaccineHistoryRecyclerView.visibility = View.VISIBLE
                initVaccineRecyclerView()

                sharedViewModel?.activePatientVaccineHistory?.observe(viewLifecycleOwner){
                    vaccineHistoryAdapter.vaccineList = it
                    vaccineHistoryAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun initVaccineRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        vaccineHistoryAdapter = VaccineHistoryAdapter()
        vaccineHistoryRecyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = vaccineHistoryAdapter
        }
    }

    private fun initAllergyRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        allergyHistoryAdapter = AllergyHistoryAdapter()
        allergyTimeLineRecyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = allergyHistoryAdapter
        }
    }

    private fun initBloodSugarRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        bloodSugarTimelineViewAdapter = BloodSugarTimelineViewAdapter()
        bloodSugarTimeLineRecyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = bloodSugarTimelineViewAdapter
        }
    }

    private fun initBpRecyclerView() {

        val linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        bpTimelineAdapter = BpTimelineViewAdapter()
        bpTimeLineRecyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = bpTimelineAdapter
        }

    }
}