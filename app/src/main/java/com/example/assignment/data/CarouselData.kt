package com.example.assignment.data

import androidx.annotation.DrawableRes

data class CarouselData(
        @DrawableRes val carouselImage: Int, val data: List<CarouselListData>)