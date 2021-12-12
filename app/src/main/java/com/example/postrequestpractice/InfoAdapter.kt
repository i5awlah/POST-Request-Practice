package com.example.postrequestpractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postrequestpractice.databinding.ItemRowBinding

class InfoAdapter(val persons: ArrayList<PersonItem>) : RecyclerView.Adapter<InfoAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var person = persons[position]
        holder.binding.apply {
            tvName.text = person.name
            tvLocation.text = person.location
        }
    }

    override fun getItemCount() = persons.size
}