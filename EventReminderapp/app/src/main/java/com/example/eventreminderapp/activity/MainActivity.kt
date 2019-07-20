package com.example.eventreminderapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.eventreminderapp.R
import com.example.eventreminderapp.fragment.FaceBookFragment
import com.example.eventreminderapp.fragment.GooGleFragment
import com.example.eventreminderapp.helper.Constants.FACE_BOOK
import com.example.eventreminderapp.helper.Constants.GOOGLE
import com.example.eventreminderapp.helper.Constants.TYPE
import com.facebook.AccessToken
import com.facebook.login.LoginManager

class MainActivity : AppCompatActivity() {
    private lateinit var type: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = intent
        type = intent.getStringExtra(TYPE)

        LoginType()
    }

    private fun LoginType() {

        when (type) {
            FACE_BOOK -> loadFragment(FaceBookFragment(), 0)
            GOOGLE -> loadFragment(GooGleFragment(), 0)

        }
    }

    fun loadFragment(fragment: Fragment?, itemId: Int): Boolean {
        //switching fragment
        if (fragment != null) {
            val bundle = Bundle()
            bundle.putInt("itemId", itemId)
            //            Log.i("cat_id_adapter", String.valueOf(itemId));

            fragment.arguments = bundle
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.details_main_nav_host, fragment)
                .commit()
            return true
        }
        return false
    }

    override fun onBackPressed() {
        if (AccessToken.getCurrentAccessToken() != null) {

            LoginManager.getInstance().logOut()
            finish()


        } else {
            super.onBackPressed()

        }
    }
}

