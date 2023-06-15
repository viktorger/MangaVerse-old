package com.viktorger.mangaverse.presentation.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.viktorger.mangaverse.R
import com.viktorger.mangaverse.databinding.HomeItemMangaBinding
import com.viktorger.mangaverse.domain.models.MangaSimpleListItem

class RecentMangaAdapter(private val listener: MangaItemListener) : RecyclerView.Adapter<RecentMangaAdapter.ViewHolder>() {
    interface MangaItemListener {
        fun onClickListener(mangaId: Int)
    }

    private val items = ArrayList<MangaSimpleListItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: ArrayList<MangaSimpleListItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: HomeItemMangaBinding,
        private val listener: MangaItemListener
    ) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var item: MangaSimpleListItem
        init {
            binding.root.setOnClickListener {
                listener.onClickListener(item.mangaId)
            }
        }

        fun bind(item: MangaSimpleListItem) {
            binding.itemMangaShortcut.setImageResource(R.drawable.item_manga_shortcut)
            binding.itemMangaTitle.text = item.title
            binding.itemMangaType.text = item.type
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HomeItemMangaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])


    override fun getItemCount(): Int = items.size
}