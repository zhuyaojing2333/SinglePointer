package com.bawei.zyj.singlepointer;

import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.iv);
        iv.setOnTouchListener(new View.OnTouchListener() {
            private float x;
            private float y;
            // 用来操作图片的模型
            private Matrix oldMatrix = new Matrix();
            private Matrix newMatrix = new Matrix();

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) { // 判断操作类型
                    case MotionEvent.ACTION_DOWN:
                        //按下时记住x,y的坐标
                        x = event.getX();
                        y = event.getY();
                        oldMatrix.set(iv.getImageMatrix());
                        break;
                    case MotionEvent.ACTION_MOVE://移动时
                        //用另一个模型记住按下时的位置
                        newMatrix.set(oldMatrix);
                        //移动模型
                        newMatrix.setTranslate(event.getX() - x, event.getY() - y);
                        break;
                }
                //把图片放入移动后的模型中
                iv.setImageMatrix(newMatrix);
                return true;
            }
        });
    }
}
