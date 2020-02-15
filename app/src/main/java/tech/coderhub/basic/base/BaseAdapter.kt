package tech.coderhub.basic.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import tech.coderhub.basic.listener.ClickListener
import tech.coderhub.basic.view.EmptyHolder
import java.util.*

abstract class BaseAdapter(@LayoutRes private val resource: Int) : RecyclerView.Adapter<EmptyHolder>() {

    var items: MutableList<Any> = ArrayList()
    var clickListener: ClickListener = object: ClickListener {override fun onItemClick(item:View, any:Any){} }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            EmptyHolder(LayoutInflater.from(parent.context).inflate(resource, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: EmptyHolder, position: Int) {
        bindView(holder.itemView, items[position], position)
    }

    abstract fun bindView(view: View, any: Any, position: Int=0)

    fun setItemList(itemList: MutableList<Any>) {
        this.items = itemList
        notifyDataSetChanged()
    }

    fun setItemClickListener(itemClickListener: ClickListener) {
        this.clickListener = itemClickListener
    }
}