package com.example.xqtest.scrollabletable.demo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xqtest.R
import com.example.xqtest.extend.dp2pxInt
import com.example.xqtest.scrollabletable.TableAdapter
import com.example.xqtest.util.UIUtil

/**
 * @author: hepan
 * @date: 2022/6/8
 * @desc:
 *
 * 目的: 测试一个现象. 动态改变列的宽度后, 标题栏与内容栏无法保持一致.
 *
 */
class ScrollTable2Adapter(context:Context, val titles:List<String>) : TableAdapter() {

    private val TYPE_NAME = 1
    private val TYPE_ITEM= 2

    private val mItemWidth = context.dp2pxInt(78f)
    private val mLastItemPaddingNormal = context.dp2pxInt(16f)
    private val mLastItemPaddingBig = context.dp2pxInt(46f)

    var isBigType = false
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
        if (isBigType){
            return if (column == columnStart) {
                screenWidth - mItemWidth * 3 - mLastItemPaddingBig
            } else if (column == columnCount - 1) {
                mItemWidth + mLastItemPaddingBig
            } else {
                mItemWidth
            }
        }else{
            return if (column == columnStart) {
                screenWidth - mItemWidth * 3 - mLastItemPaddingNormal
            } else if (column == columnCount - 1) {
                mItemWidth + mLastItemPaddingNormal
            } else {
                mItemWidth
            }
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
        println("hepan bind row = "+row)
        if (holder is MyItemHolder) {
            holder.setData("行:$row  列:$column ")
        }
        if (holder is MyTitleHolder){
            // 修改首列宽度,直接在 ScrollableTabl e内部修改无效
            if (holder.itemView.layoutParams.width != getItemViewWidth(row, columnStart)) {
                holder.itemView.layoutParams.width = getItemViewWidth(row, columnStart)
            }
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
            LayoutInflater.from(parent.context).inflate(R.layout.nesting_portfolio_fund_index_item, parent, false)
        )
    }


    override fun hasHeadRow(): Boolean {
        return true
    }

    override fun onCreateHeaderViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return MyFirstTitleHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.scroll_table_item, parent, false)
        )
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder, column: Int) {
        if (holder is MyItemHolder){
            holder.setData("列$column")
        }
    }

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
//        val tvTest: TextView = itemView.findViewById(R.id.tvTest)
        val tvTest: TextView = itemView.findViewById(R.id.tvTopValue)
        fun setData(msg: String) {
            tvTest.text = "$msg"
        }
    }


    private class MyFirstTitleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                val tvTest: TextView = itemView.findViewById(R.id.tvTest)
        fun setData(msg: String) {
            tvTest.text = "$msg"
        }
    }
}