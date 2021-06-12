package com.babbarandrotech.medchat.room



data class LastMessage(
    val recieverId: Int,
    val patientName : String,
    val message: String,
    val time: Long
)