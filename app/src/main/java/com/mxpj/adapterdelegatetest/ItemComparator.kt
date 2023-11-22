package com.mxpj.adapterdelegatetest

interface ItemComparator<I> {

    fun areItemsTheSame(oldItem: I, newItem: I): Boolean

    fun areContentsTheSame(oldItem: I, newItem: I): Boolean
}