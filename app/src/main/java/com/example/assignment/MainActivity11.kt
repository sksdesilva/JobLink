package com.example.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity11 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main11)

        val buttonLogin: Button = findViewById(R.id.buttonLogin)
        buttonLogin.setOnClickListener {
            val usernameEditText: EditText = findViewById(R.id.editTextUsername)
            val passwordEditText: EditText = findViewById(R.id.editTextPassword)

            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (isInputValid(username, password)) {
                // Start the MainActivity12
                val intent = Intent(this, MainActivity12::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isInputValid(username: String, password: String): Boolean {
        return username.isNotEmpty() && password.isNotEmpty()
    }
}