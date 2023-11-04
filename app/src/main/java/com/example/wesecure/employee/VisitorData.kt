package com.example.wesecure.employee

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wesecure.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class VisitorData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visdata)
        val visitorNumber = findViewById<EditText>(R.id.visnumber)
        val visitorName = findViewById<EditText>(R.id.visname)
        val visitorPhone = findViewById<EditText>(R.id.visphone)
        val visitorPurpose = findViewById<EditText>(R.id.vispurpose)
        val submit = findViewById<Button>(R.id.submit)
        submit.setOnClickListener {
            val dbref: DatabaseReference = FirebaseDatabase.getInstance().getReference("visitors")
                .child(visitorNumber.text.toString())
            dbref.child("vis_number").setValue(visitorNumber.text.toString())
            dbref.child("vis_name").setValue(visitorName.text.toString())
            dbref.child("vis_phone").setValue(visitorPhone.text.toString())
            dbref.child("vis_purpose").setValue(visitorPurpose.text.toString())

            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, Visitors::class.java))
        }

    }
}