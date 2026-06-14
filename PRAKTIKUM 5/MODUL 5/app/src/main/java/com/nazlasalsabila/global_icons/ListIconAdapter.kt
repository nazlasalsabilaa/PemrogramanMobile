package com.nazlasalsabila.global_icons

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nazlasalsabila.global_icons.databinding.ItemRowIconBinding

class ListIconAdapter(private var listIcon: ArrayList<GlobalIcon>) :
    RecyclerView.Adapter<ListIconAdapter.ListViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null
    private var fullListIcon: List<GlobalIcon> = ArrayList()

    fun setOnItemClickCallback(callback: OnItemClickCallback) {
        this.onItemClickCallback = callback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: GlobalIcon)
    }

    fun updateData(newList: List<GlobalIcon>) {
        fullListIcon = newList
        listIcon.clear()
        listIcon.addAll(newList)
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        val filteredList = if (query.isEmpty()) {
            fullListIcon
        } else {
            fullListIcon.filter { it.name.contains(query, ignoreCase = true) }
        }
        listIcon.clear()
        listIcon.addAll(filteredList)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(val binding: ItemRowIconBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowIconBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listIcon.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listIcon[position]
        val context = holder.itemView.context

        holder.binding.tvName.text = data.name
        holder.binding.tvLocation.text = "${context.getString(R.string.label_release)} ${data.releaseDate}"
        holder.binding.btnRating.text = "Rating"

        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500${data.photoUrl}")
            .into(holder.binding.imgIcon)

        holder.binding.btnDetail.setOnClickListener {
            onItemClickCallback?.onItemClicked(data)
        }

        holder.binding.btnRating.setOnClickListener {
            val url = "https://www.themoviedb.org/movie/${data.id}"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        }
    }
}