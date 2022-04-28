package com.example.lab6.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lab6.R.drawable.*
import com.example.lab6.databinding.FragmentDishBinding

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentDishBinding? = null
    private lateinit var imageList: Array<Array<Int>>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDishBinding.inflate(inflater, container, false)
        val root = binding.root
        Log.i("TAG", DISH)
        Log.i("TAG", CASE)

        val image: ImageView = binding.imageView
        pageViewModel.text.observe(viewLifecycleOwner, Observer {
            when (DISH) {
                "Drinks" -> imageList = arrayOf(arrayOf(coffee1, coffee2), arrayOf(tea1, tea2),
                    arrayOf(juice2, juice2))
                "Salads" -> imageList = arrayOf(arrayOf(caesars1, caesars2), arrayOf(venecia1, venecia2),
                    arrayOf(sunflower1, sunflower2))
                "Desserts" -> imageList = arrayOf(arrayOf(cheesecake1, cheesecake2), arrayOf(strudel1, strudel2),
                    arrayOf(brownieie1, brownieie2))
            }
            when(it) {
                "1" -> {
                    image.setImageResource(imageList[CASE.toInt()][0])
                }
                "2" -> {
                    image.setImageResource(imageList[CASE.toInt()][1])
                }
            }
        })
        return root
    }

    companion object {
        var CASE = "None"
        var DISH = "DISH"

        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}