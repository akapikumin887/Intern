package com.example.deb.Scene;

import com.example.deb.BaseClass.BaseScene;
import com.example.deb.Status.BGStatus;

public class StatusScene extends BaseScene
{
    BGStatus bgStatus;

    public StatusScene()
    {
        BGStatus.loadTexture();
        bgStatus = new BGStatus();
        //list.add(bgStatus);
    }
}
