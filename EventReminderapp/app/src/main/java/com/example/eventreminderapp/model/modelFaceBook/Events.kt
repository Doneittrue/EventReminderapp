package com.example.eventreminderapp.model.modelFaceBook

import com.google.gson.annotations.SerializedName

data class Events (

    @SerializedName("data") val data : List<Data>,
    @SerializedName("paging") val paging : Paging
)