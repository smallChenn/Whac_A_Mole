package com.example.eatbits;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by laper sfacg on 2016/7/20.
 */
public class Eater {

    public static final int UP=1;
    public static final int RIGHT=2;
    public static final int DOWN=3;
    public static final int LEFT=4;
    int pointX,pointY;
    int x,y;
    int direction;
    int life;
    Bitmap image;

    Bitmap EATER_IMAGE_1;
    Bitmap EATER_IMAGE_2;
    Bitmap EATER_U_1;
    Bitmap EATER_U_2;
    Bitmap EATER_R_1;
    Bitmap EATER_R_2;
    Bitmap EATER_D_1;
    Bitmap EATER_D_2;
    Bitmap EATER_L_1;
    Bitmap EATER_L_2;
    Bitmap reset;
    int score;


    public Eater(Context context) {

       reset= BitmapFactory.decodeResource(context.getResources(),R.drawable.reset);

        EATER_U_1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.eater_u_1);
        EATER_U_2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.eateru_2);
        EATER_R_1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.eater_r_1);
        EATER_R_2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.eater_r_2);
        EATER_D_1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.eater_d_1);
        EATER_D_2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.eater_d_2);
        EATER_L_1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.eater_l_1);
        EATER_L_2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.eater_l_2);

        EATER_IMAGE_1 = EATER_U_1;
        EATER_IMAGE_2 = EATER_U_2;
        image = EATER_IMAGE_1;
        pointX=12;
        pointY=32;
        x=432;
        y=1152;
        direction=UP;
        life=1;
        isShow=true;
        score=0;
    }



    public void draw(Canvas canvas, Paint paint) {
        paint.setTextSize(72);
        paint.setColor(Color.WHITE);
        canvas.drawText("分数:"+score, 108, 108, paint);

        if (isShow) {
            image = EATER_IMAGE_2;
        } else {
            image = EATER_IMAGE_1;
        }
        isShow = !isShow;
        if(life==0){
            paint.setTextSize(120);
            paint.setColor(Color.WHITE);
            canvas.drawText("GAME OVER!", 150, 500, paint);

            canvas.drawBitmap(reset,
                    520-reset.getWidth()/2,
                    800-reset.getHeight()/2,
                    paint);

        }else if(life==2){
            paint.setTextSize(120);
            paint.setColor(Color.WHITE);
            canvas.drawText("GAME WIN!", 150, 500, paint);

            canvas.drawBitmap(reset,
                    520-reset.getWidth()/2,
                    800-reset.getHeight()/2,
                    paint);
        }
        else
            canvas.drawBitmap(image, x, y, paint);
    }

    boolean isShow;

    public void logic() {
        switch (direction) {
            case UP:
                y=(y-6+1800)%1800;
                break;
            case DOWN:
                y=(y+6+1800)%1800;
                break;
            case LEFT:
                x=(x-6+1080)%1080;
                break;
            case RIGHT:
                x=(x+6+1080)%1080;
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
                EATER_IMAGE_1 = EATER_U_1;
                EATER_IMAGE_2 = EATER_U_2;
                break;
            case 2:
                direction=RIGHT;
                EATER_IMAGE_1 = EATER_R_1;
                EATER_IMAGE_2 = EATER_R_2;
                break;
            case 3:
                direction=DOWN;
                EATER_IMAGE_1 = EATER_D_1;
                EATER_IMAGE_2 = EATER_D_2;
                break;
            case 4:
                direction=LEFT;
                EATER_IMAGE_1 = EATER_L_1;
                EATER_IMAGE_2 = EATER_L_2;
                break;
        }
    }
    //重玩按钮点击操作
    public void againButtonClick(Context context,MotionEvent e){
        if(e.getY()>800-reset.getHeight()/2 &&
                e.getY()<800+reset
                        .getHeight()/2&&e.getX()>520-reset.getWidth()/2&&e.getX()<520+reset.getWidth()/2){
            if(e.getAction()==MotionEvent
                    .ACTION_UP){
                Intent i=new Intent();
                i.setClass(context, MainActivity.class);
                context.startActivity(i);
                ((MainActivity)context).finish();
            }
        }
    }

}
