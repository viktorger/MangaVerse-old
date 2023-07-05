package com.viktorger.mangaverse.presentation.home.chapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.viktorger.mangaverse.databinding.ItemPageBinding
import com.viktorger.mangaverse.domain.models.MangaShortcut
import java.net.URL

class PagesAdapter : RecyclerView.Adapter<PagesAdapter.ViewHolder>() {

    private val items = mutableListOf<String>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(urls: List<String>) {
        this.items.clear()
        this.items.addAll(urls)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPageBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    class ViewHolder(
        private val binding: ItemPageBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(url: String) {
            val storageReference = Firebase.storage.getReferenceFromUrl(url)
            storageReference.downloadUrl.addOnSuccessListener {

                val circularProgressDrawable = CircularProgressDrawable(binding.root.context)
                circularProgressDrawable.strokeWidth = 5f
                circularProgressDrawable.centerRadius = 30f
                circularProgressDrawable.start()

                Glide.with(binding.root.context)
                    .load(it)
                    .placeholder(circularProgressDrawable)
                    .dontTransform()
                    .into(binding.ivPage)

            }.addOnFailureListener {
                Log.i("PagesAdapter", "${it.stackTrace}")
            }
        }

    }
}