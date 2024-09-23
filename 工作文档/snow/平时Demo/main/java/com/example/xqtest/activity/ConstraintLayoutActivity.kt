package com.example.xqtest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.Barrier
import com.example.xqtest.R

class ConstraintLayoutActivity : AppCompatActivity() {
     lateinit var clContainer:View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_layout2)
        clContainer =  findViewById<View>(R.id.clContainer)
//        setBarrierType()

        clContainer.setOnClickListener {
            changeIDs()
        }

    }

    /**
     * 利用 Barrier 关联的 View 显示/隐藏来控制 Barrier 位置.
     * @see R.layout.activity_constraint_layout2
     */
    private fun changeBarrierChildState() {
        val end2 = findViewById<View>(R.id.end2)
        if (end2.visibility == View.VISIBLE) {
            end2.visibility = View.GONE
        } else {
            end2.visibility = View.VISIBLE
        }
    }

    /**
     * 设置 Barrier 的 Type (barrierDirection),
     * 设置完毕后需要调用 barrier.requestLayout() 使其生效
     */
    private fun setBarrierType() {
        val barrier = findViewById<Barrier>(R.id.barrier)
        val dir = barrier.type
        if (dir == Barrier.END) {
            barrier.type = Barrier.START
        }else{
            barrier.type = Barrier.END
        }
        barrier.requestLayout()
    }

    /**
     * 利用 Barrier 的  setReferencedIds(int[] ids) 改变其特性
     * 要调用 requestLayout() 使其生效
     */
    private var changed = false
    private fun changeIDs(){
        val barrier = findViewById<Barrier>(R.id.barrier)
        if(changed){
            barrier.referencedIds = intArrayOf(R.id.end1)
        }else{
            barrier.referencedIds = intArrayOf(R.id.end2)
        }
        changed = !changed
        barrier.requestLayout()
    }
}