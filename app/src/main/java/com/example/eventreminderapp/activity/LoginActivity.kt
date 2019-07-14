package com.example.eventreminderapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.eventreminderapp.R
import com.example.eventreminderapp.helper.Constants.FACE_BOOK
import com.example.eventreminderapp.helper.Constants.TYPE
import com.facebook.CallbackManager
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.putExtra(TYPE, FACE_BOOK)
        facebookBtn.setOnClickListener {
        startActivity(intent)
        }
    }
}
