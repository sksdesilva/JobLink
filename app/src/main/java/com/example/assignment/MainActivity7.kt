package com.example.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.assignment.databinding.ActivityMain7Binding
import com.google.firebase.database.*

class MainActivity7 : AppCompatActivity() {

    private lateinit var binding: ActivityMain7Binding
    private lateinit var database: DatabaseReference
    private lateinit var companyName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain7Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the intent from MainActivity3
        val intent = intent
        companyName = intent.getStringExtra("companyName")!!

        // Set the existing company details in the form
        database = FirebaseDatabase.getInstance().getReference("Employer")
        database.child(companyName).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val employer = snapshot.getValue(Employer::class.java)
                    binding.companyName.setText(employer?.companyName)
                    binding.companyAddress.setText(employer?.address)
                    binding.companyEmail.setText(employer?.email)
                    binding.companyContact.setText(employer?.contactNumber)
                    binding.passwordER.setText(employer?.password)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@MainActivity7,
                    "Error: ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        binding.signupE.text = "Update Profile"
        binding.signupE.setOnClickListener{

            val address = binding.companyAddress.text.toString()
            val email = binding.companyEmail.text.toString()
            val contactNumber = binding.companyContact.text.toString()
            val password = binding.passwordER.text.toString()

            if (address.isEmpty()) {
                Toast.makeText(this, "Address is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (contactNumber.isEmpty()) {
                Toast.makeText(this, "Contact number is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.length < 6) {
                Toast.makeText(this, "Password length should be at least 6 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Update the company profile
            val employer = Employer(binding.companyName.text.toString(), address, email, contactNumber, password)
            database.child(companyName).setValue(employer).addOnSuccessListener {
                Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity3::class.java)
                intent.putExtra("companyName", email)
                startActivity(intent)
                finish()
            }.addOnFailureListener{
                Toast.makeText(this, "Profile update failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
