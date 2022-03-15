package com.lgomez.movies.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lgomez.movies.databinding.ItemRecyclerPopularMoviesBinding
import com.lgomez.movies.domain.model.PopularMovies
import kotlin.properties.Delegates

class PopularMoviesItemAdapter() : RecyclerView.Adapter<PopularMoviesItemAdapter.ViewHolder>() {
    var items: List<PopularMovies> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }
    var onClickListener: ((PopularMovies) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclerPopularMoviesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], onClickListener)
    }

    fun setData(data: MutableList<PopularMovies>) {
        this.items = data
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private val binding: ItemRecyclerPopularMoviesBinding) :
        RecyclerView.ViewHolder(binding.cardLayout) {

        internal fun bind(value: PopularMovies, listener: ((PopularMovies) -> Unit)?) {
            binding.txtTitle.text = value.title
            Glide.with(binding.root)
                .load(value.cover)
                .centerCrop()
                .into(binding.imageView)

            binding.cardLayout.setOnClickListener {
                listener?.invoke(value)
            }
        }
    }
}
