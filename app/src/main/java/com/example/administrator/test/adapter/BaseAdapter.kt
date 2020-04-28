package com.example.administrator.test.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/** BaseAdapter
 *  Created by qiu on 2020/3/31 14:21.
 */
abstract class BaseAdapter<T>(var context: Context) : ListAdapter<T, BaseAdapter.BaseViewHolder>(
    BaseDiff()
) {
    //点击事件
    var listener: OnItemClickListener<T>? = null
    var holderMap = HashMap<T, BaseViewHolder>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val itemView = LayoutInflater.from(context).inflate(getLayoutId(viewType), parent, false)
        return BaseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val entity = getItem(position)
        holderMap[entity] = holder
        bindData(holder, position)
        holder.itemView.setOnClickListener {
            //点击事件
            listener?.onItemClick(entity, position)
        }
        holder.itemView.setOnLongClickListener {
            //长按事件
            listener?.onItemLongClick(entity, position)
            true
        }
    }

    class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var views = HashMap<Int, View>()
        /**
         * view的复用
         */
        fun <T : View> getView(id: Int): T {
            var view = views[id]
            if (view == null) {
                view = itemView.findViewById(id)
                views[id] = view
            }
            return view!! as T
        }
    }

    /**
     * 布局
     */
    abstract fun getLayoutId(viewType: Int): Int

    /**
     * 绑定数据
     */
    abstract fun bindData(holder: BaseViewHolder, position: Int)
}

class BaseDiff<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

}

/**
 * 点击监听接口
 */
interface OnItemClickListener<T> {
    fun onItemClick(t: T, position: Int)

    fun onItemLongClick(t: T, position: Int)
}