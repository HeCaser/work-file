package com.example.xqtest.recycleview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EdgeEffect
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xqtest.R
import com.example.xqtest.activity.ViewTestActivity
import com.example.xqtest.view.StretchEdgeEffect

class RvItemRemoveActivity : AppCompatActivity() {
    lateinit var top: StretchEdgeEffect
    lateinit var bottom: StretchEdgeEffect
    lateinit var recyclerView: RecyclerView
    lateinit var mAdapter:MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv)

        val names  = arrayListOf<String>("0","1","2")
        mAdapter = MyAdapter(names)
        findViewById<View>(R.id.tvTest).setOnClickListener {
            names.removeAt(1)


            mAdapter.notifyItemRemoved(0)
        }
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(this@RvItemRemoveActivity, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
        }

        println("hepan RvItemRemoveActivity onCreate")
    }

    override fun onRestart() {
        super.onRestart()
        println("hepan RvItemRemoveActivity onNewIntent")
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        println("hepan RvItemRemoveActivity onNewIntent")
    }
    class MyAdapter(val list:ArrayList<String>) : RecyclerView.Adapter<MyAdapter.Holder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            return Holder(
                LayoutInflater.from(parent.context)
                    .inflate(android.R.layout.simple_list_item_1, parent, false)
            )
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.tv.text = list[position]
            holder.tv.setOnClickListener {
                it.context.startActivity(Intent(it.context,ViewTestActivity::class.java))
            }
        }

        override fun getItemCount(): Int {
            return list.size
        }

        class Holder(view: View) : RecyclerView.ViewHolder(view) {
            val tv = view.findViewById<TextView>(android.R.id.text1)


        }
    }
}