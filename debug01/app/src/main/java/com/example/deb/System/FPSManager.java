package com.example.deb.System;


import android.os.SystemClock;

public class FPSManager
{
    private static final int fps = 60;                    //目標fps
    private static final long frame = (1000 / fps);     //1フレームの理論値 ミリ秒換算

    private static long nowTime = 0;        //現在経過時間
    private static long oldTime = 0;        //過去経過時間
    private static double errorTime = 0;      //誤差時間
    private static double sleepTime = 0.0;      //休める時間

    private  static long realFrame = 0;     //実際に1fに達しているかどうか
    private static boolean canFrame = false;         //update文にはいれるかどうかのフラグ

    private static boolean isLate = false;  //何かの拍子に遅延がひどくなった際、フレームを読み飛ばす


    static long n = 0;
    static long tttime = 0;
    public static void calcFPS()
    {
        n++;
        if(oldTime == 0) oldTime = SystemClock.uptimeMillis();  //初期値のままだと値が大きすぎてしまう

        //遅延を確認したら今回は許す
        if(isLate)
        {
            //nowTime = SystemClock.uptimeMillis(); //現在時刻の更新
            //realFrame += nowTime - oldTime;  //経過時刻を取得
            //double tmp = (double)realFrame / 1000;
            oldTime = SystemClock.uptimeMillis(); //過去時刻を取得 次fで使用

            isLate = false;
            if(n == 5)
            {
                isLate = false;
            }
            return;
        }

        nowTime = SystemClock.uptimeMillis(); //現在時刻の更新
        realFrame += nowTime - oldTime;  //経過時刻を取得
        //double tmp = (double)realFrame / 1000;
        oldTime = SystemClock.uptimeMillis(); //過去時刻を取得 次fで使用

        sleepTime = frame - errorTime;   //休める時間の取得

        if(n == 3)
        {
            tttime = realFrame;
        }

        if(n == 4)
        {
            n = realFrame - tttime;
        }

        if(realFrame > frame)     //実際に1fに満たしたら
        {

            canFrame = true;
            errorTime = realFrame - frame;  //誤差
            realFrame = 0;
        }
    }

    public static void drowsy(double time)
    {
            //sleepに入ってる値デカすぎ問題
/*
            if(n > 2)
            {
                n = 0;
            }
*/
            time *= 1000;
            SystemClock.sleep((long)time);
    }

    //ゲッターとセッター
    public static double getSleepTime(){return  sleepTime;}
    public static boolean getcanUpdate(){return canFrame;}
    public  static void setcanUpdate(boolean canUpdate){canFrame = canUpdate;}
    public static void setIsLate(boolean late){isLate = late;}
}
