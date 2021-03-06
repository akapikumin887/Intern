package com.codedynamix.pottyari.Object;

import android.content.Context;
import android.content.SharedPreferences;

import com.codedynamix.pottyari.Activity.GameActivity;

import java.util.Random;

public class EnemyStatus
{
    private static int[] status;    //てきのステータス

    private static SharedPreferences[] statusPrefs; //敵のステータスを格納しておく

//マクロ定義
    private static final int STATUS_TYPE_MAX = 6;   //hp at def 討伐数 ボス討伐数

    //保存した情報の呼び出し
    public static void init()
    {
        statusPrefs = new SharedPreferences[STATUS_TYPE_MAX];
        statusPrefs[0] = GameActivity.getActivity().getSharedPreferences("Ehp", Context.MODE_PRIVATE);       //敵の体力
        statusPrefs[1] = GameActivity.getActivity().getSharedPreferences("Eat", Context.MODE_PRIVATE);       //敵の攻撃力
        statusPrefs[2] = GameActivity.getActivity().getSharedPreferences("Edef", Context.MODE_PRIVATE);      //敵の防御力
        statusPrefs[3] = GameActivity.getActivity().getSharedPreferences("EMhp", Context.MODE_PRIVATE);      //敵の最大体力
        statusPrefs[4] = GameActivity.getActivity().getSharedPreferences("Edead", Context.MODE_PRIVATE);     //敵の倒された数
        statusPrefs[5] = GameActivity.getActivity().getSharedPreferences("EBdead", Context.MODE_PRIVATE);    //ボスの倒された数

        status = new int[STATUS_TYPE_MAX];

        for(int i = 0; i < STATUS_TYPE_MAX; i++)
            status[i] = statusPrefs[i].getInt("int",0);
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

    public static void setEnemy()
    {
        //敵が沸いた時の敵のステータス設定
        Random random = new Random();
        int eAddStatus = 0;

        for(int i = 0; i < status[4] + 1; i++)
        {
            eAddStatus += random.nextInt(5);
        }

        status[0] = status[3] = 30 * (status[5] + 1) + eAddStatus;
        status[1] = 5 + (status[5] * 2) + eAddStatus;
        status[2] = 5 + (status[5] * 2) + eAddStatus;

        save();
    }

    public static int  getEnemyHp(){return status[0];}
    public static void setEnemyHp(int hp){status[0] = hp;}

    public static int  getEnemyAt(){return status[1];}
    public static void setEnemyAt(int at){status[1] = at;}

    public static int  getEnemyDef(){return status[2];}
    public static void setEnemyDef(int def){status[2] = def;}

    public static int  getEnemyMaxHp(){return status[3];}
    public static void setEnemyMaxHp(int max){status[3] = max;}

    public static int  getEnemyDeadCount(){return status[4];}
    public static void setEnemyDeadCount(int dc){status[4] = dc;}

    public static int  getBossDeadCount(){return status[5];}
    public static void setBossDeadCount(int bdc){status[5] = bdc;}

}
