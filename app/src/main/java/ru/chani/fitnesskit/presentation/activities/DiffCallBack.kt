package ru.chani.fitnesskit.presentation.activities

import androidx.recyclerview.widget.DiffUtil
import ru.chani.fitnesskit.presentation.activities.model.ItemViewType

class DiffCallBack(
    private val oldList: List<ItemViewType>,
    private val newList: List<ItemViewType>
) : DiffUtil.Callback()
{

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldAd = oldList[oldItemPosition]
        val newAd = newList[newItemPosition]
        return oldAd == newAd
    }
}