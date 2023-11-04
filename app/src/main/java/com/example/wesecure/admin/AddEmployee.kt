package com.example.wesecure.admin

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wesecure.R
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_add_emp.*
import kotlinx.android.synthetic.main.activity_vehdata.*
import java.io.IOException
import java.util.*

class AddEmployee : AppCompatActivity() {
    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_emp)
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        val empName = findViewById<EditText>(R.id.empname)
        val empId = findViewById<EditText>(R.id.empid)
        val empPhone = findViewById<EditText>(R.id.empphone)
        val empPass = findViewById<EditText>(R.id.emppass)
        val empType = findViewById<EditText>(R.id.emptype)
        val upload = findViewById<Button>(R.id.upload)
        val submit = findViewById<Button>(R.id.submitv)
        upload.setOnClickListener {
            launchGallery()
        }
        submit.setOnClickListener {
            val dbref: DatabaseReference =
                FirebaseDatabase.getInstance().getReference("employee").child(empId.text.toString())
                    .child("Details")
            dbref.child("emp_name").setValue(empName.text.toString())
            dbref.child("emp_id").setValue(empId.text.toString())
            dbref.child("emp_phone").setValue(empPhone.text.toString())
            dbref.child("emp_pass").setValue(empPass.text.toString())
            dbref.child("emp_type").setValue(empType.text.toString())
            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, EmployeeDetails::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                return
            }
            filePath = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                imguploaded.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        uploadImage()
    }

    private fun launchGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    private fun uploadImage() {
        val dbref: DatabaseReference =
            FirebaseDatabase.getInstance().getReference("employee").child(empid.text.toString())
                .child("Details")
        if (filePath != null) {
            val ref = storageReference?.child("employee/" + UUID.randomUUID().toString())
            val uploadTask = ref?.putFile(filePath!!)
            uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                return@Continuation ref.downloadUrl
            })?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    task.result
                    /*addUploadRecordToDb(downloadUri.toString())*/
                    ref.putFile(filePath!!).addOnCompleteListener { work ->
                        if (work.isSuccessful) {
                            ref.downloadUrl.addOnSuccessListener { uri ->
                                val url = uri.toString()
                                /*dbref.child("emp_image").setValue(URL)*/
                                dbref.child("emp_img").setValue(url)
                            }
                        }
                    }
                }
            }
        }
    }
}

