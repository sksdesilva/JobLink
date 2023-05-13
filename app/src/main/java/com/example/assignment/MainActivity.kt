package com.example.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.assignment.databinding.ActivityMain2Binding
import com.example.assignment.databinding.ActivityMainBinding
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {

        private lateinit var binding: ActivityMainBinding
        private lateinit var database: DatabaseReference

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            database = FirebaseDatabase.getInstance().getReference("Employer")

            binding.textView9.setOnClickListener {
                val intent = Intent(this@MainActivity, MainActivity2::class.java)
                startActivity(intent)
            }

            binding.buttonE.setOnClickListener {
                val email = binding.emailE.text.toString()
                val password = binding.passwordE.text.toString()

                if (email.isEmpty()) {
                    Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (password.isEmpty()) {
                    Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                database.child(email).addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            val employer = snapshot.getValue(Employer::class.java)
                            if (employer != null && employer.password == password) {
                                Toast.makeText(
                                    this@MainActivity,
                                    "Login Successful!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val intent = Intent(this@MainActivity, MainActivity3::class.java)
                                intent.putExtra("companyName", email)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(
                                    this@MainActivity,
                                    "Invalid email or password",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "Invalid email or password",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(
                            this@MainActivity,
                            "Error: ${error.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            }
        }

}
