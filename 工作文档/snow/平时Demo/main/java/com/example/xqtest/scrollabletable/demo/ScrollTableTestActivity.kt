package com.example.xqtest.scrollabletable.demo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.xqtest.R
import com.example.xqtest.model.NestingAddItems
import com.example.xqtest.view.NestingAddOptionalView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_scrolltable_test.*
import java.util.*
import kotlin.collections.ArrayList

class ScrollTableTestActivity : AppCompatActivity() {

    var mAdapter : ScrollTable2Adapter?=null

    val titles = arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolltable_test)

        initView()
    }

    private fun initView() {
        titles.add("标题1标题1标题1标题1")
        titles.add("标题2")
        titles.add("3短标题")
        for (i in 0 until 50){
            titles.add("位置 ${i+3}")
        }
//        scrollTable.tableAdapter = ScrollTableAdapter(this,titles)
        mAdapter = ScrollTable2Adapter(this,titles)
        scrollTable.tableAdapter = mAdapter
//        scrollTable.setFooter(R.layout.nesting_add_optional_footer)

        tvChange.setOnClickListener {
            testChangeItemWidth()
        }
    }

    /**
     *  动态切换条目宽度, 模拟雪球自选页切换模式后引起的 UI 异常.
     *  原因: 调用 notifyDataSetChanged() 后, 在 getItemViewWidth 回调方法中当 row >=0 时没有更新 column = 0 的宽度, 只有 row = -1 时更新了  column = 0 的宽度(标题行)
     *  解决:
     *  @see com.example.xqtest.scrollabletable.ScrollableTable.TableRowAdapter.onBindViewHolder ,更新宽度.
     */
    private fun testChangeItemWidth(){
        mAdapter?.apply {
            isBigType = !isBigType
            scrollTable.notifyDataSetChanged()
        }
    }


    /**
     * 测试数据变化, 复现类似情景变化引起的问题.
     * 1. 标题栏逻辑无法正常跟随数据量控制: 已修复
     */
    private fun testDataEmptyAndFull(){
        if(titles.isEmpty()){
            titles.add("2长标题2长标题2长标题2长标")
            titles.add("1短标题")
        }else{
            titles.clear()
        }
        scrollTable.notifyDataSetChanged()
    }


    /**
     * 添加 Footer 的数据
     */
    private fun getItemList(): List<NestingAddItems> {
        val type = object : TypeToken<List<NestingAddItems?>?>() {}.type
        val src = "[\n" +
                "    {\n" +
                "        \"title\": \"市场情报站\",\n" +
                "        \"subtitle1\": \"24小时更新\",\n" +
                "        \"list\": [\n" +
                "            {\n" +
                "                \"symbol\": \"SH600030\",\n" +
                "                \"name\": \"中信证券大发大爱对方回复阿道夫改好后大阿道夫刚发的\",\n" +
                "                \"tag\": \"推荐理由标签\",\n" +
                "                \"current\": 30,\n" +
                "                \"percent\": 1.01,\n" +
                "                \"custom_percent\": 1000.5,\n" +
                "                \"custom_tag\": \"成立以来\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"title\": \"市场情报站2\",\n" +
                "        \"subtitle1\": \"24小时更新\",\n" +
                "        \"list\": [\n" +
                "            {\n" +
                "                \"symbol\": \"SH600030\",\n" +
                "                \"name\": \"中信证券1\",\n" +
                "                \"tag\": \"推荐理由标签\",\n" +
                "                \"current\": 30,\n" +
                "                \"percent\": 1.01,\n" +
                "                \"custom_percent\": 1000.5,\n" +
                "                \"custom_tag\": \"成立以来\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"symbol\": \"SH600030\",\n" +
                "                \"name\": \"中信证券1\",\n" +
                "                \"tag\": \"推荐理由标签\",\n" +
                "                \"current\": 30,\n" +
                "                \"percent\": 1.01,\n" +
                "                \"custom_percent\": 1000.5,\n" +
                "                \"custom_tag\": \"成立以来\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]"
        val list: ArrayList<NestingAddItems> = Gson().fromJson(src, type)

        return list
    }

    private fun initFooter() {
        scrollTable.footerView?.apply {

            val footer = this.findViewById<View>(R.id.addOptionalView)
            if (footer is NestingAddOptionalView) {
                val list = getItemList()
                footer.setItems(list)

            }
        }
    }
}