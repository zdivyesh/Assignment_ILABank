package com.example.assignment.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.viewpager.widget.ViewPager
import com.example.assignment.R
import com.example.assignment.data.CarouselData
import com.example.assignment.data.CarouselListData
import com.example.assignment.databinding.FragmentMainBinding
import com.example.assignment.utils.hideKeyboard
import com.example.assignment.viewmodels.CarouselViewModel

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var recyclerAdapter: RecyclerViewAdapter
    private val viewModel: CarouselViewModel by viewModels()
    private val carouselData = arrayListOf<CarouselData>()
    private val carouselListData = arrayListOf<CarouselListData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCarouselResult()
        initView()
    }

    private fun initView() {
        initViewPager()
        initRecyclerView()
        initSearch()
    }

    private fun initViewPager() {
        viewPagerAdapter = ViewPagerAdapter(carouselData)
        binding.vpCarousel.adapter = viewPagerAdapter
        binding.tlIndicator.setupWithViewPager(binding.vpCarousel, true)
        binding.vpCarousel.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int, positionOffset: Float, positionOffsetPixels: Int,
            ) {
            }

            override fun onPageSelected(position: Int) {
                viewModel.getCarouselListData(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
    }

    private fun initRecyclerView() {
        recyclerAdapter = RecyclerViewAdapter(carouselListData)
        binding.rvCarousel.addItemDecoration(DividerItemDecoration(requireContext(),
            DividerItemDecoration.VERTICAL))
        binding.rvCarousel.adapter = recyclerAdapter
    }

    private fun initSearch() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                recyclerAdapter.filter.filter(s)
            }

        })

        binding.etSearch.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                binding.etSearch.hideKeyboard()
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun getCarouselResult() {
        viewModel.carouselDataResult().observe(viewLifecycleOwner, {
            bindViewPagerData(it)
        })

        viewModel.carouselDataListResult().observe(viewLifecycleOwner, {
            bindRecyclerViewData(it)
        })
    }

    private fun bindViewPagerData(list: List<CarouselData>) {
        carouselData.clear()
        carouselData.addAll(list)
        viewPagerAdapter.notifyDataSetChanged()
        viewModel.getCarouselListData(0)
    }

    private fun bindRecyclerViewData(list: List<CarouselListData>) {
        recyclerAdapter.filter.filter(binding.etSearch.text)
        carouselListData.clear()
        carouselListData.addAll(list)
        recyclerAdapter.notifyDataSetChanged()
    }
}