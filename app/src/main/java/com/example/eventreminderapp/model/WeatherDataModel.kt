package com.example.eventreminderapp.model

import org.json.JSONException
import org.json.JSONObject

class WeatherDataModel {

    // Member variables that hold our relevant weather inforomation.
    var mTemperature: String? = null
    var city: String? = null
    var iconName: String? = null
    var mCondition: Int = 0

    // Getter methods for temperature, city, and icon name:

    val temperature: String
        get() = mTemperature!! + "°"

    companion object {


        // Create a WeatherDataModel from a JSON.
        // We will call this instead of the standard constructor.
        fun fromJson(jsonObject: JSONObject): WeatherDataModel? {

            // JSON parsing is risky business. Need to surround the parsing code with a try-catch block.
            try {
                val weatherData = WeatherDataModel()

                weatherData.city = jsonObject.getString("name")
                weatherData.mCondition = jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id")
                weatherData.iconName = updateWeatherIcon(weatherData.mCondition)

                val tempResult = jsonObject.getJSONObject("main").getDouble("temp") - 273.15
                val roundedValue = Math.rint(tempResult).toInt()

                weatherData.mTemperature = Integer.toString(roundedValue)

                return weatherData

            } catch (e: JSONException) {
                e.printStackTrace()
                return null
            }

        }

        // Get the weather image name from OpenWeatherMap's condition (marked by a number code)
        private fun updateWeatherIcon(condition: Int): String {

            return when (condition) {
                in 0..299 -> "tstorm1"
                in 300..499 -> "light_rain"
                in 500..599 -> "shower3"
                in 600..700 -> "snow4"
                in 701..771 -> "fog"
                in 772..799 -> "tstorm3"
                800 -> "sunny"
                in 801..804 -> "cloudy2"
                in 900..902 -> "tstorm3"
                903 -> "snow5"
                904 -> "sunny"
                in 905..1000 -> "tstorm3"
                else -> "dunno"

            }
        }


    }
}
