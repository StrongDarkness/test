package com.example.administrator.test.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.administrator.test.R
import com.example.administrator.test.data.model.SocketBean

/**
 *  Created by qiu on 2020/4/28 10:25.
 */
class ChatAdapter(context: Context) : BaseAdapter<SocketBean>(context) {
    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_chat_layout
    }

    @SuppressLint("SetTextI18n")
    override fun bindData(holder: BaseViewHolder, position: Int) {
        var bean = getItem(position)
        var rl_left = holder.getView<RelativeLayout>(R.id.rl_left)
        var rl_right = holder.getView<RelativeLayout>(R.id.rl_right)
        var tv_left = holder.getView<TextView>(R.id.tv_left)
        var tv_right = holder.getView<TextView>(R.id.tv_right)
        var tv_left_name = holder.getView<TextView>(R.id.tv_left_name)
        var tv_right_name = holder.getView<TextView>(R.id.tv_right_name)

        if (bean.isSelf) {
            rl_left.visibility = View.GONE
            rl_right.visibility = View.VISIBLE
            tv_right.text = bean.msg
            tv_right_name.text = bean.username + " " + bean.time
        } else {
            rl_left.visibility = View.VISIBLE
            rl_right.visibility = View.GONE
            tv_left.text = bean.msg
            tv_left_name.text = bean.username + " " + bean.time
        }
    }
}