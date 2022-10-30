package com.example.bitfitpart1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

private  const val TAG = "NutritionListFragment"
class NutritionListFragment : Fragment() {
    private val nutritionItems = mutableListOf<NutritionEntity>()
    private lateinit var nutritionItemsRV: RecyclerView
    private lateinit var nutritionAdapter: NutritionAdapter
    // Initial call to onCreate to setup fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            (activity?.application as NutritionApplication).db.nutritionDao().getAll().collect() { databaseList ->
                databaseList.map { entity ->
                    NutritionEntity(
                        entity.id,
                        entity.food,
                        entity.calories
                    )
                }.also { mappedList ->
                    nutritionItems.clear()
                    nutritionItems.addAll(mappedList)
                    nutritionAdapter.notifyDataSetChanged()
                }
            }
        }
    }
    // Called after onCreate
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_nutrition_list, container, false)

        nutritionItemsRV = view.findViewById(R.id.nutritionList)
        nutritionItemsRV.layoutManager = LinearLayoutManager(context)
        nutritionItemsRV.setHasFixedSize(true)
        nutritionAdapter = NutritionAdapter(view.context, nutritionItems)
        nutritionItemsRV.adapter = nutritionAdapter

        return view
    }
    // Called after onCreateView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val deleteAllBtn = view.findViewById<ImageButton>(R.id.deleteAllBtn)
        // Delete All
        deleteAllBtn.setOnClickListener {
            lifecycleScope.launch(IO) {
                (activity?.application as NutritionApplication).db.nutritionDao().deleteAll()
            }
        }

        // New Entry
        val newEntryBtn = view.findViewById<Button>(R.id.newEntryBtn)
        newEntryBtn.setOnClickListener {

            val nutritionItemEt = view.findViewById<EditText>(R.id.foodEditText)
            val caloriesEt = view.findViewById<EditText>(R.id.calorieEditText)

            val nutritionItem = nutritionItemEt.text.toString()
            val calories = caloriesEt.text.toString()
            println(nutritionItem)
            println(calories)
            lifecycleScope.launch(Dispatchers.IO) {
                if (nutritionItem.isNotEmpty() && calories.isNotEmpty()) {
                    (activity?.application as NutritionApplication).db.nutritionDao().insert(
                        NutritionEntity(
                            food = nutritionItem,
                            calories = calories
                        )
                    )
                }
            }
            nutritionItemEt.text.clear()
            caloriesEt.text.clear()
        }
    }

    companion object {
        fun newInstance(): NutritionListFragment {
            return NutritionListFragment()
        }

    }
}