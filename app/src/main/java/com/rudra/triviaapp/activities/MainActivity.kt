package com.rudra.triviaapp.activities

import android.os.*
import androidx.appcompat.app.*
import com.rudra.triviaapp.*
import com.rudra.triviaapp.fragments.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.root_container, NameFragment())
                .commitAllowingStateLoss()
        }
    }
}