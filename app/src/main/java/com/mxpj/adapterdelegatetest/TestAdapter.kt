package com.mxpj.adapterdelegatetest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.mxpj.adapterdelegatetest.databinding.ItemTestBinding

class TestAdapter: AdapterDelegate<TestItem, ItemTestBinding> {
    override fun onBindViewHolder(item: TestItem, binding: ItemTestBinding) {
        binding.tvTest.text = item.testTxt
    }

    override fun getViewBindingInflater(): (LayoutInflater, ViewGroup?, Boolean) -> ViewBinding {
        return ItemTestBinding::inflate
    }

    override fun isForViewType(item: Any): Boolean {
        if(item is TestItem) return true
        return false
    }

    override fun onRecycle(binding: ItemTestBinding) {

    }
}