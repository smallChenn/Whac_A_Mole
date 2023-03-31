package com.example.eatbits;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by laper sfacg on 2016/7/20.
 */
public class BigBean {

    public static Bitmap BIGBEAN_IMAGE;
    int pointX,pointY;
    Bitmap image;
    public BigBean(Context context, int x, int y){
        pointX = x;
        pointY = y;
        if(BIGBEAN_IMAGE==null){
            //加载图片
            BIGBEAN_IMAGE= BitmapFactory.decodeResource(context.getResources(),R.drawable.big_bean);
        }
        image = BIGBEAN_IMAGE;
    }

    public void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(image, pointX*36+18, pointY*36+18, paint);
    }
}
