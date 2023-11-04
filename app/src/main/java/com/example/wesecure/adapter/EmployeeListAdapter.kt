package com.example.wesecure.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wesecure.R
import com.example.wesecure.model.EmployeeItem
import com.squareup.picasso.Picasso

class EmployeeListAdapter(private val vList: ArrayList<EmployeeItem>) :
    RecyclerView.Adapter<EmployeeListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.emp_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = vList[position]
        holder.empName.text = currentitem.emp_name
        holder.empId.text = currentitem.emp_id
        holder.empPhone.text = currentitem.emp_phone
        holder.empPass.text = currentitem.emp_pass
        holder.empType.text = currentitem.emp_type
        Picasso.get().load(currentitem.emp_img.toString()).into(holder.empImg)
    }

    override fun getItemCount(): Int {
        return vList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val empName: TextView = itemView.findViewById(R.id.empname)
        val empId: TextView = itemView.findViewById(R.id.empid)
        val empPhone: TextView = itemView.findViewById(R.id.empphone)
        val empPass: TextView = itemView.findViewById(R.id.emppass)
        val empType: TextView = itemView.findViewById(R.id.emptype)
        val empImg: ImageView = itemView.findViewById(R.id.empimg)
    }
}