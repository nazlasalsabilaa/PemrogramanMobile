package com.nazlasalsabila.global_icons

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nazlasalsabila.global_icons.databinding.ItemRowIconBinding

class ListIconAdapter(private val listIcon: ArrayList<Icon>) :
    RecyclerView.Adapter<ListIconAdapter.ListViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(callback: OnItemClickCallback) {
        this.onItemClickCallback = callback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Icon)
    }

    inner class ListViewHolder(val binding: ItemRowIconBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowIconBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listIcon.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listIcon[position]

        holder.binding.imgItemPhoto.setImageResource(data.photo)
        holder.binding.tvItemName.text = data.name
        holder.binding.tvItemLocation.text = data.location

        holder.binding.btnDetail.setOnClickListener {
            onItemClickCallback?.onItemClicked(data)
        }

        holder.binding.btnImdb.setOnClickListener {
            val intent = Intent(holder.itemView.context, BrowserActivity::class.java)
            intent.putExtra("url", data.url)
            holder.itemView.context.startActivity(intent)
        }

        holder.itemView.setOnClickListener {
            onItemClickCallback?.onItemClicked(data)
        }
    }
}