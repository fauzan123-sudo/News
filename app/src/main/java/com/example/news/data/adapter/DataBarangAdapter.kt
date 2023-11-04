package com.example.news.data.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.news.data.model.Item
import com.example.news.databinding.ItemBinding

class DataBarangAdapter() : RecyclerView.Adapter<DataBarangAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: Item) {
            val no = data.no
            val code = data.kode
            val productName = data.namaBarang
            val sales = data.sales.toString()
            val seller = data.penjualan.toString()
            val productReturn = data.pengembalianBarang.toString()
            val stock = data.persediaan.toString()

            binding.tvUser.text = "$no . $code . $productName . $sales . $seller $productReturn $stock"
        }
    }


    private val differCallback = object : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }


    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }


}