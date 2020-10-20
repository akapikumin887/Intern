package com.example.deb.System;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import com.example.deb.Activity.GameActivity;
import com.example.deb.Activity.MainActivity;

import static android.content.Context.SENSOR_SERVICE;

public class StepCount
{
    private static int ttPhone;       //今回携帯側で管理している歩数
    private static int ltPhone;       //前回携帯側で管理していた歩数
    private static int thisTime;      //今回起動して何歩歩いたか(差)
    private static int lastTime;      //前回起動したときに何歩歩いていたか
    private static int all;           //今までで何歩歩いたか

    //保存用
    private static SharedPreferences phonePrefs;
    private static SharedPreferences allPrefs;
    private static SharedPreferences thisPrefs;

    public static void init()
    {
        //保存した情報の読み込み
        phonePrefs = GameActivity.getActivity().getSharedPreferences("phoneStep", Context.MODE_PRIVATE);
        ltPhone = phonePrefs.getInt("int",0);   //前回起動時に歩いていた距離(スマホ換算)

        allPrefs = GameActivity.getActivity().getSharedPreferences("allStep", Context.MODE_PRIVATE);
        lastTime = allPrefs.getInt("int",0);    //前回起動時に歩いていた歩数(人間換算)

        thisPrefs = GameActivity.getActivity().getSharedPreferences("countStep", Context.MODE_PRIVATE);
        thisTime = allPrefs.getInt("int",0);    //消費されるまで今回歩いた分を残し続けておく


        //再起動するとttPhoneの歩数が0になってしまうため確認を入れて少しでも誤差を減らす
        //しかしこれでも再起動前の歩数は失われてしまう
        if(ttPhone < ltPhone)
            all = ttPhone + ltPhone;
        else
        {
            if(ltPhone == 0)    //初回起動時は0歩スタートでなければならない
                all = 0;
            else
                all = ttPhone - ltPhone;
        }

        thisTime = all - lastTime;
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

        editor = thisPrefs.edit();
        editor.putInt("int",thisTime);
        editor.apply();
    }

    //ゲッターとセッター
    public static void setTtPhone(float f){ttPhone = (int)f;}

    public static int getAll(){return all;}
    public static int getThisTime(){return thisTime;}
    public static void setThisTime(int num){thisTime = num;}
}
