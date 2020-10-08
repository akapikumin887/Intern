package com.example.deb.Status;


import android.transition.Scene;
import android.view.MotionEvent;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.BaseScene;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.Scene.HomeScene;
import com.example.deb.Scene.StatusScene;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;

public class BGStatus extends Object
{
    private static Texture texture;

    public BGStatus()
    {
        super();
        layer = Layer.LAYER_BG;
        size = new Vector2(GameActivity.BASE_WID,GameActivity.BASE_HEI);
    }

    @Override
    public void draw()
    {
        texture.draw(pos,size,rotate,reverse, new Vector2(),new Vector2(1.0f,1.0f),color);
    }

    @Override
    public void update(float dt)
    {
        super.update(dt);

    }

    public static void loadTexture()
    {
        texture = new Texture();
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.status);
    }

    @Override
    public void touch(MotionEvent event)
    {
        BaseScene.setnextScene(new HomeScene());
    }
}
