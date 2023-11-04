package com.example.wesecure.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wesecure.R
import com.example.wesecure.admin.AdminHome
import com.example.wesecure.employee.EmployeeHome
import com.example.wesecure.model.EmployeeItem
import com.google.firebase.database.*


class Login : AppCompatActivity() {
    lateinit var list: ArrayList<EmployeeItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
        val userId = findViewById<EditText>(R.id.uid)
        val userPassword = findViewById<EditText>(R.id.upassword)
        val b1 = findViewById<Button>(R.id.button)
        b1.setOnClickListener {
            list = arrayListOf()
            val ref = FirebaseDatabase.getInstance().reference.child("employee")
                .child(userId.text.toString()).orderByChild("emp_pass")
                .equalTo(userPassword.text.toString())
            ref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (employee in dataSnapshot.children) {
                            val emp = employee.getValue(EmployeeItem::class.java)
                            list.add(emp!!)
                        }
                        val emp = list[0]
                        val type = emp.emp_type
                        //val id=emp.emp_id
                        val employeeId = userId.text.toString()
                        if (type == "Admin" || type == "admin") {
                            val intent = Intent(this@Login, AdminHome::class.java)
                            intent.putExtra("id", employeeId)
                            startActivity(intent)
                        } else {
                            val intent = Intent(this@Login, EmployeeHome::class.java)
                            intent.putExtra("id", employeeId)
                            startActivity(intent)
                        }


                    } else
                        Toast.makeText(this@Login, "Incorrect Password", Toast.LENGTH_SHORT).show()


                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Toast.makeText(this@Login, "No Data Found", Toast.LENGTH_SHORT).show()

                }
            })

        }


    }

}





