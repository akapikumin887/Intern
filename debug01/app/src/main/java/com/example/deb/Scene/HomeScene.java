package com.example.deb.Scene;


import android.content.Context;

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

    public HomeScene(GL10 gl, Context context)
    {
        //インスタンス初期化
        bgTitle = new BGTitle(Texture.loadTexture(gl, context.getResources(), R.drawable.title));
        //TextureInfo info = Texture.loadTexture(gl, context.getResources(), R.drawable.title);
        list.add(bgTitle);
        gl10 = gl;
    }
}
