package com.example.deb.Scene;


import android.content.Context;

import com.example.deb.BaseClass.BaseScene;
import com.example.deb.R;
import com.example.deb.System.Texture;
import com.example.deb.System.TextureInfo;
import com.example.deb.Title.BGTitle;

import javax.microedition.khronos.opengles.GL10;

public class HomeScene extends BaseScene
{
    BGTitle bgTitle;

    public HomeScene(GL10 gl, Context context)
    {
        //スプライト初期化
        TextureInfo info;
        info = Texture.loadTexture(gl, context.getResources(), R.drawable.title);
        bgTitle = new BGTitle(info);
        gl10 = gl;
    }

    @Override
    public void update()
    {

    }

    @Override
    public void draw()
    {

    }
}
