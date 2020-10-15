package com.example.deb.Title;

import android.view.MotionEvent;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;

public class HeroTitle extends Object
{
    private static Texture texture;

    public HeroTitle()
    {
        super();
        setLayer(Layer.LAYER_CHARE);
        setSize(new Vector2(800.0f,800.0f));
        setPosition(new Vector2(0.0f,100.0f));
    }

    @Override
    public void draw()
    {
        //描画 ここ書けば描画できそう
        texture.draw(pos,size,rotate,reverse, new Vector2(0.6666f,0.0f),new Vector2(0.3334f,1.0f),color);
    }

    @Override
    public void update()
    {

    }

    public static void loadTexture()
    {
        texture = new Texture();
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.player);
    }

    @Override
    public void touch(MotionEvent event)
    {
        //BaseScene.setnextScene(new StatusScene());
    }

}
