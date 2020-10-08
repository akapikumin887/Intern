package com.example.deb.Title;

import android.view.MotionEvent;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;

public class BGTitle extends Object
{
    private static Texture texture;

    public BGTitle()
    {
        //マルチスレッドでぶん投げ隊
        super();
        layer = Layer.LAYER_BG;
        size = new Vector2(1000.0f,1000.0f);
    }

    @Override
    public void draw()
    {
        //描画 ここ書けば描画できそう
        texture.draw(pos,size,rotate,reverse, new Vector2(),new Vector2(1.0f,1.0f),color);
        /*
        * イメージ
        * 例外処理
        * texture.draw();
        * */
    }

    @Override
    public void update(float dt)
    {
        super.update(dt);

    }

    public static void loadTexture()
    {
        texture = new Texture();
        texture.loadTexture(GameActivity.context, R.drawable.title);
    }

    @Override
    public void touch(MotionEvent event)
    {

    }
}
