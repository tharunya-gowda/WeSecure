package com.example.wesecure.admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wesecure.R
import com.example.wesecure.adapter.EmployeeListAdapter
import com.example.wesecure.model.EmployeeItem
import com.google.firebase.database.*


class ListOfEmployees : AppCompatActivity() {
    private lateinit var dbref: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<EmployeeItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_list)
        userRecyclerView = findViewById(R.id.recyclerView)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)
        userArrayList = arrayListOf()
        userArrayList.clear()
        getEmpData()


    }

    private fun getEmpData() {
        dbref = FirebaseDatabase.getInstance().getReference("employee")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (empSnapshot in snapshot.children) {
                        val user = empSnapshot.child("Details").getValue(EmployeeItem::class.java)
                        userArrayList.add(user!!)


                    }
                    userRecyclerView.adapter = EmployeeListAdapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}