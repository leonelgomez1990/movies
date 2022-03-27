package com.lgomez.movies.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lgomez.movies.databinding.ItemRecyclerPopularMoviesBinding
import com.lgomez.movies.domain.model.PopularMovies
import kotlin.properties.Delegates

class PopularMoviesItemAdapter() : RecyclerView.Adapter<PopularMoviesItemAdapter.ViewHolder>() {
    var items: List<PopularMovies> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }
    var onClickListener: ((PopularMovies) -> Unit)? = null
    var itemsBackup = mutableListOf<PopularMovies>()

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
        this.itemsBackup = data
    }

    override fun getItemCount(): Int = items.size

    fun filter(text: String) {
        var filteredList = mutableListOf<PopularMovies>()
        if (text.isEmpty()) {
            filteredList = itemsBackup
        } else {
            itemsBackup.forEach {
                if (it.title.lowercase().contains(text.lowercase()))
                    filteredList.add(it)
            }
        }
        items = filteredList
    }

    val filter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults? {
            val filteredList: MutableList<PopularMovies> = ArrayList()
            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(items)
            } else {
                val filterPattern = constraint.toString().lowercase().trim { it <= ' ' }
                for (item in items) {
                    if (item.title.lowercase().contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            items = results.values as List<PopularMovies>
            notifyDataSetChanged()
        }
    }

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
