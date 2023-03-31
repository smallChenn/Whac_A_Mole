package com.example.eatbits;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by laper sfacg on 2016/7/20.
 */
public class GameLogic {
    Paint paint;
    Eater eater;
    music m;
    BigBean bigBit;
    BG bg;
    int dir;
    int ecount,gcount,bcount;
    private MediaPlayer player;
    //所有的墙和豆子
    int[][] allWalls = {
            {2,2,2,2,2,2,2,2,2,2,0,1,1,2,0,1,1,0,0,1,1,1,0,0,1,1,0,0,1,1},
            {2,0,0,0,0,0,0,0,0,2,0,1,1,2,0,1,1,0,0,1,1,1,0,0,1,1,0,0,1,1},
            {2,0,1,1,1,1,1,1,1,2,0,1,1,2,0,1,1,1,1,1,1,1,0,0,1,1,0,0,1,1},
            {2,0,1,1,1,1,1,1,1,2,0,1,1,2,0,1,1,1,1,1,1,1,0,0,1,1,0,0,1,1},
            {2,0,1,1,2,2,2,2,2,2,0,1,1,2,2,2,2,2,2,2,2,2,2,0,1,1,0,0,1,1},
            {2,0,1,1,2,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,2,0,0,1,1,0,0,1,1},
            {2,0,1,1,2,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,0,1,1,1,0,0,1,1},
            {2,0,1,1,2,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,0,1,1,1,0,0,1,1},
            {2,0,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0},
            {0,0,1,1,2,0,0,0,2,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,2,0,1,1,2,0,1,1,1,1,1,1,1,2,0,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,2,0,1,1,2,0,1,1,1,1,1,1,1,2,0,1,1,1,1,1,1,1,1,1,1,1},
            {0,0,0,0,2,0,1,1,2,0,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0},
            {0,0,0,0,2,0,1,1,2,0,1,1,2,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,2,0,1,1,2,0,1,1,2,0,1,1,1,1,1,2,0,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,2,0,1,1,2,0,1,1,2,0,1,1,1,1,1,2,0,1,1,1,1,1,1,1,1,1},
            {0,0,0,0,2,0,1,1,2,0,1,1,2,0,1,1,2,0,0,2,2,2,2,2,2,2,2,2,2,0},
            {0,0,0,0,0,0,1,1,2,0,1,1,2,0,1,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1,2,0,1,1,2,0,1,1,2,0,1,1,1,1,1,1,1,1,1,1,0,0},
            {1,1,1,1,1,1,1,1,2,0,1,1,2,0,1,1,2,0,1,1,1,1,1,1,1,1,1,1,0,0},
            {2,2,2,2,2,2,2,2,2,2,2,2,2,0,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,2,0,1,1,2,0,0,0,0,0,2,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,2,0,1,1,2,0,1,1,1,1,2,0,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,2,0,1,1,2,0,1,1,1,1,2,0,1,1,1,1,1,1},
            {2,2,2,2,2,2,2,2,2,0,1,1,2,0,1,1,2,0,1,1,1,1,2,0,1,1,0,0,0,0},
            {0,0,2,0,0,0,0,0,2,0,1,1,2,0,1,1,2,0,1,1,1,1,2,0,1,1,0,0,0,0},
            {1,1,2,0,1,1,1,1,2,0,1,1,2,0,1,1,2,0,1,1,1,1,2,0,1,1,0,0,1,1},
            {1,1,2,0,1,1,1,1,2,0,1,1,2,0,1,1,2,0,1,1,1,1,2,0,1,1,0,0,1,1},
            {1,1,2,0,1,1,0,0,2,2,2,2,2,0,1,1,2,0,1,1,1,1,2,0,1,1,0,0,1,1},
            {1,1,2,0,1,1,0,0,0,0,0,0,2,0,0,0,2,0,0,0,0,0,2,0,0,0,0,0,1,1},
            {1,1,2,0,1,1,0,0,1,1,1,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
            {1,1,2,0,1,1,0,0,1,1,1,1,2,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,2,0,1,1,0,0,1,1,1,1,2,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,2,0,1,1,0,0,0,0,0,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,1,1},
            {1,1,2,0,1,1,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,2,0,1,1},
            {0,0,2,0,1,1,1,1,1,1,1,1,1,1,1,1,2,0,1,1,1,1,1,1,1,1,2,0,0,0},
            {0,0,2,0,1,1,1,1,1,1,1,1,1,1,1,1,2,0,1,1,1,1,1,1,1,1,2,0,0,0},
            {1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,1,1,2,2,2,2,2,2,2,0,1,1},
            {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,1,1,2,0,0,0,0,0,0,0,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,2,0,1,1,2,0,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,2,0,1,1,2,0,1,1,1,1,1,1,1,1},
            {1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,1,1,2,0,0,0,1,1,0,0,1,1},
            {1,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,2,0,0,0,1,1,2,0,1,1},
            {1,1,2,0,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,2,0,0,0,1,1,2,0,1,1},
            {1,1,2,0,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,2,2,2,0,1,1,2,0,1,1},
            {1,1,2,0,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,0,0,2,0,1,1,2,0,1,1},
            {1,1,2,0,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,2,0,1,1,2,0,1,1},
            {1,1,2,0,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,2,0,1,1,2,0,1,1},
            {1,1,2,0,1,1,1,1,1,0,0,1,1,2,2,2,2,2,2,2,2,2,2,0,1,1,2,0,1,1},
            {1,1,2,0,1,1,1,1,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,1}};
    //所以的墙
    ArrayList<Wall> allwalls=new
            ArrayList<Wall>();
    //所以的豆子
    ArrayList<Bean> allbeans=new
            ArrayList<Bean>();
    int[][] allGhosts={
            {0,0},
            {0,9},
            {5,21},
            {36,25},
            {12,0},
            {24,28},
            {48,2},
            {48,26}
    };
    //所有的鬼
    ArrayList<Ghost> allghosts=new
            ArrayList<Ghost>();
    int[][] allBigBeans={
            {0,17},
            {12,0},
            {42,26}
    };
    //所有的大豆子
    ArrayList<BigBean> allbigbeans=new
            ArrayList<BigBean>();

    private Context context;
    public Context getContext() {
        return context;
    }

    public GameLogic(Context context) {
        this.context=context;
        bg = new BG(context);
        paint = new Paint();
        eater = new Eater(context);
        m.init(context);
        ecount=0;
        gcount=0;
        bcount=1000;
        dir=0;
        for (int i = 0; i < 50; i++) {
            for(int j = 0; j < 30; j++) {
                if(allWalls[i][j]==1) {
                    addWall(i,j);
                }
                else if(allWalls[i][j]==2){
                    addBean(i,j);
                }
            }
        }
        for(int i = 0;i < 8; i++)
            addGhost(allGhosts[i][0],allGhosts[i][1]);
        for(int i = 0;i < 3; i++)
            addBigBean(allBigBeans[i][0],allBigBeans[i][1]);
    }
    public void draw(Canvas canvas) {
        // 绘制游戏的界面
        bg.draw(canvas, paint);
        // 绘制所有的墙和豆子
        for (int i = 0; i < allwalls.size(); i++) {
            Wall wall = allwalls.get(i);
            wall.draw(canvas, paint);
        }
        for (int i = 0; i < allbeans.size(); i++) {
            Bean bean = allbeans.get(i);
            bean.draw(canvas, paint);
        }
        for (int i = 0; i < allbigbeans.size(); i++){
            BigBean bigbean = allbigbeans.get(i);
            bigbean.draw(canvas, paint);
        }
        // 绘制所有的鬼
        for (int i = 0; i < allghosts.size(); i++) {
            Ghost ghost3 = allghosts.get(i);
            ghost3.draw(canvas, paint);
        }
        eater.draw(canvas, paint);
    }
    int fuhuo=0;
    public void logic() {

        if(ecount==6) {
            eater.logic36();
            ecount = 0;
        }
        if(gcount==36/allghosts.get(0).speed){
            if(fuhuo!=0) {
                Ghost g = new Ghost(getContext(), 0, 0);
                g.caneat=1;
                allghosts.add(g);
                fuhuo=0;
            }
            for (int i = 0; i < allghosts.size() ; i++) {
                allghosts.get(i).logic36();
            }
            gcount=0;
        }
        if(bcount==180){
            for(int i = 0; i< allghosts.size();i++){
                allghosts.get(i).caneat=0;
                bcount = 1000;
            }
        }
        if(ecount==0) {
            //改变方向
            if(dir!=0) {
                eater.changeDir(dir);
                dir=0;
            }

            //判断面前是否有豆子
            if (isBean()){
                eater.score++;
                removeBean();
                if(allbeans.size()==0)
                    eater.life=2;
            }

            //判断面前是否是大豆子
            if(isBigBean()){
                for(int i = 0; i< allghosts.size();i++){
                    allghosts.get(i).caneat=1;
                }
                eater.score+=20;
                removeBigBean();
                bcount = 0;
                m.eatedsound();
            }

            //判断面前是否有墙
            if (!isWall()) {
                eater.logic();
                ecount++;
            }
        }
        else {
            eater.logic();
            ecount++;
        }
        bcount++;

        if(gcount==0){
            for (int i = 0; i < allghosts.size() ; i++) {
                Ghost ghost = allghosts.get(i);
                ArrayList<Integer> dirs = new ArrayList<>();
                int gdir=0;
                if(!isnear(ghost)) {
                    //获取所以可以走的方向
                    for (int j = 1; j <= 4 ; j++) {
                        if(!isWall2(ghost,j))
                            dirs.add(j);
                    }
                    //去掉来时的方向
                    for (int j = 0; j < dirs.size(); j++) {
                        if (ghost.direction == 2) {
                            if (dirs.get(j).intValue() == 4)
                                dirs.remove(j);
                        } else {
                            if (dirs.get(j).intValue() == (ghost.direction + 2) % 4)
                                dirs.remove(j);
                        }
                    }
                    //一般不回头
                    if (dirs.size() >= 1) {
                        gdir = (int) Math.floor(Math.random() * dirs.size());
                        ghost.changeDir(dirs.get(gdir).intValue());
                    }
                    //死路回头
                    if (dirs.size() == 0) {
                        if (ghost.direction == 2)
                            ghost.changeDir(4);
                        else
                            ghost.changeDir((ghost.direction + 2) % 4);
                    }
                }
                else{
                    if(eater.pointX<ghost.pointX&&eater.pointY<ghost.pointY);
                    if(eater.pointX<ghost.pointX&&eater.pointY>ghost.pointY);
                    if(eater.pointX>ghost.pointX&&eater.pointY<ghost.pointY);
                    if(eater.pointX>ghost.pointX&&eater.pointY>ghost.pointY);

                    if(eater.pointX==ghost.pointX&&eater.pointY>ghost.pointY);
                    if(eater.pointX==ghost.pointX&&eater.pointY<ghost.pointY);
                    if(eater.pointX>ghost.pointX&&eater.pointY==ghost.pointY);
                    if(eater.pointX<ghost.pointX&&eater.pointY==ghost.pointY);

                }
                ghost.logic();
                //是否碰到鬼
                if(ghost.isConnectionWithEATER(eater))
                    if(ghost.caneat==0) {
                        if(eater.life==1) {
                            eater.life = 0;
                            m.diesound();
                        }
                    }
                    else {
                        allghosts.remove(i);
                        fuhuo++;
                        eater.score+=40;
                        m.chisound();
                        i--;
                    }
            }
            gcount++;

        }
        else{
            for (int i = 0; i < allghosts.size() ; i++) {
                Ghost ghost4 =  allghosts.get(i);
                ghost4.logic();
            }
            gcount++;
        }
    }
    public void onTouchEvent(MotionEvent event1,MotionEvent event2) {

        float x1 = event1.getX();
        float x2 = event2.getX();
        float y1 = event1.getY();
        float y2 = event2.getY();
        if(x1==x2) {
            if (y1 < y2)
                dir = 4;
            else
                dir = 2;
        }
        else if(x1<x2) {
            if ((y2 - y1) / (x2 - x1) > 1)
                dir = 3;
            else if ((y2 - y1) / (x2 - x1) < -1)
                dir = 1;
            else
                dir = 2;
        }
        else {
            if ((y2 - y1) / (x2 - x1) > 1)
                dir = 1;
            else if ((y2 - y1) / (x2 - x1) < -1)
                dir = 3;
            else
                dir = 4;
        }
    }
    public void onTouchEvent2(MotionEvent e) {
        if(eater.life!=1)
            eater.againButtonClick(getContext(), e);
    }
    public void addWall(int x,int y){
        Wall walls = new Wall(getContext(),y,x);
        allwalls.add(walls);
    }
    public void addBean(int x,int y){
        Bean beans = new Bean(getContext(),y,x);
        allbeans.add(beans);
    }
    private void removeBean(){
        int x,y;
        x=y=100;
        switch (eater.direction){
            case 1:
                x = (eater.pointX + 30)%30;
                y = (eater.pointY + 50 - 1)%50;
                break;
            case 2:
                x = (eater.pointX + 30 + 1)%30;
                y = (eater.pointY + 50)%50;
                break;
            case 3:
                x = (eater.pointX + 30)%30;
                y = (eater.pointY + 50 + 1)%50;
                break;
            case 4:
                x = (eater.pointX + 30 - 1)%30;
                y = (eater.pointY + 50)%50;
                break;
        }

        for (int i = 0; i < allbeans.size(); i++){
            if(allbeans.get(i).pointX==x&&allbeans.get(i).pointY==y) {
                allbeans.remove(i);
            }
        }
    }
    public void removeBigBean(){
        for(int i = 0; i<allbigbeans.size(); i++)
            if(allbigbeans.get(i).pointX==eater.pointX&&allbigbeans.get(i).pointY==eater.pointY)
                allbigbeans.remove(i);
    }
    public void addGhost(int x,int y){
        Ghost ghost2 = new Ghost(getContext(),y,x);
        allghosts.add(ghost2);
    }

    public void addBigBean(int x,int y){
        BigBean bigBean2 = new BigBean(getContext(),y,x);
        allbigbeans.add(bigBean2);
    }

    public boolean isnear(Ghost g){
        int xd,yd;
        xd=yd=100;
        if(eater.pointX>=g.pointX)
            xd = eater.pointX-g.pointX;
        else
            xd = g.pointX-eater.pointX;
        if(eater.pointY>=g.pointY)
            yd = eater.pointY-g.pointY;
        else
            yd = g.pointY-eater.pointY;
        if((xd+yd)<8)
            return false;
        else
            return false;
    }
    private boolean isWall(){
        switch (eater.direction) {
            case 1:
                if (allWalls[(eater.pointY + 50 - 1) % 50][(eater.pointX + 30) % 30] != 1
                        && allWalls[(eater.pointY + 50 - 1) % 50][(eater.pointX + 30 + 1) % 30] != 1) {
                    return false;
                }
                break;
            case 2:
                if (allWalls[(eater.pointY + 50) % 50][(eater.pointX + 30 + 2) % 30] != 1
                        && allWalls[(eater.pointY + 50 + 1) % 50][(eater.pointX + 30 + 2) % 30] != 1) {
                    return false;
                }
                break;
            case 3:
                if (allWalls[(eater.pointY + 50 + 2) % 50][(eater.pointX + 30) % 30] != 1
                        && allWalls[(eater.pointY + 50 + 2) % 50][(eater.pointX + 30 + 1) % 30] != 1) {
                    return false;
                }
                break;
            case 4:
                if (allWalls[(eater.pointY + 50) % 50][(eater.pointX + 30 - 1) % 30] != 1
                        && allWalls[(eater.pointY + 50 + 1) % 50][(eater.pointX + 30 - 1) % 30] != 1) {
                    return false;
                }
                break;
        }
        return true;
    }
    private boolean isWall2(Ghost g,int direct){
        switch (direct) {
            case 1:
                if (allWalls[(g.pointY + 50 - 1) % 50][(g.pointX + 30) % 30] != 1
                        && allWalls[(g.pointY + 50 - 1) % 50][(g.pointX + 30 + 1) % 30] != 1) {
                    return false;
                }
                break;
            case 2:
                if (allWalls[(g.pointY + 50) % 50][(g.pointX + 30 + 2) % 30] != 1
                        && allWalls[(g.pointY + 50 + 1) % 50][(g.pointX + 30 + 2) % 30] != 1) {
                    return false;
                }
                break;
            case 3:
                if (allWalls[(g.pointY + 50 + 2) % 50][(g.pointX + 30) % 30] != 1
                        && allWalls[(g.pointY + 50 + 2) % 50][(g.pointX + 30 + 1) % 30] != 1) {
                    return false;
                }
                break;
            case 4:
                if (allWalls[(g.pointY + 50) % 50][(g.pointX + 30 - 1) % 30] != 1
                        && allWalls[(g.pointY + 50 + 1) % 50][(g.pointX + 30 - 1) % 30] != 1) {
                    return false;
                }
                break;
        }
        return true;
    }
    private boolean isBean(){
        switch (eater.direction) {
            case 1:
                if (allWalls[(eater.pointY + 50 - 1)%50][(eater.pointX + 30)%30] == 2) {
                    allWalls[(eater.pointY + 50 - 1)%50][(eater.pointX + 30)%30] = 0;
                    return true;
                }
                break;
            case 2:
                if (allWalls[(eater.pointY + 50)%50][(eater.pointX + 30 + 1)%30] == 2) {
                    allWalls[(eater.pointY + 50)%50][(eater.pointX + 30 + 1)%30] = 0;
                    return true;
                }
                break;
            case 3:
                if (allWalls[(eater.pointY + 50 + 1)%50][(eater.pointX + 30)%30] == 2) {
                    allWalls[(eater.pointY + 50 + 1)%50][(eater.pointX + 30)%30] = 0;
                    return true;
                }
                break;
            case 4:
                if (allWalls[(eater.pointY + 50)%50][(eater.pointX + 30 - 1)%30] == 2) {
                    allWalls[(eater.pointY + 50)%50][(eater.pointX + 30 - 1)%30] = 0;
                    return true;
                }
                break;
        }
        return false;
    }
    private boolean isBigBean(){
        for(int i = 0;i < allbigbeans.size();i++)
            if(eater.pointX==allbigbeans.get(i).pointX
                    &&eater.pointY==allbigbeans.get(i).pointY)
                return true;
        return false;
    }
}
