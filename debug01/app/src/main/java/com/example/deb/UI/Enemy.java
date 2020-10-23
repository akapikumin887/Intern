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
                setPosition(new Vector2(0.0f,GameActivity.getBaseHei() / 5));
                texStartPoint = new Vector2();
                texSize = new Vector2(0.2f,0.3333f);
                isBoss = false;
                break;
            case 1:
                //敵02 角が生えたやつ
                setSize(new Vector2(GameActivity.getBaseWid() / 2,GameActivity.getBaseWid() / 2));
                setPosition(new Vector2(0.0f,GameActivity.getBaseHei() / 5));
                texStartPoint = new Vector2(0.2f,0.0f);
                texSize = new Vector2(0.2f,0.3333f);
                isBoss = false;
                break;
            case 2:
                //ボス01 動く
                setSize(new Vector2(GameActivity.getBaseWid() / 3 * 2,GameActivity.getBaseWid() / 3 * 2));
                setPosition(new Vector2(0.0f,GameActivity.getBaseHei() / 5));
                texStartPoint = new Vector2(0.0f,0.3334f);
                texSize = new Vector2(0.25f,0.3333f);
                isBoss = true;
                break;
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
            if(animCnt >= 15)
            {
                texStartPoint.x += 0.25f;
                if(texStartPoint.x >= 0.75f)
                {
                    if(texStartPoint.y >= 0.6667f)
                        texStartPoint.y = 0.3334f;
                    else
                    {
                        texStartPoint.y = 0.6667f;
                    }
                    texStartPoint.x = 0.0f;
                }
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
