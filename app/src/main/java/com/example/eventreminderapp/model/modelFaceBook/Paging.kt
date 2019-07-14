package com.example.eventreminderapp.model.modelFaceBook

import com.google.gson.annotations.SerializedName

data class Paging (

	@SerializedName("cursors") val cursors : Cursors
)