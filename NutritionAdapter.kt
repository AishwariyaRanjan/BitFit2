package com.example.bitfitpart1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NutritionAdapter(private val context: Context, private val nutritionItems: List<NutritionEntity>) :
    RecyclerView.Adapter<NutritionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.nutrition_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NutritionAdapter.ViewHolder, position: Int) {
        val nutritionItems = nutritionItems[position]
        holder.bind(nutritionItems)
    }

    override fun getItemCount(): Int {
        return nutritionItems.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val foodTextView = itemView.findViewById<TextView>(R.id.foodItem)
        private val calorieTextView = itemView.findViewById<TextView>(R.id.calories)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(nutritionItem: NutritionEntity) {
            foodTextView.text = nutritionItem.food
            calorieTextView.text = nutritionItem.calories

        }

        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }
    }
}