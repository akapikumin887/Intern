package com.example.deb.System;


import android.os.SystemClock;

public class FPSManager
{
//マクロ定義
    private static final int LATE_NORMAL = 1;

    private static final int FPS = 60;                  //目標fps
    private static final long FRAME = (1000 / FPS);     //1フレームの理論値 ミリ秒換算


    private static long nowTime = 0;        //現在経過時間
    private static long oldTime = 0;        //過去経過時間
    private static long errorTime = 0;      //誤差時間
    private static long sleepTime = 0;      //休める時間

    private  static long realFrame = 0;              //実際に1fに達しているかどうか
    private static boolean canFrame = false;         //update文にはいれるかどうかのフラグ

    private static int isLate = 0;                       //何かの拍子に遅延がひどくなった際、フレームを読み飛ばす


    //時間計算
    public static void calcFPS()
    {
        if(oldTime == 0) oldTime = SystemClock.uptimeMillis();  //初期値のままだと値が大きすぎてしまう

        //遅延を確認したら今回は許す
        if(isLate < 0)
        {
            oldTime = SystemClock.uptimeMillis(); //過去時刻を取得 次fで使用

            isLate++;
            return;
        }

        nowTime = SystemClock.uptimeMillis();   //現在時刻の更新
        realFrame += nowTime - oldTime;         //経過時刻を取得
        oldTime = SystemClock.uptimeMillis();   //過去時刻を取得 次fで使用

        sleepTime = FRAME - errorTime;   //休める時間の取得

        if(realFrame > FRAME)     //実際に1fに満たしたら
        {
            int i = 0;
            isLate = LATE_NORMAL;
            //errorTimeが16より大きくならないようにする
            do
            {
                i++;
                isLate--;                       //誤差を確認した分だけ次fを飛ばす
                errorTime = realFrame - (FRAME * i);  //誤差
            }while(errorTime > FRAME);

            canFrame = true;
            realFrame = 0;
        }
    }

    public static void drowsy(long time)
    {
            //sleepに入ってる値デカすぎ問題
            SystemClock.sleep(time);   //ミリ秒を秒に変換
    }

    //ゲッターとセッター
    public static long getSleepTime(){return  sleepTime;}
    public static boolean getcanUpdate(){return canFrame;}
    public static void setcanUpdate(boolean canUpdate){canFrame = canUpdate;}
    public static void setIsLate(int late){isLate = late;}
}
