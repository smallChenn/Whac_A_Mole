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
public class Ghost {

    public static final int UP=1;
    public static final int RIGHT=2;
    public static final int DOWN=3;
    public static final int LEFT=4;

    public static  Bitmap GHOST_IMAGE_UP;
    public static  Bitmap GHOST_IMAGE_DOWN;
    public static  Bitmap GHOST_IMAGE_UP2;
    public static  Bitmap GHOST_IMAGE_DOWN2;
    public static  Bitmap GHOST_IMAGE_UP3;
    public static  Bitmap GHOST_IMAGE_DOWN3;

    int pointX,pointY;
    int x,y;
    Bitmap image;
    int caneat;
    int direction;
    int speed;
    boolean isShow;
    public Ghost(Context context,int szx,int szy){
        if(GHOST_IMAGE_UP2==null){
            //加载图片
            GHOST_IMAGE_UP2= BitmapFactory.decodeResource(context.getResources(),R.drawable.ghost_f_1);
        }
        if(GHOST_IMAGE_DOWN2==null){
            //加载图片
            GHOST_IMAGE_DOWN2= BitmapFactory.decodeResource(context.getResources(),R.drawable.ghost_f_2);
        }
        if(GHOST_IMAGE_UP==null){
            //加载图片
            GHOST_IMAGE_UP= BitmapFactory.decodeResource(context.getResources(),R.drawable.ghost_n_1);
        }
        if(GHOST_IMAGE_DOWN==null){
            //加载图片
            GHOST_IMAGE_DOWN= BitmapFactory.decodeResource(context.getResources(),R.drawable.ghost_n_2);
        }
        caneat=0;
        image = GHOST_IMAGE_UP;
        pointX=szx;
        pointY=szy;
        x=pointX*36;
        y=pointY*36;
        speed=4;
        isShow=true;
    }
    public void draw(Canvas canvas, Paint paint) {
        if (caneat == 0) {
            if (isShow)
                image = GHOST_IMAGE_DOWN;
            else
                image = GHOST_IMAGE_UP;

        }else {
            if (isShow)
                image = GHOST_IMAGE_DOWN2;
            else
                image = GHOST_IMAGE_UP2;
        }
        isShow = !isShow;
        canvas.drawBitmap(image, x, y, paint);
    }
    public void logic() {
        if(caneat==0)
            speed=6;
        else
            speed=3;
        switch (direction) {
            case UP:
                y=(y-speed+1800)%1800;
                break;
            case DOWN:
                y=(y+speed+1800)%1800;
                break;
            case LEFT:
                x=(x-speed+1080)%1080;
                break;
            case RIGHT:
                x=(x+speed+1080)%1080;
                break;
        }
    }
    //对齐时调用
    public void logic36(){
        switch (direction){
            case UP:
                pointY=(pointY+50-1)%50;
                break;
            case DOWN:
                pointY=(pointY+50+1)%50;
                break;
            case LEFT:
                pointX=(pointX+30-1)%30;
                break;
            case RIGHT:
                pointX=(pointX+30+1)%30;
                break;
        }
    }
    //改变方向
    public void changeDir(int dir){
        switch (dir){
            case 0:
                break;
            case 1:
                direction=UP;
                break;
            case 2:
                direction=RIGHT;
                break;
            case 3:
                direction=DOWN;
                break;
            case 4:
                direction=LEFT;
                break;
        }
    }
    //碰撞测试
    public boolean isConnectionWithEATER(Eater e){

        Bitmap eimage=e.image;
        //右边
        if(e.x>x+image.getWidth())
            return false;
        //下边
        if(e.y>y+image.getHeight())
            return false;
        //左边
        if(e.x+eimage.getWidth()<x)
            return false;
        //上边
        if(e.y+eimage.getHeight()<y)
            return false;

        return true;
    }
}
