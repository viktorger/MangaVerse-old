package com.viktorger.mangaverse.presentation.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.viktorger.mangaverse.R
import com.viktorger.mangaverse.databinding.ItemMangaShortcutBinding
import com.viktorger.mangaverse.domain.models.MangaShortcut

class RecentMangaAdapter(private val listener: MangaItemListener) : RecyclerView.Adapter<RecentMangaAdapter.ViewHolder>() {
    interface MangaItemListener {
        fun onClickListener(mangaId: String)
    }

    private val items = mutableListOf<MangaShortcut>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<MangaShortcut>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ItemMangaShortcutBinding,
        private val listener: MangaItemListener
    ) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var item: MangaShortcut
        init {
            binding.root.setOnClickListener {
                listener.onClickListener(item.mangaId)
            }
        }

        // TODO: UNSUPPORTABLE
        fun bind(item: MangaShortcut) {
            this.item = item
            binding.itemMangaTitle.text = item.title
            binding.itemMangaType.text = item.type


            // Reference to an image file in Cloud Storage
            val storageReference = Firebase.storage.getReferenceFromUrl(item.imgURL)
            storageReference.downloadUrl.addOnSuccessListener {

                val circularProgressDrawable = CircularProgressDrawable(binding.root.context)
                circularProgressDrawable.strokeWidth = 5f
                circularProgressDrawable.centerRadius = 30f
                circularProgressDrawable.start()

                Glide.with(binding.root.context)
                    .load(it)
                    .placeholder(circularProgressDrawable)
                    .into(binding.itemMangaShortcut)

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMangaShortcutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])


    override fun getItemCount(): Int = items.size
}