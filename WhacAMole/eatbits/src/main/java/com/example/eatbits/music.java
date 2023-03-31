package com.example.eatbits;



import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import com.example.eatbits.R;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

/**
 * 声音控制类
 * @author wyf
 *
 */
public class music {

    private static SoundPool soundPool;

    private static boolean soundSt = true; //音效开关
    private static Context context;

    private static Map<Integer,Integer> soundMap; //音效资源id与加载过后的音源id的映射关系表

    /**
     * 初始化方法
     * @param c
     */
    public static void init(Context c)
    {
        context = c;
        initSound();
    }

    //初始化音效播放器
    private static void initSound()
    {
        soundPool = new SoundPool(10,AudioManager.STREAM_MUSIC,100);
        soundMap = new HashMap<Integer,Integer>();
        soundMap.put(R.raw.eated, soundPool.load(context, R.raw.eated, 1));
        soundMap.put(R.raw.chi, soundPool.load(context, R.raw.chi, 1));
        soundMap.put(R.raw.die, soundPool.load(context, R.raw.die, 1));
    }


    /**
     * 播放音效
     * @param resId 音效资源id
     */
    public static void playSound(int resId)
    {
        if(soundSt == false)
            return;

        Integer soundId = soundMap.get(resId);
        if(soundId != null)
            soundPool.play(soundId, 1, 1, 1, 0, 1);
    }


    /**
     * 获得音效开关状态
     * @return
     */
    public static boolean isSoundSt() {
        return soundSt;
    }

    /**
     * 设置音效开关
     * @param soundSt
     */
    public static void setSoundSt(boolean soundSt) {
        music.soundSt = soundSt;
    }


    public static void eatedsound() {
        playSound(R.raw.eated);
    }
    public static  void chisound(){
        playSound(R.raw.chi);
    }
    public static  void diesound(){
        playSound(R.raw.die);
    }
}

