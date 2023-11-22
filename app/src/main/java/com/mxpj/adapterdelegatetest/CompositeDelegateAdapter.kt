package com.mxpj.adapterdelegatetest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KClass

class CompositeDelegateAdapter : Adapter<CompositeDelegateAdapter.BaseViewHolder>() {

    private val delegates: MutableList<AdapterDelegate<Any, ViewBinding>> = mutableListOf()

    private val comparators: HashMap<KClass<*>,ItemComparator<Any>> = hashMapOf()

    var list: List<Any> = listOf()
        set(value) {
            val callback = CompositeDelegateDiffCallback(field, value, comparators)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = delegates[viewType].getViewBindingInflater()
        val binding = inflater(LayoutInflater.from(parent.context), parent, false)
        return BaseViewHolder(binding, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val binding = holder.binding
        val item = list[position]
        delegates[holder.viewType].onBindViewHolder(item, binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        for((index, delegate) in delegates.withIndex()){
            if(delegate.isForViewType(list[position])){
                return index
            }
        }
        throw RuntimeException("Delegate not found")
    }

    override fun onViewRecycled(holder: BaseViewHolder) {
        val binding = holder.binding
        delegates[holder.viewType].onRecycle(binding)
        super.onViewRecycled(holder)
    }

    fun addAdapter(adapter: AdapterDelegate<*,*>) {
        delegates.add(adapter as AdapterDelegate<Any, ViewBinding>)
    }

    fun addComparator(comparator: ItemComparator<*>, itemType: KClass<*>) {
        comparators[itemType] = comparator as ItemComparator<Any>
    }

    class BaseViewHolder(val binding: ViewBinding, val viewType: Int): ViewHolder(binding.root)
}