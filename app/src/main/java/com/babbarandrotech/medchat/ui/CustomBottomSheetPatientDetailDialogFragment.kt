package com.babbarandrotech.medchat.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.babbarandrotech.medchat.R
import com.babbarandrotech.medchat.viewModel.SharedViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.layout_patient_detail_dialog_fragment.*

class CustomBottomSheetPatientDetailDialogFragment : BottomSheetDialogFragment() {

    companion object{
        val TAG = "PatientDetailDialog"
    }

    var sharedViewModel : SharedViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedViewModel = activity?.run { ViewModelProvider(this)[SharedViewModel::class.java] }
        return inflater.inflate(R.layout.layout_patient_detail_dialog_fragment,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sharedViewModel?.activeChatPatientDetails?.observe(viewLifecycleOwner){
            et_age_patient_detail_dialog.setText(it.age.toString())
            et_contact_patient_detail_dialog.setText(it.mobile.toString())
            et_address_patient_detail_dialog.setText(it.address)
        }

        btn_save_patient_detail_dialog.setOnClickListener{
            if (et_age_patient_detail_dialog.text.isEmpty() || et_contact_patient_detail_dialog.text.isEmpty() ||
                    et_address_patient_detail_dialog.text.isEmpty())
                Toast.makeText(context,"You cannot leave these fields empty", Toast.LENGTH_SHORT).show()
            else{
                sharedViewModel?.apply {
                    this.activeIntChatPatientId?.let { it1 ->
                        updateMustPatientDetail(et_age_patient_detail_dialog.text.toString().toInt(),
                            et_contact_patient_detail_dialog.text.toString().toLong(),
                            et_address_patient_detail_dialog.text.toString(),
                            it1
                        )
                    }
                }
                Toast.makeText(context,"Updated", Toast.LENGTH_SHORT).show()
            }
        }

    }
}