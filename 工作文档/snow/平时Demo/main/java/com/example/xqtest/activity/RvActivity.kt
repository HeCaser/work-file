package com.example.xqtest.activity

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
import com.example.xqtest.view.StretchEdgeEffect

class RvActivity : AppCompatActivity() {
    lateinit var top: StretchEdgeEffect
    lateinit var bottom: StretchEdgeEffect
    lateinit var recyclerView: RecyclerView
     var mAdapter= MyAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv)

        findViewById<View>(R.id.tvTest).setOnClickListener {
            mAdapter.notifyItemChanged(0)
        }
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(this@RvActivity, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
        }
        top = object : StretchEdgeEffect(this@RvActivity, recyclerView) {
            override fun another(): StretchEdgeEffect {
                return bottom
            }

            override fun pivotY(): Float {
                return 0f
            }
        }

        bottom = object : StretchEdgeEffect(this@RvActivity, recyclerView) {
            override fun another(): StretchEdgeEffect {
                return top
            }

            override fun pivotY(): Float {
                return view.height.toFloat()
            }
        }
        recyclerView.edgeEffectFactory = object : RecyclerView.EdgeEffectFactory() {
            override fun createEdgeEffect(view: RecyclerView, direction: Int): EdgeEffect {
                if (direction == DIRECTION_TOP) {
                    return top
                }
                if (direction == DIRECTION_BOTTOM) {
                    return bottom
                }
                return super.createEdgeEffect(view, direction)
            }
        }
    }

    class MyAdapter : RecyclerView.Adapter<MyAdapter.Holder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            return Holder(
                LayoutInflater.from(parent.context)
                    .inflate(android.R.layout.simple_list_item_1, parent, false)
            )
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            println("hepan onBindViewHolder $position")
            holder.tv.text = "$position"
        }

        override fun getItemCount(): Int {
            return 30
        }

        class Holder(view: View) : RecyclerView.ViewHolder(view) {
            val tv = view.findViewById<TextView>(android.R.id.text1)
        }
    }
}