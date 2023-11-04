package com.example.wesecure.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wesecure.R
import com.example.wesecure.adapter.VehicleAdapter
import com.example.wesecure.model.VehicleItem
import com.google.firebase.database.*

class ListOfVehicles : AppCompatActivity() {
    private lateinit var dbref: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<VehicleItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehlist)
        userRecyclerView = findViewById(R.id.recyclerView)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)
        userArrayList = arrayListOf()
        getVehData()
    }

    private fun getVehData() {
        dbref = FirebaseDatabase.getInstance().getReference("vehicles")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (vehSnapshot in snapshot.children) {
                        val user = vehSnapshot.getValue(VehicleItem::class.java)
                        userArrayList.add(user!!)
                    }
                    userRecyclerView.adapter = VehicleAdapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}