package com.example.ch18_network.recycler

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ch18_network.databinding.ItemMainBinding
import com.example.ch18_network.model.ItemModel


class MyViewHolder(val binding : ItemMainBinding) : RecyclerView.ViewHolder(binding.root)

class MyAdapter (val context: Context, val datas : MutableList<ItemModel>?)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding

        val model = datas!![position]
        binding.itemTitle.text = model.title
        binding.itemDesc.text = model.description
        binding.itemTime.text = "${model.author} At ${model.publishedAt}"
        Glide.with(context)
            .load(model.urlToImage)
            .into(binding.itemImage)
    }

}
