package com.example.wesecure.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wesecure.R
import com.example.wesecure.adapter.VisitorAdapter
import com.example.wesecure.model.VisitorItem
import com.google.firebase.database.*

class ListOfVisitors : AppCompatActivity() {
    private lateinit var dbref: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<VisitorItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vislist)
        userRecyclerView = findViewById(R.id.recyclerView)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)
        userArrayList = arrayListOf()
        getVehData()
    }

    private fun getVehData() {
        dbref = FirebaseDatabase.getInstance().getReference("visitors")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (vehSnapshot in snapshot.children) {
                        val user = vehSnapshot.getValue(VisitorItem::class.java)
                        userArrayList.add(user!!)
                    }
                    userRecyclerView.adapter = VisitorAdapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}