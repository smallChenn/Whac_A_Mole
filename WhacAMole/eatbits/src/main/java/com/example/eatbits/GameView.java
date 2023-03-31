package com.example.eatbits;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by laper sfacg on 2016/7/20.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;
    private DrawThread drawThread;
    private GestureDetector mGestureDetector;

    public GameView(Context context) {
        super(context);
        init();
    }

    private void init() {
        holder = this.getHolder();
        holder.addCallback(this);
        drawThread = new DrawThread(getContext(),holder);//创建一个绘图线程
        mGestureDetector = new GestureDetector(getContext(), new MyGestureListener() );
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getPointerCount() > 1) {
                    return false;
                }
                mGestureDetector.onTouchEvent(motionEvent);
                return true;

            }
        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        drawThread.isRun = true;
        drawThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        drawThread.isRun = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mGestureDetector.onTouchEvent(event))
            return true;
        else
            return false;
    }
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            drawThread.onTouchEvent(e1,e2);
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            drawThread.onTouchEvent2(e);
            return true;
        }
    }
}