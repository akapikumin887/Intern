package com.example.deb.Scene;


import android.view.MotionEvent;

import com.example.deb.BaseClass.BaseScene;
import com.example.deb.Title.BGTitle;

public class HomeScene extends BaseScene
{
    BGTitle[] bgTitle = new BGTitle[10];

    public HomeScene()
    {
        //インスタンス初期化
        for(int i = 0; i < 10; i++)
        {
            bgTitle[i] = new BGTitle();
            list.add(bgTitle[i]);
        }
        //bgTitle = new BGTitle();
        //list.add(bgTitle);
    }

    @Override
    public void uninit()
    {
        list.remove(bgTitle);
    }

    @Override
    public  void touch(MotionEvent event)
    {
        super.touch(event);

    }
}
