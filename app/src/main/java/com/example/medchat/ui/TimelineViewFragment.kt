package com.example.medchat.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.medchat.R
import com.google.android.material.tabs.TabLayout


class TimelineViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_timeline_view, container, false)

        /* we will use getChildFragmentManager() Return a private FragmentManager for placing and
        managing Fragments inside of this Fragment. */
        val navHostFragment = childFragmentManager.findFragmentById(R.id.timelineview_navhost_fragment)
        val navController = navHostFragment?.findNavController()

        val tabLayout = v.findViewById<TabLayout>(R.id.tab_layout_timelineview)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let {
                    if (it == 0)
                        navController?.navigate(R.id.action_addRecordFragment_to_historyFragment)
                    else navController?.navigate(R.id.action_historyFragment_to_addRecordFragment)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.d(ChatFragment.TAG, "onTabUnselected: called ${tab?.text}")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        return v
    }


}