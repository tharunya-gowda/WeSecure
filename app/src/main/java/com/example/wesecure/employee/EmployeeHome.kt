package com.example.wesecure.employee

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.wesecure.*
import com.example.wesecure.main.Login

class EmployeeHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val qrs = findViewById<CardView>(R.id.qr)
        val prof = findViewById<CardView>(R.id.pro)
        val visit = findViewById<CardView>(R.id.vis)
        val vehicle = findViewById<CardView>(R.id.veh)
        val holiday = findViewById<CardView>(R.id.lev)
        val logout = findViewById<CardView>(R.id.log)
        val id = intent.getStringExtra("id")
        qrs.setOnClickListener {
            val intent = Intent(this, QRScanner::class.java)
            startActivity(intent)
        }
        prof.setOnClickListener {
            val intent = Intent(this, UserProfile::class.java)
            intent.putExtra("id", id.toString())
            startActivity(intent)
        }
        visit.setOnClickListener {
            val intent = Intent(this, Visitors::class.java)
            startActivity(intent)
        }
        vehicle.setOnClickListener {
            val intent = Intent(this, Vehicles::class.java)
            startActivity(intent)
        }
        holiday.setOnClickListener {
            val intent = Intent(this, Vacations::class.java)
            intent.putExtra("id", id.toString())
            startActivity(intent)
        }
        logout.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}