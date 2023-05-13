package com.example.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        val imageView6: ImageView = findViewById(R.id.imageView6)
        imageView6.setOnClickListener {
            val intent = Intent(this@MainActivity5, MainActivity::class.java)
            startActivity(intent)
        }

        val imageView5: ImageView = findViewById(R.id.imageView5)
        imageView5.setOnClickListener {
            val intent = Intent(this@MainActivity5, MainActivity11::class.java)
            startActivity(intent)
        }
    }
}