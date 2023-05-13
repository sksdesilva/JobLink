package com.example.assignment

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.assignment.databinding.ActivityMain3Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity3 : AppCompatActivity() {

    private lateinit var binding: ActivityMain3Binding
    private lateinit var database: DatabaseReference
    private lateinit var companyName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the company name from MainActivity2
        val intent = intent
        companyName = intent.getStringExtra("companyName")!!

        val button: Button = findViewById(R.id.editProfileButton)
        button.setOnClickListener {
            val intent = Intent(this@MainActivity3, MainActivity7::class.java)
            intent.putExtra("companyName", companyName)
            startActivity(intent)
        }

        supportActionBar?.title = companyName

        binding.textView.text = "Welcome " + companyName


        // Set the company name as the title of the activity
        supportActionBar?.title = companyName

        // Initialize the Realtime Database reference
        database = FirebaseDatabase.getInstance().getReference("Employer")

        // Delete the user's profile
        binding.deleteProfileButton.setOnClickListener {
            // Show a confirmation dialog
            AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Are you sure you want to delete your profile?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                    database.child(companyName).removeValue().addOnSuccessListener {
                        Toast.makeText(this, "Profile deleted successfully", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity5::class.java)
                        startActivity(intent)
                        finish()
                    }.addOnFailureListener {
                        Toast.makeText(this, "Failed to delete profile", Toast.LENGTH_SHORT).show()
                    }
                })
                .setNegativeButton("No", null)
                .show()
        }
    }
    }
