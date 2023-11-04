package com.example.wesecure.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wesecure.R
import com.example.wesecure.model.VacationItem
import com.google.firebase.database.FirebaseDatabase

class VacationRequestsAdapter(private val cList: ArrayList<VacationItem>) :
    RecyclerView.Adapter<VacationRequestsAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.vacation_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val vacItem = cList[position]
        holder.from.text = vacItem.from
        holder.until.text = vacItem.until
        holder.reason.text = vacItem.purpose
        holder.status.text = vacItem.status
        holder.id.text = vacItem.id
        val empId = vacItem.id.toString()
        holder.declineText.isEnabled = false
        holder.decline.isEnabled = false
        holder.sendDecline.isEnabled = false
        holder.approved.setOnClickListener {
            val dbref = FirebaseDatabase.getInstance().getReference("vacations/$empId")
            dbref.child("status").setValue("approved")
        }
        holder.declined.setOnClickListener {
            val dbref = FirebaseDatabase.getInstance().getReference("vacations/$empId")
            dbref.child("status").setValue("declined")
            holder.declineText.isEnabled = true
            holder.decline.isEnabled = true
            holder.sendDecline.isEnabled = true
        }
        holder.sendDecline.setOnClickListener {
            val dbref = FirebaseDatabase.getInstance().getReference("vacations/$empId")
            dbref.child("status").setValue(holder.decline.text.toString())
        }

    }

    override fun getItemCount(): Int {
        return cList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.id)
        val from: TextView = itemView.findViewById(R.id.from)
        val until: TextView = itemView.findViewById(R.id.until)
        val reason: TextView = itemView.findViewById(R.id.reason)
        val status: TextView = itemView.findViewById(R.id.status)
        val approved: Button = itemView.findViewById(R.id.vacationok)
        val declined: Button = itemView.findViewById(R.id.vacationnotok)
        val declineText: TextView = itemView.findViewById(R.id.declinetext)
        val decline: TextView = itemView.findViewById(R.id.decline)
        val sendDecline: ImageView = itemView.findViewById(R.id.senddecline)
    }
}