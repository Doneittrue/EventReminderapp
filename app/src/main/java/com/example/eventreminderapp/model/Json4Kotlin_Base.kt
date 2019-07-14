package com.example.eventreminderapp.model

import com.google.gson.annotations.SerializedName


data class Json4Kotlin_Base (

	@SerializedName("id") val id : String,
	@SerializedName("name") val name : String,
	@SerializedName("events") val events : Events
)