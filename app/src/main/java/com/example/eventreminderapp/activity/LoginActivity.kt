package com.example.eventreminderapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import com.example.eventreminderapp.R
import com.example.eventreminderapp.helper.Constants.FACE_BOOK
import com.example.eventreminderapp.helper.Constants.GOOGLE
import com.example.eventreminderapp.helper.Constants.TYPE
import com.example.eventreminderapp.helper.hide
import com.example.eventreminderapp.helper.show
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.loader.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//        updateUI(false)
        val intent = Intent(this@LoginActivity, MainActivity::class.java)

        facebookBtn.setOnClickListener {
            intent.putExtra(TYPE, FACE_BOOK)
            startActivity(intent)
        }
        googleBtn.setOnClickListener {
            intent.putExtra(TYPE, GOOGLE)
            startActivity(intent)

        }
    }



}