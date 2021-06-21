package com.example.assignment.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.example.assignment.R
import com.example.assignment.data.CarouselData
import com.example.assignment.databinding.RowCarouselBinding

class ViewPagerAdapter(private val list: List<CarouselData>) : PagerAdapter() {

    override fun isViewFromObject(view: View, Object: Any): Boolean {
        return view === Object as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = DataBindingUtil.inflate<RowCarouselBinding>(
            LayoutInflater.from(container.context), R.layout.row_carousel, container, false
        )
        binding.ivCarousalImage.setImageResource(list[position].carouselImage)
        container.addView(binding.root)
        return binding.root
    }

    override fun getCount(): Int = list.size

    override fun destroyItem(container: ViewGroup, position: Int, Object: Any) {
        container.removeView(Object as ConstraintLayout)
    }
}