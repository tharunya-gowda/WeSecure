package com.example.wesecure.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wesecure.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddCompany : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_company)
        val compName = findViewById<EditText>(R.id.compname)
        val compPhone = findViewById<EditText>(R.id.compphone)
        val compAddress = findViewById<EditText>(R.id.compaddress)
        val submit = findViewById<Button>(R.id.submit)
        submit.setOnClickListener {
            val dbref: DatabaseReference = FirebaseDatabase.getInstance().getReference("company")
                .child(compName.text.toString())
            dbref.child("comp_name").setValue(compName.text.toString())
            dbref.child("comp_phone").setValue(compPhone.text.toString())
            dbref.child("comp_address").setValue(compAddress.text.toString())
            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, CompanyDetails::class.java))
        }
    }
}