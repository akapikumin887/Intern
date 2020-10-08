package com.example.deb.Scene;

import android.view.MotionEvent;

import com.example.deb.BaseClass.BaseScene;
import com.example.deb.Status.BGStatus;
import com.example.deb.Title.BGTitle;

public class StatusScene extends BaseScene
{
    BGStatus[] bgStatus = new BGStatus[10];

    public StatusScene()
    {
        for(int i = 0; i < 10; i++)
        {
            bgStatus[i] = new BGStatus();
            list.add(bgStatus[i]);
        }

        //bgStatus = new BGStatus();
        //list.add(bgStatus);
    }

    @Override
    public void uninit()
    {
        list.remove(bgStatus);
    }

    @Override
    public  void touch(MotionEvent event)
    {
        super.touch(event);

    }

}
