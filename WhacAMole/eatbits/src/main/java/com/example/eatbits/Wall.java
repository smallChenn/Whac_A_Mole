package com.example.eatbits;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by laper sfacg on 2016/7/20.
 */
public class Wall {

    public static Bitmap WALL_IMAGE;
    int pointX,pointY;
    Bitmap image;

    public Wall(Context context, int szx, int szy){
        pointX = szx;
        pointY = szy;

        //加载图片
        image = BitmapFactory.decodeResource(context.getResources(),R.drawable.wall);
    }
    public void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(image, pointX*36, pointY*36, paint);
    }
}
