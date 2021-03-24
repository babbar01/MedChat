package com.example.medchat.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.medchat.R
import com.example.medchat.viewModel.SharedViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.layout_blood_group_dialog_fragment.*
import kotlinx.android.synthetic.main.layout_problem_dialog_fragment.*

class CustomBottomSheetBloodGroupDialogFragment : BottomSheetDialogFragment() {

    companion object{
        val TAG = "BloodGroupDialog"
    }

    var sharedViewModel : SharedViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedViewModel = activity?.run { ViewModelProvider(this)[SharedViewModel::class.java] }
        return inflater.inflate(R.layout.layout_blood_group_dialog_fragment,container,false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sharedViewModel?.activeChatPatientDetails?.observe(viewLifecycleOwner){
            et_edit_blood_group_dialog.apply {
                if(it.bloodGroup != null) setText(it.bloodGroup)
                setSelectAllOnFocus(true)
                showSoftInputOnFocus
                requestFocus()

                activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            }
        }

        btn_save_blood_group_dialog.setOnClickListener{
            if (et_edit_blood_group_dialog.text.trim() == "")
                Toast.makeText(context,"You cannot leave it empty", Toast.LENGTH_SHORT).show()
            else{
                sharedViewModel?.apply {
                    this.activeIntChatPatientId?.let { it1 ->
                        updatePatientBloodGroup(et_edit_blood_group_dialog.text.toString(),
                            it1
                        )
                    }
                }
                Toast.makeText(context,"Updated", Toast.LENGTH_SHORT).show()
            }
        }

    }
}