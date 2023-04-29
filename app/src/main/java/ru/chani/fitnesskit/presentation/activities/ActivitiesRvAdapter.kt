package ru.chani.fitnesskit.presentation.activities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ru.chani.fitnesskit.databinding.DateCardBinding
import ru.chani.fitnesskit.databinding.TrainingItemCardBinding
import ru.chani.fitnesskit.presentation.activities.model.ItemViewType

class ActivitiesRvAdapter : RecyclerView.Adapter<ActivitiesRvAdapter.CustomViewHolder>() {

    inner class CustomViewHolder(val binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root)


    private var listOfItems = emptyList<ItemViewType>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = when (viewType) {
            ItemViewType.Date.VIEW_TYPE_ID -> DateCardBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
            ItemViewType.Training.VIEW_TYPE_ID -> TrainingItemCardBinding.inflate(
                LayoutInflater.from(parent.context)
            )
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        return CustomViewHolder(binding = binding)
    }

    override fun getItemCount() = listOfItems.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentItem = listOfItems[position]
        when (holder.binding) {
            is DateCardBinding -> {
                val date = currentItem as ItemViewType.Date
                holder.binding.tvDate.text = date.date
            }
            is TrainingItemCardBinding -> {
                val trainingItem = currentItem as ItemViewType.Training
                with(holder.binding) {
                    tvTimeStart.text = trainingItem.trainingItem.statTime
                    tvTimeEnd.text = trainingItem.trainingItem.endTime
                    tvTrainingType.text = trainingItem.trainingItem.typeOfTraining
                    tvPersonName.text = trainingItem.trainingItem.coachName
                    tvLocation.text = trainingItem.trainingItem.place
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentItem = listOfItems[position]
        return when (currentItem) {
            is ItemViewType.Date -> ItemViewType.Date.VIEW_TYPE_ID
            is ItemViewType.Training -> ItemViewType.Training.VIEW_TYPE_ID
        }
    }

    fun setNewList(newList: List<ItemViewType>) {
        val diffCallback = DiffCallBack(listOfItems, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
        listOfItems = newList
    }
}