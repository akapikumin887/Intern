package com.codedynamix.pottyari.System;

import android.content.Context;
import android.content.SharedPreferences;

import com.codedynamix.pottyari.Activity.GameActivity;

//初めてのシーンに入った時に説明を映すが、その初めてを観察するclass
public class NewEnter
{
    private static SharedPreferences[] scenePrefs;
    private static boolean[] isInformed;

//マクロ定義
    private static final int INFORMATION_MAX = 4;

    public static void init()
    {
        scenePrefs = new SharedPreferences[INFORMATION_MAX];
        scenePrefs[0] = GameActivity.getActivity().getSharedPreferences("informTitle", Context.MODE_PRIVATE);
        scenePrefs[1] = GameActivity.getActivity().getSharedPreferences("informStatus", Context.MODE_PRIVATE);
        scenePrefs[2] = GameActivity.getActivity().getSharedPreferences("informShop", Context.MODE_PRIVATE);
        scenePrefs[3] = GameActivity.getActivity().getSharedPreferences("informProgress", Context.MODE_PRIVATE);

        isInformed = new boolean[INFORMATION_MAX];
        for(int i = 0; i < INFORMATION_MAX; i++)
            isInformed[i] = scenePrefs[i].getBoolean("boolean",true);
    }

    public static boolean getInformScene(int num)
    {
        if(num > INFORMATION_MAX) return false;

        for(int i = 0; i < INFORMATION_MAX; i++)
        {
            if(i == num)
                return isInformed[i];
        }
            return true;
    }

    public static void setInformScene(int num, boolean flag)
    {
        if(num > INFORMATION_MAX) return;

        for(int i = 0; i < INFORMATION_MAX; i++)
        {
            if(i == num)
            {
                isInformed[i] = flag;

                SharedPreferences.Editor editor = scenePrefs[i].edit();
                editor.putBoolean("boolean",flag);
                editor.apply();
                break;
            }
        }
    }
}
