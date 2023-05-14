package com.example.assignment


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.assignment.databinding.ActivityMain2Binding
import com.example.assignment.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding : ActivityMain2Binding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupE.setOnClickListener{

            val companyName = binding.companyName.text.toString()
            val address = binding.companyAddress.text.toString()
            val email = binding.companyEmail.text.toString()
            val contactNumber = binding.companyContact.text.toString()
            val password = binding.passwordER.text.toString()

            if (companyName.isEmpty()) {
                Toast.makeText(this, "Compnay name is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

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
                Toast.makeText(this, "password length should be exceed 6 charectors", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }





            if (address.isEmpty()) {
                Toast.makeText(this, "Address is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }







            database = FirebaseDatabase.getInstance().getReference("Employer")
            val employer = Employer(companyName,address,email, contactNumber, password);
            database.child(email).setValue(employer).addOnSuccessListener {
                binding.companyName.text.clear()
                binding.companyAddress.text.clear()
                binding.companyEmail.text.clear()
                binding.companyContact.text.clear()
                binding.passwordER.text.clear()

                Toast.makeText(this,"sucessfully created new company profile",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }.addOnFailureListener{
                Toast.makeText(this,"new company added unseccefully",Toast.LENGTH_SHORT).show()
            }
        }

    }

//    fun clickNext2(view: View){
//        val intent = Intent(this, MainActivity4::class.java)
//        startActivity(intent)
//        finish()
//    }
//update file


}