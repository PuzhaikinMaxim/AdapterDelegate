package com.mxpj.adapterdelegatetest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

interface AdapterDelegate<I, B : ViewBinding> {

    fun onBindViewHolder(item: I, binding: B)

    fun getViewBindingInflater(): (LayoutInflater, ViewGroup?, Boolean) -> ViewBinding

    fun isForViewType(item: Any): Boolean

    fun onRecycle(binding: B)
}