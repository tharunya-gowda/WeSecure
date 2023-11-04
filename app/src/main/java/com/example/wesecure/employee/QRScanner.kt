package com.example.wesecure.employee

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wesecure.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.zxing.integration.android.IntentIntegrator
import java.text.DecimalFormat


class QRScanner : AppCompatActivity() {
    private var uid: String? = null
    private var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrscanner)
        val btn = findViewById<Button>(R.id.scanner)
        textView = findViewById(R.id.text_view)
        val dbref = FirebaseDatabase.getInstance().getReference("Time/$uid")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    textView?.text = snapshot.child("running").value.toString()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        btn.setOnClickListener {
            val scan = IntentIntegrator(this)
            scan.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            scan.setBeepEnabled(true)
            scan.initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val btn = findViewById<Button>(R.id.scanner)
        if (resultCode == Activity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
                    uid = FirebaseAuth.getInstance().currentUser?.uid.toString()
                    object : CountDownTimer(3600000, 1000) {
                        override fun onTick(long: Long) {
                            btn.isEnabled = false
                            val sDuration = DecimalFormat("00")
                            val hour = (long / 3600000) % 24
                            val min = (long / 60000) % 60
                            val sec = (long / 1000) % 60
                            val time =
                                sDuration.format(hour) + ":" + sDuration.format(min) + ":" + sDuration.format(
                                    sec
                                )
                            FirebaseDatabase.getInstance().getReference("Time/$uid/running")
                                .setValue(time)

                        }

                        override fun onFinish() {
                            FirebaseDatabase.getInstance().getReference("employee/100/Details")
                                .child("workinghours").setValue("1")
                            FirebaseDatabase.getInstance().getReference("Time/$uid/running")
                                .setValue("00:00:00")
                            btn.isEnabled = true
                            Toast.makeText(
                                applicationContext,
                                "countdown has ended",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }.start()

                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }

    }

}
