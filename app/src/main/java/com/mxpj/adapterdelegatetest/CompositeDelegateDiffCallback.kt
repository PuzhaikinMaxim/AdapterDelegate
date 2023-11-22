package com.mxpj.adapterdelegatetest

import androidx.recyclerview.widget.DiffUtil
import kotlin.reflect.KClass

class CompositeDelegateDiffCallback(
    private val oldList: List<Any>,
    private val newList: List<Any>,
    private val comparators: Map<KClass<*>, ItemComparator<Any>>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        val comparator = comparators[oldItem::class] ?: throw RuntimeException("Comparator not found")
        return comparator.areItemsTheSame(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        val comparator = comparators[oldItem::class] ?: throw RuntimeException("Comparator not found")
        return comparator.areContentsTheSame(oldItem, newItem)
    }
}