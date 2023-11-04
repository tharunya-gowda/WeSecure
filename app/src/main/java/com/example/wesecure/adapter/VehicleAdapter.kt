package com.example.wesecure.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wesecure.R
import com.example.wesecure.model.VehicleItem
import com.squareup.picasso.Picasso


class VehicleAdapter(private val vList:ArrayList<VehicleItem>): RecyclerView.Adapter<VehicleAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.veh_item,parent,false)



        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem=vList[position]
        holder.d_name.text=currentitem.d_name
        holder.d_phone.text=currentitem.d_phone
        holder.v_plate.text=currentitem.v_plate
        Picasso.get().load(currentitem.img).into(holder.img)
    }

    override fun getItemCount(): Int {
        return vList.size
    }
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val d_name=itemView.findViewById<TextView>(R.id.d_name)
        val d_phone=itemView.findViewById<TextView>(R.id.d_phone)
        val v_plate=itemView.findViewById<TextView>(R.id.v_plate)
        val img=itemView.findViewById<ImageView>(R.id.img)
    }
}