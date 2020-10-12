package com.example.deb.Scene;


import android.view.MotionEvent;

import com.example.deb.BaseClass.BaseScene;
import com.example.deb.Object.Figure;
import com.example.deb.Status.BGStatus;
import com.example.deb.System.Vector2;
import com.example.deb.Title.BGTitle;

public class HomeScene extends BaseScene
{
    private BGTitle bgTitle;
    private Figure fighre;

    public HomeScene()
    {
        //インスタンス初期化
        bgTitle = new BGTitle();
        list.add(bgTitle);
        fighre = new Figure(0);
        list.add(fighre);
        fighre.setSize(new Vector2(300.0f,300.0f));
        fighre.setPosition(new Vector2());
    }

    @Override
    public void uninit()
    {
        list.remove(bgTitle);
        list.remove(fighre);
    }

    @Override
    public  void touch(MotionEvent event)
    {
        super.touch(event);
        fighre.setValue(fighre.getValue() + 1);
    }
}
