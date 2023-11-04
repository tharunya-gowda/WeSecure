package com.example.wesecure.employee

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.wesecure.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_vehdata.*
import java.io.ByteArrayOutputStream

class VehicleData : AppCompatActivity() {
    private lateinit var storage: StorageReference
    private var bytes: ByteArrayOutputStream = ByteArrayOutputStream()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehdata)
        storage = FirebaseStorage.getInstance().getReference("vehicleimage")
        val driverName = findViewById<EditText>(R.id.drivername)
        val driverPhone = findViewById<EditText>(R.id.driverphone)
        val vehiclePlate = findViewById<EditText>(R.id.vehicleplate)
        val capture = findViewById<Button>(R.id.capture)
        val submit = findViewById<Button>(R.id.submitv)




        capture.isEnabled = false
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 111)
        } else
            capture.isEnabled = true

        capture.setOnClickListener {
            val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i, 101)
        }

        submit.setOnClickListener {
            val dbref: DatabaseReference = FirebaseDatabase.getInstance().getReference("vehicles")
                .child(vehiclePlate.text.toString())
            dbref.child("d_name").setValue(driverName.text.toString())
            dbref.child("d_phone").setValue(driverPhone.text.toString())
            dbref.child("v_plate").setValue(vehiclePlate.text.toString())

            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, Vehicles::class.java))
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101) {
            val pic: Bitmap? = data?.getParcelableExtra("data")
            imagecaptured.setImageBitmap(pic)
            pic?.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
            val bb = bytes.toByteArray()
            uploadtoFirebase(bb)

        }
    }

    private fun uploadtoFirebase(bb: ByteArray) {
        val store = storage.child(vehicleplate.text.toString() + ".jpg")
        val uploadTask = store.putBytes(bb)
        uploadTask.addOnSuccessListener {
            store.downloadUrl.addOnSuccessListener {
                val url = it.toString()
                val imageref = FirebaseDatabase.getInstance()
                    .getReference("vehicles/" + vehicleplate.text.toString())
                imageref.child("img").setValue(url)
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            val img = findViewById<Button>(R.id.capture)
            img.isEnabled = true
        }
    }


}
