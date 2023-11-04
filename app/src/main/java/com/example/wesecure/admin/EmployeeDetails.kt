package com.example.wesecure.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.wesecure.R

class EmployeeDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emp_details)
        val b1 = findViewById<Button>(R.id.button2)
        val b2 = findViewById<Button>(R.id.button3)
        b1.setOnClickListener {
            val intent = Intent(this, AddEmployee::class.java)
            startActivity(intent)
        }
        b2.setOnClickListener {
            val intent = Intent(this, ListOfEmployees::class.java)
            startActivity(intent)
        }
    }
}