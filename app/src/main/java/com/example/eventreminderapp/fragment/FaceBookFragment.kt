package com.example.eventreminderapp.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eventreminderapp.activity.MainActivity
import com.example.eventreminderapp.adapter.MyEventAdapter
import com.example.eventreminderapp.R
import com.example.eventreminderapp.helper.hide
import com.example.eventreminderapp.helper.show
import com.example.eventreminderapp.model.Json4Kotlin_Base
import com.example.eventreminderapp.model.WeatherDataModel
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.gson.Gson
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.fragment_face_book.*
import kotlinx.android.synthetic.main.loader.*
import org.json.JSONObject
import java.util.*

class FaceBookFragment : Fragment() {
    private var callbackManager: CallbackManager? = null
    private lateinit var rv_event: RecyclerView


    private val WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather"

    // App ID to use OpenWeather data
    private val APP_ID = "e72ca729af228beabd5d20e3b7749713"

    // Set LOCATION_PROVIDER here. Using GPS_Provider for Fine Location (good for emulator):
    // Recommend using LocationManager.NETWORK_PROVIDER on physical devices (reliable & fast!)

    // Member Variables:
    private var mUseLocation: Boolean = true
    lateinit var mCityLabel: TextView
    lateinit var mWeatherImage: ImageView
    lateinit var mTemperatureLabel: TextView
    lateinit var signOutBtn: ImageButton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_face_book, container, false)
        initial(view)


//




        CallFacebook()
        return view


    }

    private fun initial(view: View?) {

        rv_event = view!!.findViewById(R.id.rv_event)
        rv_event.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        mCityLabel = view.findViewById(R.id.locationTV)
        mWeatherImage = view.findViewById(R.id.weatherSymbolIV)
        mTemperatureLabel = view.findViewById(R.id.tempTV)
        signOutBtn = view.findViewById(R.id.signOutBtn)

        signOutBtn.setOnClickListener {
        activity?.onBackPressed()

        }
    }



    private fun CallFacebook() {
        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().logInWithReadPermissions(
            this
            , Arrays.asList("user_birthday")
        )
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    Log.d("MainActivity", "Facebook token: " + loginResult.accessToken.token)
                    Log.d("MainActivity", "Facebook token: " + loginResult.accessToken.userId)
                    var imageUrl = "https://graph.facebook.com/${loginResult.accessToken.userId}/picture?type=large"
                    activity?.let { Glide.with(it).load(imageUrl).into(profileImage) }
                    progressBar.show()
                    val request = GraphRequest.newMeRequest(
                        loginResult.accessToken
                    ) { `object`, response ->
                        // Insert your code here

                        println(response.jsonArray)

                        println(`object`.toString())
                        val name = `object`.getString("name")
                        profileHeaderUsernameTv.text = name.toString()

                        progressBar.hide()

                        val topic = Gson().fromJson(`object`.toString(), Json4Kotlin_Base::class.java)
                        rv_event.adapter = MyEventAdapter(
                            topic.events.data,
                            activity as MainActivity
                        )
//

                        getWeatherForNewCity(
                            topic.events.data[0]
                                .timezone.substring
                                (topic.events.data[0].timezone.indexOf("/")+1))

                        Log.i("zcasfsaf", "" + Gson().toJson(topic))


                    }
                    val parameters = Bundle()
                    parameters.putString("fields", "id,name,events.limit(20){timezone,updated_time,description}")

                    request.parameters = parameters
                    request.executeAsync()


                }

                override fun onCancel() {
                    Log.d("MainActivity", "Facebook onCancel.")

                }

                override fun onError(error: FacebookException) {
                    Log.d("MainActivity", "Facebook onError.")
                    Log.d("MainActivity", "" + error.localizedMessage)
                    Log.d("MainActivity", "" + error.message)

                }


            })
    }

    private fun getWeatherForNewCity(city: String) {
        Log.d("LOGCAT_TAG", "Getting weather for new city")
        val params = RequestParams()
        params.put("q", city)
        params.put("appid", APP_ID)

        letsDoSomeNetworking(params)
    }


    // This is the actual networking code. Parameters are already configured.
    private fun letsDoSomeNetworking(params: RequestParams) {

        // AsyncHttpClient belongs to the loopj dependency.
        val client = AsyncHttpClient()

        // Making an HTTP GET request by providing a URL and the parameters.
        client.get(WEATHER_URL, params, object : JsonHttpResponseHandler() {

            override fun onSuccess(statusCode: Int, headers: Array<Header>?, response: JSONObject?) {

                Log.d("LOGCAT_TAG", "Success! JSON: " + response!!.toString())
                val weatherData = WeatherDataModel.fromJson(response)
                weatherData?.let { updateUI(it) }
            }

            override fun onFailure(statusCode: Int, headers: Array<Header>?, e: Throwable, response: JSONObject?) {

                Log.e("LOGCAT_TAG", "Fail $e")
                Toast.makeText(activity, "Request Failed", Toast.LENGTH_SHORT).show()

                Log.d("LOGCAT_TAG", "Status code $statusCode")
                Log.d("LOGCAT_TAG", "Here's what we got instead " + response!!.toString())
            }

        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }

    // Updates the information shown on screen.
    private fun updateUI(weather: WeatherDataModel) {
        mTemperatureLabel.text = weather.temperature
        mCityLabel.text = weather.city

        // Update the icon based on the resource id of the image in the drawable folder.
        val resourceID = resources.getIdentifier(weather.iconName, "drawable", activity?.packageName)
        mWeatherImage.setImageResource(resourceID)

    }



}