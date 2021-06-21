package com.example.assignment.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.data.CarouselListData
import com.example.assignment.databinding.RowCarouselListBinding


class RecyclerViewAdapter(
    private val list: ArrayList<CarouselListData>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(),Filterable {

    var filterList = ArrayList<CarouselListData>()

    init {
        filterList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RowCarouselListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.row_carousel_list, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = filterList[position]
        holder.binding.apply {
            tvLabel.text = item.label
            ivLabel.setImageResource(item.image)
        }
    }

    override fun getItemCount(): Int = filterList.size

    class ViewHolder(val binding: RowCarouselListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    filterList = list
                } else {
                    val resultList = ArrayList<CarouselListData>()
                    for (item  in list) {
                        if (item.label.contains(charSearch,true)) {
                            resultList.add(item)
                        }
                    }
                    filterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList = results?.values as ArrayList<CarouselListData>
                notifyDataSetChanged()
            }

        }
    }
}

