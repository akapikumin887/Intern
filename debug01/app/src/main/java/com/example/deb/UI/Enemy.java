package com.example.deb.UI;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;

public class Enemy extends Object
{
    private static Texture texture;

    private Vector2 texStartPoint;
    private Vector2 texSize;

    private boolean isBoss;
    private int animCnt;

    public Enemy(int num)
    {
        super();
        setLayer(Layer.LAYER_BUTTON);
        switch (num)
        {
            case 0:
                //敵01 一般的な奴
                setSize(new Vector2(GameActivity.getBaseWid() / 2,GameActivity.getBaseWid() / 2));
                setPosition(new Vector2(0.0f,GameActivity.getBaseHei() / 4));
                texStartPoint = new Vector2();
                texSize = new Vector2(0.2f,0.5f);
                isBoss = false;
                return;
            case 1:
                //敵02 角が生えたやつ
                setSize(new Vector2(GameActivity.getBaseWid() / 2,GameActivity.getBaseWid() / 2));
                setPosition(new Vector2(0.0f,GameActivity.getBaseHei() / 4));
                texStartPoint = new Vector2(0.2f,0.0f);
                texSize = new Vector2(0.2f,0.5f);
                isBoss = false;
                return;
            case 2:
                //ボス01 動く
                setSize(new Vector2(GameActivity.getBaseWid() / 3 * 2,GameActivity.getBaseWid() / 3 * 2));
                setPosition(new Vector2(0.0f,GameActivity.getBaseHei() / 4));
                texStartPoint = new Vector2(0.0f,0.5f);
                texSize = new Vector2(0.1667f,0.5f);
                isBoss = true;
                return;
        }
        animCnt = 0;
    }

    @Override
    public void update()
    {
        //ボスだったらカウントを進めて、20f経過したらアニメーションさせる
        if(isBoss)
        {
            animCnt++;
            if(animCnt >= 10)
            {
                texStartPoint.x += 0.1667f;
                if(texStartPoint.x >= 1.0f)
                    texStartPoint.x = 0.0f;
                animCnt = 0;
            }
        }
    }

    @Override
    public void draw()
    {
        texture.draw(pos,size,rotate,reverse,texStartPoint,texSize,color);
    }

    public static void loadTexture()
    {
        texture = new Texture();
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.enemy);
    }

}
