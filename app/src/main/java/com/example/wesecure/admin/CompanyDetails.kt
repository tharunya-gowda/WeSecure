package com.example.wesecure.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.wesecure.R

class CompanyDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comp_details)
        val b1 = findViewById<Button>(R.id.button2)
        val b2 = findViewById<Button>(R.id.button3)
        b1.setOnClickListener {
            val intent = Intent(this, AddCompany::class.java)
            startActivity(intent)
        }
        b2.setOnClickListener {
            val intent = Intent(this, ListOfCompanies::class.java)
            startActivity(intent)
        }
    }
}