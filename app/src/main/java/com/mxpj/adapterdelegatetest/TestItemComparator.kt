package com.mxpj.adapterdelegatetest

class TestItemComparator: ItemComparator<TestItem> {

    override fun areItemsTheSame(oldItem: TestItem, newItem: TestItem): Boolean {
        return oldItem.test == newItem.test
    }

    override fun areContentsTheSame(oldItem: TestItem, newItem: TestItem): Boolean {
        return oldItem == newItem
    }
}