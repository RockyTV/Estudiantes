package com.example.encuestasestudiantesrodrigo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.encuestasestudiantesrodrigo.databinding.ActivityDetailBinding
import com.example.encuestasestudiantesrodrigo.databinding.ActivityFormBinding
import com.example.encuestasestudiantesrodrigo.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        Handler().postDelayed({
            val intent=Intent(this@SplashScreenActivity, FormActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}