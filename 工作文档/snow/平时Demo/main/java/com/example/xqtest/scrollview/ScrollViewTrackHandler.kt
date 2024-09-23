package com.example.xqtest.scrollview

import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import kotlin.collections.ArrayList
import kotlin.collections.HashSet
import kotlin.collections.List

/**
 * ScrollView 的子 View 曝光(埋点)管理,逻辑参考
 * @see
 */
class ScrollViewTrackHandler(private val scrollview: ViewGroup) {

    // 可见条目,准确的描述是在屏幕内的条目
    private val currentVisibleItems = HashSet<Int>()
    // 需要检查的条目
    private val mItems = arrayListOf<OnItemScrollListener>()

    /**
     * 递归遍历 View 树,查找 OnItemScrollListener
     */
    @Deprecated("may be cause a stackoverflow error, please call addItem")
    fun findALlItemsAuto(){
        mItems.clear()
        val items = scrollview.getAllChildren().filter { it is OnItemScrollListener }
        items.forEach {
            if (it is OnItemScrollListener) {
                mItems.add(it)
            }
        }
    }

    fun addItem(item:OnItemScrollListener){
        mItems.add(item)
    }

    fun addItems(items: Collection<OnItemScrollListener>){
        mItems.addAll(items)
    }

    fun initItems(items: Collection<OnItemScrollListener>) {
        mItems.clear()
        mItems.addAll(items)
    }

    fun onScrollIdle() {
        traverseItems()
    }

    fun onPageShow() {
        if (currentVisibleItems.isEmpty()){
            return
        }
        val iterator = mItems.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (item is View) {
                if (item.isVisibleInParent(scrollview)) {
                    if (currentVisibleItems.contains(item.hashCode())) {
                        item.onShow()
                        currentVisibleItems.add(item.hashCode())
                    }
                }
            }
        }
    }

    /**
     * 进入其他页面，到后台等，页面不可见时...
     */
    fun onPageHidden() {
        if (currentVisibleItems.isEmpty()){
            return
        }
        val iterator = mItems.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (item is View) {
                if (item.isVisibleInParent(scrollview)) {
                    if (currentVisibleItems.contains(item.hashCode())) {
                        item.onPageHidden()
                    }
                }
            }
        }
    }

    private fun traverseItems() {
        val iterator = mItems.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (item is View) {
                if (item.isVisibleInParent(scrollview)) {
                    if (!currentVisibleItems.contains(item.hashCode())) {
                        item.onShow()
                        currentVisibleItems.add(item.hashCode())
                    }
                } else {
                    if (currentVisibleItems.contains(item.hashCode())) {
                        item.onHide()
                        currentVisibleItems.remove(item.hashCode())
                    }
                }
            }
        }
    }


    private fun View.isVisibleInParent(parent: View): Boolean {
        // 可以更精确的描述 View 位置
//        val viewLocation = IntArray(2)
//        getLocationOnScreen(viewLocation)
//
//        val parentLocation = IntArray(2)
//        parent.getLocationOnScreen(parentLocation)
//        println("hepan  parentLocation: ${parentLocation[0]}:${parentLocation[1]} parentHeight = ${parent.height}   child-${getTag()} location = ${viewLocation[0]}:${viewLocation[1]}")

        val isVisible = getGlobalVisibleRect(Rect())
        return isVisible && visibility == View.VISIBLE
    }


    private fun View.getAllChildren(): List<View> {
        val result = ArrayList<View>()
        if (this !is ViewGroup) {
            result.add(this)
        } else {
            for (index in 0 until this.childCount) {
                val child = this.getChildAt(index)
                result.addAll(child.getAllChildren())
            }
        }
        return result
    }

}