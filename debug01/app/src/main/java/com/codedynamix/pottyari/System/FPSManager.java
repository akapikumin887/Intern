package com.codedynamix.pottyari.System;


import android.os.SystemClock;

import java.util.LinkedList;

public class FPSManager
{
    private static final int FPS = 60;                          //目標fps
    private static final long FRAME = (1000 << 16) / FPS;       //1フレームの理論値 ミリ秒換算
    private static LinkedList<Long> elapsedTimeList;            //1フレームを60つ保存してfpsを求めるためのコンテナ

    private static long nowTime;        //現在経過時間
    private static long oldTime;        //過去経過時間
    private static long errorTime;      //誤差時間
    private static long sleepTime;      //休める時間

    //private static int isLate = 0;        //何かの拍子に遅延がひどくなった際、フレームを読み飛ばす

    private static long first,last;         //1f全体の始めと終わりを取得して実際のずれを確認する

    //init
    public static void init()
    {
        nowTime = 0;
        oldTime = SystemClock.uptimeMillis();   //初期化
        errorTime = 0;
        sleepTime = 0;

        elapsedTimeList = new LinkedList<Long>();
        for(int i = 0; i < FPS; i++)
            elapsedTimeList.add(1l);            //全部の要素に0を入れておく
        first = 0l;
        last  = 0l;
    }

    //時間計算
    public static void calcFPS()
    {
        first   = SystemClock.uptimeMillis() << 16;
        oldTime = SystemClock.uptimeMillis() << 16;
    }

    public static void drowsy()
    {
        nowTime = SystemClock.uptimeMillis() << 16;
        long l = nowTime - oldTime;
        sleepTime = FRAME - l - errorTime;
        oldTime =nowTime;

        if(sleepTime < 0x20000) sleepTime = 0x20000;

        SystemClock.sleep(sleepTime >> 16); //休憩

        nowTime = SystemClock.uptimeMillis() << 16;
        errorTime = nowTime - oldTime - sleepTime;

        last = SystemClock.uptimeMillis() << 16;

        long number = last - first;
        elapsedTimeList.add(number >> 16);
        elapsedTimeList.remove(0);
    }

    //ゲッターとセッター
    public static long getFPS()
    {
        long num = 0;
        for(int i = 0; i < FPS; i++)
            num += elapsedTimeList.get(i);
        return (long)(num / 16.6666);
    }
}
