package com.example.karunada_vanya

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class WildlifeAdapter(
    private val context: Context,
    private var itemList: List<WildlifeItem>
) : RecyclerView.Adapter<WildlifeAdapter.ViewHolder>() {

    fun updateList(newList: List<WildlifeItem>) {
        itemList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_wildlife_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.txtName.text = item.name
        holder.txtCategory.text = item.category
        holder.txtFact.text = item.fact
        holder.txtDescription.text = item.description
        Glide.with(context)
            .load(item.imageResource)
            .centerCrop()
            .into(holder.imgAnimal)
    }

    override fun getItemCount() = itemList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgAnimal: ImageView = itemView.findViewById(R.id.imgAnimal)
        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtCategory: TextView = itemView.findViewById(R.id.txtCategory)
        val txtFact: TextView = itemView.findViewById(R.id.txtFact)
        val txtDescription: TextView = itemView.findViewById(R.id.txtDescription)
    }
}