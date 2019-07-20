package com.example.eventreminderapp.model.modelFaceBook

import com.google.gson.annotations.SerializedName



data class Cursors (

	@SerializedName("before") val before : String,
	@SerializedName("after") val after : String
)