package com.example.assignment.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment.data.CarouselData
import com.example.assignment.data.CarouselListData
import com.example.assignment.repository.CarouselRepository


class CarouselViewModel : ViewModel() {

    private val carouselData = MutableLiveData<List<CarouselData>>()
    private val carouselListData = MutableLiveData<List<CarouselListData>>()

    init {
        getCarouselData()
    }

    private fun getCarouselData() {
        return carouselData.postValue(CarouselRepository().getCarouselData())
    }

    fun carouselDataResult(): LiveData<List<CarouselData>> {
        return carouselData
    }


    fun getCarouselListData(id: Int) {
        return carouselListData.postValue(carouselData.value?.get(id)?.data)
    }

    fun carouselDataListResult(): LiveData<List<CarouselListData>> {
        return carouselListData
    }


}