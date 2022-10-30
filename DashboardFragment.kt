package com.example.bitfitpart1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.toString
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class DashboardFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            (activity?.application as NutritionApplication).db.nutritionDao().getAll()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val totalValueTV = view.findViewById<TextView>(R.id.totalValue)
        val averageValueTV = view.findViewById<TextView>(R.id.averageValue)
        val minValueTV = view.findViewById<TextView>(R.id.minValue)
        val maxValueTV = view.findViewById<TextView>(R.id.maxValue)

        viewLifecycleOwner.lifecycleScope.launch(IO) {
            val total = (activity?.application as NutritionApplication).db.nutritionDao().getSum()
            val average = (activity?.application as NutritionApplication).db.nutritionDao().getAverage()
            val min = (activity?.application as NutritionApplication).db.nutritionDao().getMin()
            val max = (activity?.application as NutritionApplication).db.nutritionDao().getMax()

            totalValueTV.text = total.toString()
            averageValueTV.text = average.toString()
            minValueTV.text = min.toString()
            maxValueTV.text = max.toString()
        }
    }

    companion object {
        fun newInstance() : DashboardFragment {
            return DashboardFragment()
        }
    }
}