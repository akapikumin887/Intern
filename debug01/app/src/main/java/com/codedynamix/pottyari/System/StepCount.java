package com.codedynamix.pottyari.System;

import android.content.Context;
import android.content.SharedPreferences;

import com.codedynamix.pottyari.Activity.GameActivity;

public class StepCount
{
    private static int ttPhone;       //今回携帯側で管理している歩数
    private static int ltPhone;       //前回携帯側で管理していた歩数
    private static int lastTime;      //前回起動したときに何歩歩いていたか
    private static int all;           //今までで何歩歩いたか

    private static int ttPoint;       //歩いた際に得られるポイント
    private static int ttStep;        //進行画面を開いたときに進む距離

    //保存用
    private static SharedPreferences phonePrefs;
    private static SharedPreferences allPrefs;
    private static SharedPreferences thisStepPrefs;
    private static SharedPreferences pointPrefs;

    private static boolean isRead = false;

    public static void init()
    {
        //既に呼び出していたら省略する
        if(isRead)
            return;

        //保存した情報の読み込み
        phonePrefs = GameActivity.getActivity().getSharedPreferences("phoneStep", Context.MODE_PRIVATE);
        ltPhone = phonePrefs.getInt("int",0);   //前回起動時に歩いていた歩数(スマホ換算)

        allPrefs = GameActivity.getActivity().getSharedPreferences("allStep", Context.MODE_PRIVATE);
        lastTime = allPrefs.getInt("int",0);    //前回起動時に歩いていた歩数(人間換算)

        thisStepPrefs = GameActivity.getActivity().getSharedPreferences("countStep", Context.MODE_PRIVATE);
        ttStep = thisStepPrefs.getInt("int",0);    //消費されるまでゲーム画面で歩く歩数を記録しておく

        pointPrefs = GameActivity.getActivity().getSharedPreferences("countPoint", Context.MODE_PRIVATE);
        ttPoint = pointPrefs.getInt("int",0);    //消費されるまで今回得たポイントを記録しておく

        //Sensorが呼び出されなかったら今回は歩いてないことにする
        if(ttPhone < 0)
            ttPhone = ltPhone;

        //今回歩いた歩数を出す
        int n;

        if(ttPhone < ltPhone)
            n = ttPhone + ltPhone;  //再起動時の譲歩
        else
        {
            if(ltPhone == 0)    //初回起動時は0歩スタートでなければならない
                n = 0;
            else
                n = ttPhone - ltPhone;  //一般的にはここを通る
        }

        //デバッグ用
        if(n < 0)
            n = 0;

        //このアプリを起動してから今までの総歩数を出す
        all = n + lastTime;

        ttStep += n;
        ttPoint += n;

        isRead = true;
        //ttPoint = 0;
    }

    public static void save()
    {
        //データを保存する
        SharedPreferences.Editor editor = allPrefs.edit();
        editor.putInt("int",all);
        editor.apply();

        editor = phonePrefs.edit();
        editor.putInt("int", ttPhone);
        editor.apply();

        editor = thisStepPrefs.edit();
        editor.putInt("int",ttStep);
        editor.apply();

        editor = pointPrefs.edit();
        editor.putInt("int",ttPoint);
        editor.apply();
    }

    //ゲッターとセッター
    public static void setTtPhone(float f){ttPhone = (int)f;}

    public static int getAll(){return all;}

    public static int getTtPoint(){return ttPoint;}
    public static void resetTtPoint(){ttPoint = 0;}
    public static int getTtStep(){return ttStep;}
    public static void resetTtStep(){ttStep = 0;}
}
