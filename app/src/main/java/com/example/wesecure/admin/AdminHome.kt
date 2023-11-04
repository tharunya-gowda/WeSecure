package com.example.wesecure.admin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.wesecure.*
import com.example.wesecure.main.ListOfVehicles
import com.example.wesecure.main.ListOfVisitors
import com.example.wesecure.main.Login

class AdminHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home)
        val qrs = findViewById<CardView>(R.id.qr)
        val prof = findViewById<CardView>(R.id.pro)
        val visit = findViewById<CardView>(R.id.vis)
        val vehicle = findViewById<CardView>(R.id.veh)
        val holiday = findViewById<CardView>(R.id.lev)
        val logout = findViewById<CardView>(R.id.log)
        qrs.setOnClickListener {
            val intent = Intent(this, ListOfVisitors::class.java)
            startActivity(intent)
        }
        prof.setOnClickListener {
            val intent = Intent(this, ListOfVehicles::class.java)
            startActivity(intent)
        }
        visit.setOnClickListener {
            val intent = Intent(this, EmployeeDetails::class.java)
            startActivity(intent)
        }
        vehicle.setOnClickListener {
            val intent = Intent(this, CompanyDetails::class.java)
            startActivity(intent)
        }
        holiday.setOnClickListener {
            val intent = Intent(this, ListOfVacationRequests::class.java)
            startActivity(intent)
        }
        logout.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}