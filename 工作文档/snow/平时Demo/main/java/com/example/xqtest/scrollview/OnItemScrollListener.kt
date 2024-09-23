package com.example.xqtest.scrollview

interface OnItemScrollListener {
    fun onShow()
    fun onHide()
    fun onScroll()
    fun onPageHidden() {}
}