package com.example.medchat.repository

import com.example.medchat.room.Message
import com.example.medchat.room.Patient
import com.example.medchat.room.PatientDao

class Repository(val patientdao : PatientDao) {

    fun insertPatient(patient : Patient) = patientdao.insertPatient(patient)

    fun deletePatient(patientId : Int) = patientdao.deletePatient(patientId)

    fun allPatients() = patientdao.allPatients()

    fun scanPatient(patientId: Int) = patientdao.scanPatient(patientId)

    fun createMessage(message: Message) = patientdao.createMessage(message)

    fun deleteMessage(messageId : Int) = patientdao.deleteMessage(messageId)

    fun allLastMessagesList() = patientdao.allLastMessagesList()

    fun listChatHistory(patientId: Int) = patientdao.listChatHistory(patientId)

}