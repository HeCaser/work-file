package com.example.xqtest.scrollabletable.demo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xqtest.R
import com.example.xqtest.extend.dp2px
import com.example.xqtest.extend.dp2pxInt
import com.example.xqtest.scrollabletable.TableAdapter
import com.example.xqtest.util.UIUtil

/**
 * @author: hepan
 * @date: 2022/6/8
 * @desc:
 *
 * 1. 默认第一列固定,其他列可滑动
 * 2. 默认没有顶部固定行,顶部固定行由 scrollable_table_row 布局填充
 * 3. 首列宽度默认包裹内容, 其  RecyclerView.ViewHolder 默认设置的宽度无效, 于复用,导致 UI 异常(复用时没有更新宽度),由于宽度不一致,导致后续列无法对其
 * 4. 针对 3 中问题, 需要重写 getItemViewWidth 方法, 特别是首列宽度.
 * 5. 行高以以当前行中 View 最大高度为准.
 */
class ScrollTableAdapter(context:Context,val titles:List<String>) : TableAdapter() {

    private val TYPE_NAME = 1
    private val TYPE_ITEM= 2

    private val firstColumnWidth = context.dp2pxInt(120f)
    private val screenWidth = UIUtil.getScreenWidth(context)
    // 行数
    override fun getRowCount(): Int {
        return titles.size
    }

    // 列数
    override fun getColumnCount(): Int {
        return 10
    }

    // 宽度设置
    override fun getItemViewWidth(row: Int, column: Int): Int {
        if (column == 0) {
            return firstColumnWidth
        }else{
            return  (screenWidth - firstColumnWidth)/(3)
        }
    }


    /**
     * 获取 ViewType ,当 hasHeadRow 返回 true. row 从 -1 开始. 否则从 0 开始
     */
    override fun getItemViewType(row: Int, column: Int): Int {
        if (column == columnStart){
            return TYPE_NAME
        }
        return TYPE_ITEM
    }

    /**
     * bug:  每个列都是 wrap_content. 当第一列(item)高度小于后面列高度时, 第一列可以完全展示. 当第一列(item) 高度大于后面列高度时,第一列高度会被限制.
     * 修改 R.layout.scrollable_table_row 行高逻辑可以解决
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, row: Int, column: Int) {
        if (holder is MyItemHolder) {
            holder.setData("行:$row  列:$column ")
        }
        if (holder is MyTitleHolder){
            holder.setData(titles.get(row))
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_NAME){
            return MyTitleHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.nesting_portfolio_fund_name_item, parent, false)
            )
        }
        return MyItemHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.scroll_table_item, parent, false)
        )
    }

    /** 设置 Header (固定行). 88888888888888888888*/

    override fun hasHeadRow(): Boolean {
        if (titles.isNullOrEmpty()){
            println("hepan 列表为空")
            return false
        }
        println("hepan 列表不为空")
        return true
    }

    override fun onCreateHeaderViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return MyItemHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.scroll_table_item, parent, false)
        )
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder, column: Int) {
        if (holder is MyItemHolder){
            holder.setData("列$column")
        }
    }

    /** 88888888888888888888*/


    /**
     * @see R.layout.nesting_portfolio_fund_name_item
     */
    private class MyTitleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        fun setData(msg: String) {
            tvName.text = "$msg"
        }
    }

    /**
     * @see R.layout.scroll_table_item
     */
    private class MyItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTest: TextView = itemView.findViewById(R.id.tvTest)
        fun setData(msg: String) {
            tvTest.text = "$msg"
        }
    }
}