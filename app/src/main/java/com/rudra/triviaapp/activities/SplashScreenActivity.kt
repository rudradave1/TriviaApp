package com.rudra.triviaapp.activities

import android.content.*
import android.os.*
import android.view.*
import androidx.appcompat.app.*
import androidx.core.view.WindowInsetsCompat.Type
import com.rudra.triviaapp.*

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            //  System is running on Android 12 or higher
            window.insetsController?.hide(Type.statusBars())
        } else {
            //  System is running on Android 11 or lower
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        
        Handler()
            .postDelayed(
                {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                },
                1800
            )
    }
}