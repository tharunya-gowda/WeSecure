package com.example.wesecure.employee


import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.wesecure.R
import com.example.wesecure.model.EmployeeItem
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_profile.*


class UserProfile : AppCompatActivity() {
    lateinit var list: ArrayList<EmployeeItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        list = arrayListOf()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        val uid = intent.getStringExtra("id")
        val dbref = FirebaseDatabase.getInstance().getReference("employee/" + uid.toString())
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val name = findViewById<TextView>(R.id.empname)
                val pic = findViewById<ImageView>(R.id.profile)
                val phone = findViewById<TextView>(R.id.empphone)
                val id = findViewById<TextView>(R.id.empid)
                val password = findViewById<TextView>(R.id.emppass)
                val type = findViewById<TextView>(R.id.emptype)
                if (snapshot.exists()) {
                    for (employee in snapshot.children) {
                        val test = employee.getValue(EmployeeItem::class.java)
                        list.add(test!!)
                    }
                    name.text = list[0].emp_name.toString()
                    phone.text = list[0].emp_phone.toString()
                    id.text = list[0].emp_id.toString()
                    password.text = list[0].emp_pass.toString()
                    type.text = list[0].emp_type.toString()
                    Picasso.get().load(list[0].emp_img).into(pic)
                    Handler().postDelayed({
                        progressBar3.visibility = View.GONE
                    }, 2300)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}