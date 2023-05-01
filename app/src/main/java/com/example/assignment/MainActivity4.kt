package com.example.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
    }

    fun clickNext3(view: View){
        val intent = Intent(this, MainActivity3::class.java)
        startActivity(intent)
        finish()
    }

    fun clickNext4(view: View){
        val intent = Intent(this, MainActivity5::class.java)
        startActivity(intent)
        finish()
    }
}