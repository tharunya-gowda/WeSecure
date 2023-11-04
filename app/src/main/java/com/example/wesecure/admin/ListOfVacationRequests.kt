package com.example.wesecure.admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wesecure.R
import com.example.wesecure.adapter.VacationRequestsAdapter
import com.example.wesecure.model.VacationItem
import com.google.firebase.database.*

class ListOfVacationRequests : AppCompatActivity() {
    private lateinit var dbref: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<VacationItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vrequests)
        userRecyclerView = findViewById(R.id.vacationlist)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)
        userArrayList = arrayListOf()
        getVacData()
    }

    private fun getVacData() {
        dbref = FirebaseDatabase.getInstance().getReference("vacations")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userArrayList.clear()
                if (snapshot.exists()) {
                    for (vacSnapshot in snapshot.children) {
                        val company = vacSnapshot.getValue(VacationItem::class.java)
                        userArrayList.add(company!!)
                    }
                    userRecyclerView.adapter = VacationRequestsAdapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}