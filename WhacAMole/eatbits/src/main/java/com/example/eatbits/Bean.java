package com.example.eatbits;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

/**
 * Created by laper sfacg on 2016/7/20.
 */
public class Bean {

    Bitmap image;
    int pointX,pointY;//豆子坐标

    public Bean(Context context,int x, int y){
        pointX = x;
        pointY = y;
        if(image==null){
            //加载图片
            image= BitmapFactory.decodeResource(context.getResources(),R.drawable.bean);
        }
    }

    public void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(image, pointX*36+18, pointY*36+18, paint);
    }
}

