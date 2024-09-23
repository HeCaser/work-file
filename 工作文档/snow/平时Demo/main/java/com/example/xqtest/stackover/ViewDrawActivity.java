package com.example.xqtest.stackover;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.xqtest.R;

/**
 * https://stackoverflow.com/questions/74008657/how-to-make-drawn-view-appear-bigger-on-canvas
 *
 * 没有把 Bitmap 展示出来
 */
public class ViewDrawActivity extends AppCompatActivity {
    LinearLayout llDrawView;
    View viewDraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_draw);
        llDrawView = findViewById(R.id.llDrawView);
        viewDraw = findViewById(R.id.viewDraw);
        llDrawView.postDelayed(() -> {
            testDraw();
        }, 1000);
    }


    private void testDraw() {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inMutable = true;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.empty_comment_close, opt);

        Bitmap b2 = Bitmap.createBitmap(100,100, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        viewDraw.draw(canvas);
        viewDraw.postInvalidate();
//        llDrawView.setBackgroundResource(R.drawable.empty_comment_close);
    }

}