package com.example.eatbits;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by laper sfacg on 2016/7/20.
 */
public class BG {
    Bitmap bg;//背景图片

    public BG(Context context){

        bg= BitmapFactory.decodeResource(context.getResources(),R.drawable.background);

    }
    public void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(bg, 0, 0, paint);//绘制背景
    }
}
