package com.mxpj.adapterdelegatetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.mxpj.adapterdelegatetest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val testAdapter = TestAdapter()
        val compositeDelegateAdapter = CompositeDelegateAdapter()
        compositeDelegateAdapter.addAdapter(testAdapter)
        compositeDelegateAdapter.addComparator(object : ItemComparator<TestItem> {
            override fun areItemsTheSame(oldItem: TestItem, newItem: TestItem): Boolean {
                return oldItem.test == newItem.test
            }

            override fun areContentsTheSame(oldItem: TestItem, newItem: TestItem): Boolean {
                return oldItem == newItem
            }
        }, TestItem::class)
        binding.rvMain.adapter = compositeDelegateAdapter
        compositeDelegateAdapter.list = listOf(TestItem(1,"Huy"))
        compositeDelegateAdapter.list = listOf(TestItem(1,"Huy"),TestItem(2,"Huy"))
    }
}