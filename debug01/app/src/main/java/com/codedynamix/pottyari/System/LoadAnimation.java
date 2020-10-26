package com.codedynamix.pottyari.System;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.R;

import javax.microedition.khronos.opengles.GL10;

public class LoadAnimation implements Runnable
{
    private Texture texture;

    private Vector2 pos;
    private Vector2 size;
    private Vector2 texRange;
    private Vector2 texSize;
    private Color color;

    private float texAnim;

    private int animCnt;

    public LoadAnimation()
    {
        texture = new Texture();
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.player_animation);

        texAnim = 0.0f;

        pos      = new Vector2();
        size     = new Vector2(200.0f,200.0f);
        texRange = new Vector2(texAnim,0.0f);
        texSize  = new Vector2(0.25f,1.0f);
        color = new Color();
    }

    @Override
    public void run()
    {
        NowLoading loading = new NowLoading();
        Thread loadThread = new Thread(loading);
        loadThread.start();

        while(loadThread.isAlive())
        {
            FPSManager.calcFPS();

            animCnt++;
            if(animCnt == 25)
                texAnim += 0.25f;
            if(texAnim >= 1.0f)
                texAnim = 0.0f;

            GameActivity.getGL().glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
            texture.draw(pos,size,0.0f,false,texRange,texSize,color);

            FPSManager.drowsy();
        }
    }
}
