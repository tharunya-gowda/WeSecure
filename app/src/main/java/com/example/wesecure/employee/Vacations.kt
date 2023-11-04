package com.example.wesecure.employee

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wesecure.R
import com.google.firebase.database.*

class Vacations : AppCompatActivity() {
    var data: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vacations)
        val decline = findViewById<TextView>(R.id.decline)
        val id = intent.getStringExtra("id")
        val dbRef = FirebaseDatabase.getInstance().getReference("vacations/$id")
        val status = findViewById<TextView>(R.id.status)

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    for (item in snapshot.children) {
                        if (item.key == "status") {
                            val vac = item.getValue(String::class.java)
                            if (vac == "approved") {
                                status.setTextColor(resources.getColor(R.color.green))
                            } else {
                                status.setTextColor(resources.getColor(R.color.red))
                            }
                            status.text = vac.toString()
                        }
                        if (item.key == "declined") {
                            val dec = item.getValue(String::class.java)
                            decline.text = dec.toString()
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        val from = findViewById<EditText>(R.id.from)
        val until = findViewById<EditText>(R.id.until)
        val purpose = findViewById<EditText>(R.id.purpose)
        val leaveSubmit = findViewById<Button>(R.id.leave_submit)



        leaveSubmit.setOnClickListener {
            val dbref: DatabaseReference =
                FirebaseDatabase.getInstance().getReference("vacations").child(id.toString())
            dbref.child("from").setValue(from.text.toString())
            dbref.child("until").setValue(until.text.toString())
            dbref.child("purpose").setValue(purpose.text.toString())
            dbref.child("status").setValue("pending")
            dbref.child("id").setValue(id)
            Toast.makeText(
                this,
                "Leave request sent. Please wait until the admin responds",
                Toast.LENGTH_SHORT
            ).show()
            //startActivity(Intent(this, Vacations::class.java))

        }


    }
}







