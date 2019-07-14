package com.example.eventreminderapp.model

import com.google.gson.annotations.SerializedName



data class Data (

	@SerializedName("timezone") val timezone : String,
	@SerializedName("updated_time") val updated_time : String,
	@SerializedName("description") val description : String,
	@SerializedName("id") val id : String
)