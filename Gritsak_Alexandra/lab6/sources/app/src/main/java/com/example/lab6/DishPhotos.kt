package com.example.lab6

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.example.lab6.ui.main.SectionsPagerAdapter
import com.example.lab6.databinding.ActivityDishBinding
import com.example.lab6.ui.main.PlaceholderFragment

class DishPhotos : AppCompatActivity() {

    private lateinit var binding: ActivityDishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDishBinding.inflate(layoutInflater)
        setContentView(binding.root)
        PlaceholderFragment.CASE = intent.getStringExtra(PlaceholderFragment.CASE)!!
        PlaceholderFragment.DISH = intent.getStringExtra(PlaceholderFragment.DISH)!!
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)

        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
    }
}