package com.example.deb.Scene;


import android.content.Context;
import android.view.MotionEvent;

import androidx.appcompat.view.menu.MenuBuilder;

import com.example.deb.BaseClass.BaseScene;
import com.example.deb.R;
import com.example.deb.System.Texture;
import com.example.deb.System.TextureInfo;
import com.example.deb.Title.BGTitle;

import javax.microedition.khronos.opengles.GL10;

public class HomeScene extends BaseScene
{
    BGTitle bgTitle;

    public HomeScene()
    {
        //インスタンス初期化
        BGTitle.loadTexture();
        bgTitle = new BGTitle();
        list.add(bgTitle);
    }

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
