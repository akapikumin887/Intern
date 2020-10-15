package com.example.deb.Scene;

import android.view.MotionEvent;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.BaseScene;
import com.example.deb.Status.BGStatus;
import com.example.deb.Status.UIStatus;
import com.example.deb.System.Vector2;
import com.example.deb.Title.BGTitle;
import com.example.deb.Title.HeroTitle;

public class StatusScene extends BaseScene
{
    BGStatus bgStatus;
    UIStatus uiStatus;
    HeroTitle heroStatus;

    public StatusScene()
    {
        //背景
        bgStatus = new BGStatus();
        list.add(bgStatus);

        //UI
        uiStatus = new UIStatus();
        list.add(uiStatus);

        //勇者
        heroStatus = new HeroTitle();
        list.add(heroStatus);
        heroStatus.setPosition(new Vector2(-GameActivity.getBaseWid() / 4,0.0f));
        heroStatus.setSize(new Vector2(600.0f,600.0f));
    }

    @Override
    public void uninit()
    {
        super.uninit();
    }

    @Override
    public  void touch(MotionEvent event)
    {
        super.touch(event);

    }

}
