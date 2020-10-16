package com.example.deb.BG;

import android.view.MotionEvent;

import com.example.deb.Activity.GameActivity;
import com.example.deb.Activity.MainActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;

public class BGTitle extends Object
{
    private static Texture texture;

    public BGTitle()
    {
        super();
        setLayer(Layer.LAYER_BG);
        setSize(new Vector2(GameActivity.getBaseWid(),GameActivity.getBaseHei()));
    }

    @Override
    public void draw()
    {
        //描画 ここ書けば描画できそう
        texture.draw(pos,size,rotate,reverse, new Vector2(),new Vector2(1.0f,1.0f),color);
    }

    public static void loadTexture()
    {
        texture = new Texture();
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.home_background);
    }
}
