package com.example.eatbits;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

/**
 * Created by laper sfacg on 2016/7/20.
 */
public class DrawThread extends Thread {
    private SurfaceHolder holder;
    public boolean isRun;
    private final GameLogic logic;


    public DrawThread(Context context, SurfaceHolder holder) {
        this.holder = holder;
        isRun = true;
        logic = new GameLogic(context);

    }



    @Override
    public void run() {
        while (isRun) {
            Canvas c = null;
            try {
                synchronized (holder) {
                    c = holder.lockCanvas();//锁定画布，一般在锁定后就可以通过其返回的画布对象Canvas，在其上面画图等操作了。
                    logic.logic();
                    logic.draw(c);

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (c != null) {
                    holder.unlockCanvasAndPost(c);//结束锁定画图，并提交改变。
                }
            }
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void onTouchEvent(MotionEvent event1,MotionEvent event2) {
        //滑动响应
        logic.onTouchEvent(event1,event2);
    }

    public void onTouchEvent2(MotionEvent e) {
        //点击响应
        logic.onTouchEvent2(e);
    }
}
