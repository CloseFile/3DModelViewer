package com.ctapk.poly.features.sarch.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ctapk.poly.R
import com.squareup.picasso.Picasso
import com.ctapk.poly.model.Model3d

class Modle3dAdapter(private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<Modle3dAdapter.MyViewHolder>(),
    View.OnClickListener {
    private var modelsList: ArrayList<Model3d>? = null
    private val picasso = Picasso.get()

   fun setData(model3ds: ArrayList<Model3d>?) {
        modelsList = model3ds
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_model, parent, false))


    override fun getItemCount() = modelsList?.size ?: 0


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val imageUrl = modelsList?.get(position)?.getBiggestThumbnailUrl()
        if (imageUrl != null) {
            picasso.load(imageUrl).into(holder.image)
        }
        holder.name.text = modelsList?.get(position)?.name
        holder.description.text = modelsList?.get(position)?.name
        holder.itemView.tag = position
        holder.itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val position = v?.tag as Int
        onItemClickListener.onItemClicked(position, modelsList?.get(position))
    }

    class MyViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {
        val image: ImageView = containerView.findViewById(R.id.image)
        val name: TextView = containerView.findViewById(R.id.name)
        val description: TextView = containerView.findViewById(R.id.description)
    }

    interface OnItemClickListener {
        fun onItemClicked(position: Int, model3d: Model3d?)
    }
}