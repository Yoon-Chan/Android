package com.example.test12

import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ch12_material.databinding.FragmentOneBinding
import com.example.ch12_material.databinding.ItemRecyclerviewBinding


class OneFragment : Fragment() {
    lateinit var binding: FragmentOneBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneBinding.inflate(inflater, container, false)

        val data = mutableListOf<String>()
        for(i in 1..10) {
            data.add("Item $i")
        }

        //리사이클러 뷰에 LayoutManager, Adapter, ItemDecoration 적용
        val layoutManager = LinearLayoutManager(activity)
        binding.recyclerview.layoutManager = layoutManager
        binding.recyclerview.adapter = MyAdapter(data)
        binding.recyclerview.addItemDecoration(MyDecoration(activity as Context))



        return binding.root
    }



    // 항목 뷰를 가진 역할
    class MyViewHolder(val binding : ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)

    // 항목 구성자. 어댑터
    class MyAdapter(val datas : MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        override fun getItemCount(): Int {
            return datas.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val binding = (holder as MyViewHolder).binding

            binding.itemData.text = datas[position]

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            MyViewHolder(
                ItemRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false))


    }

    //리사이클러 뷰 꾸미기
    class MyDecoration(val context : Context) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            val index = parent.getChildAdapterPosition(view) + 1

            if(index % 3 == 0)
                outRect.set(10,10,10,60)
            else
                outRect.set(10,10,10,0)

            view.setBackgroundColor(Color.parseColor("#28A0FF"))
            ViewCompat.setElevation(view, 20.0f)

        }

    }

}