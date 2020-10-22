package com.example.deb.Object;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.deb.Activity.GameActivity;

public class HeroStatus
{
    private static int[] status;    //勇者のステータス

    private static SharedPreferences[] statusPrefs;  //勇者のステータスを格納しておく

//マクロ定義
    private static final int STATUS_TYPE_MAX = 8;

    public static void init()
    {
        //値の格納 0がLv 1がHp 2がAt 3がWp 4がMaxHp
        statusPrefs = new SharedPreferences[STATUS_TYPE_MAX];
        statusPrefs[0] = GameActivity.getActivity().getSharedPreferences("Lv", Context.MODE_PRIVATE);       //レベル
        statusPrefs[1] = GameActivity.getActivity().getSharedPreferences("Hp", Context.MODE_PRIVATE);       //体力
        statusPrefs[2] = GameActivity.getActivity().getSharedPreferences("At", Context.MODE_PRIVATE);       //攻撃力
        statusPrefs[3] = GameActivity.getActivity().getSharedPreferences("wp", Context.MODE_PRIVATE);       //武器の火力
        statusPrefs[4] = GameActivity.getActivity().getSharedPreferences("MaxHp", Context.MODE_PRIVATE);    //最大体力
        statusPrefs[5] = GameActivity.getActivity().getSharedPreferences("Heal", Context.MODE_PRIVATE);     //野菜ジュースの数
        statusPrefs[6] = GameActivity.getActivity().getSharedPreferences("Revive", Context.MODE_PRIVATE);   //栄養ドリンクの数
        statusPrefs[7] = GameActivity.getActivity().getSharedPreferences("wpLv", Context.MODE_PRIVATE);     //武器のレベル


        status = new int[STATUS_TYPE_MAX];
        status[0] = statusPrefs[0].getInt("int",1);
        status[1] = statusPrefs[1].getInt("int",30);
        status[2] = statusPrefs[2].getInt("int",9);
        status[3] = statusPrefs[3].getInt("int",1);
        status[4] = statusPrefs[4].getInt("int",30);
        status[5] = statusPrefs[5].getInt("int",0);
        status[6] = statusPrefs[6].getInt("int",0);
        status[7] = statusPrefs[7].getInt("int",1);
    }

    public static void save()
    {
        SharedPreferences.Editor editor;
        //値を保存しておく
        for(int i = 0; i < STATUS_TYPE_MAX; i++)
        {
            editor = statusPrefs[i].edit();
            editor.putInt("int",status[i]);
            editor.apply();
        }
    }

    public static int getLv(){return status[0];}
    public static int getHp(){return status[1];}
    public static int getAt(){return status[2];}
    public static int getWp(){return status[3];}
    public static int getMaxHp(){return status[4];}
    public static int getHealCnt(){return status[5];}
    public static int getReviveCnt(){return status[6];}

    //これは武器と攻撃力の合算 基本ステータス表示はこれを使う
    public static int getAttack(){return status[2] + status[3];}

    public static void setLv(int lv)        {status[0] = lv;}
    public static void setHp(int hp)        {status[1] = hp;}
    public static void setAt(int at)        {status[2] = at;}
    public static void setWp(int wp)        {status[3] = wp;}
    public static void setMaxHp(int max)    {status[4] = max;}
    public static void setHealCnt(int hl)   {status[5] = hl;}
    public static void setReviveCnt(int rv) {status[6] = rv;}
}
