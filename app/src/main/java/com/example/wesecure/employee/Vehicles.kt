package com.example.wesecure.employee

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.wesecure.main.ListOfVehicles
import com.example.wesecure.R

class Vehicles : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicles)
        val bd = findViewById<Button>(R.id.button2)
        val bl = findViewById<Button>(R.id.button3)
        bd.setOnClickListener {
            val intent = Intent(this, VehicleData::class.java)
            startActivity(intent)
        }
        bl.setOnClickListener {
            val intent = Intent(this, ListOfVehicles::class.java)
            startActivity(intent)
        }
    }
}