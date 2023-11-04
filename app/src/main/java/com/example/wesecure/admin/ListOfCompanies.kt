package com.example.wesecure.admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wesecure.R
import com.example.wesecure.adapter.CompanyListAdapter
import com.example.wesecure.model.CompanyItem
import com.google.firebase.database.*

class ListOfCompanies : AppCompatActivity() {
    private lateinit var dbref: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<CompanyItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comp_list)
        userRecyclerView = findViewById(R.id.recyclerView)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)
        userArrayList = arrayListOf()
        getCompData()
    }

    private fun getCompData() {
        dbref = FirebaseDatabase.getInstance().getReference("company")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (compSnapshot in snapshot.children) {
                        val company = compSnapshot.getValue(CompanyItem::class.java)
                        userArrayList.add(company!!)
                    }
                    userRecyclerView.adapter = CompanyListAdapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}