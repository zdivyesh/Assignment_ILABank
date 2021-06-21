package com.example.assignment.repository

import com.example.assignment.R
import com.example.assignment.data.CarouselData
import com.example.assignment.data.CarouselListData


const val CAROUSEL_SIZE = 5
const val CAROUSEL_LIST_SIZE = 15

class CarouselRepository {
    fun getCarouselData(): List<CarouselData> {
        val carouselData = arrayListOf<CarouselData>()
        for (i in 0 until CAROUSEL_SIZE) {
            val carouselListData = arrayListOf<CarouselListData>()
            for (j in 0 until CAROUSEL_LIST_SIZE) {
                carouselListData.add(
                    CarouselListData(
                        "Label $i$j", R.drawable.ic_android,
                    )
                )
            }
            carouselData.add(
                CarouselData(
                    R.drawable.img_banner, carouselListData
                )
            )
        }
        return carouselData
    }
}