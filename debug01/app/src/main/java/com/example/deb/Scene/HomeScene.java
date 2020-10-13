package com.example.deb.Scene;


import android.content.Context;
import android.content.SharedPreferences;
import android.view.MotionEvent;

import com.example.deb.Activity.GameActivity;
import com.example.deb.Activity.MainActivity;
import com.example.deb.BaseClass.BaseScene;
import com.example.deb.Object.Figure;
import com.example.deb.Status.BGStatus;
import com.example.deb.System.FPSManager;
import com.example.deb.System.Vector2;
import com.example.deb.Title.BGTitle;

public class HomeScene extends BaseScene
{
    private BGTitle bgTitle;
    private Figure fps;
    private Figure touchCount;

    SharedPreferences prefs;
    public HomeScene()
    {
//インスタンス初期化
        //背景
        bgTitle = new BGTitle();
        list.add(bgTitle);

        //fps
        fps = new Figure(0);
        list.add(fps);
        fps.setSize(new Vector2(100.0f,100.0f));
        fps.setPosition(new Vector2(GameActivity.getBaseWid() / 2 - 50.0f,GameActivity.getBaseHei() / 2 - 50.0f));

        //保存データから値を読み込む
        prefs = GameActivity.getActivity().getSharedPreferences("touchCount", Context.MODE_PRIVATE);

        //タッチカウント
        touchCount = new Figure(prefs.getInt("int",0));
        list.add(touchCount);
        touchCount.setSize(new Vector2(75.0f,75.0f));
        touchCount.setPosition(new Vector2(GameActivity.getBaseWid() / 2 - 50.0f,0.0f));
    }

    @Override
    public void uninit()
    {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("int",touchCount.getValue());
        editor.apply();

        super.uninit();
    }

    @Override
    public void update()
    {
        super.update();

        fps.setValue((int)FPSManager.getFPS());
    }

    @Override
    public  void touch(MotionEvent event)
    {
        //getX(),getYは左上が0。右下になるにつれて値が大きくなる。
        super.touch(event);
        if(event.getAction() == MotionEvent.ACTION_DOWN)    //trigger
        {
            touchCount.setValue(touchCount.getValue() + 1);
            if(event.getY() < GameActivity.getBaseHei() / 2)
                touchCount.setValue(0);
        }
    }
}
