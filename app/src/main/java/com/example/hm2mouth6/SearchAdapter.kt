package com.example.hm2mouth6

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hm2mouth6.databinding.ItemSearchBinding

class SearchAdapter (private val list: ArrayList<String>, val clickListener: (String)->Unit):
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {


    inner class SearchViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(tModel: String) {
            binding.item.text = "#$tModel"
            itemView.setOnClickListener {
                clickListener(tModel)
            }

        }
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
            return SearchViewHolder(
                ItemSearchBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
        }

        override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
            holder.onBind(list[position])
        }

        override fun getItemCount(): Int {
            return list.size
        }

}