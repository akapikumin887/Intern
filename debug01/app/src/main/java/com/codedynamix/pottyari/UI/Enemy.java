package com.codedynamix.pottyari.UI;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.BaseClass.Object;
import com.codedynamix.pottyari.R;
import com.codedynamix.pottyari.System.Texture;
import com.codedynamix.pottyari.System.Vector2;

public class Enemy extends Object
{
    private static Texture texNormal;
    private static Texture texChocolate;
    private static Texture texStrawberry;

    private Vector2 texStartPoint;
    private Vector2 texSize;

    private boolean isBoss;
    private int animCnt;

    private int enemyType;      //0は通常で1はチョコ

    //引数は敵の種類を
    public Enemy(int num)
    {
        super();
        setLayer(Layer.LAYER_BUTTON);
        enemyType = num;

        switch (enemyType % 3)
        {
            case 0:
                //敵01 一般的な奴
                setSize(new Vector2(GameActivity.getBaseWid() / 2,GameActivity.getBaseWid() / 2));
                setPosition(new Vector2(0.0f,GameActivity.getBaseHei() / 5));
                texStartPoint = new Vector2();
                texSize = new Vector2(0.25f,0.3333f);
                isBoss = false;
                break;
            case 1:
                //敵02 角が生えたやつ
                setSize(new Vector2(GameActivity.getBaseWid() / 2,GameActivity.getBaseWid() / 2));
                setPosition(new Vector2(0.0f,GameActivity.getBaseHei() / 5));
                texStartPoint = new Vector2(0.25f,0.0f);
                texSize = new Vector2(0.25f,0.3333f);
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
        switch(enemyType / 3)
        {
            case 0:
                if(texNormal == null) return;
                texNormal.draw(pos,size,rotate,reverse,texStartPoint,texSize,color);
                break;
            case 1:
                if(texChocolate == null) return;
                texChocolate.draw(pos,size,rotate,reverse,texStartPoint,texSize,color);
                break;
            case 2:
                if(texStrawberry == null) return;
                texChocolate.draw(pos,size,rotate,reverse,texStartPoint,texSize,color);
                break;
        }
    }

    public static void loadTexture()
    {
        texNormal = new Texture();
        texNormal.loadTexture(GameActivity.getCntxt(), R.drawable.enemy);

        texChocolate = new Texture();
        texChocolate.loadTexture(GameActivity.getCntxt(), R.drawable.enemy2);

        texStrawberry = new Texture();
        texStrawberry.loadTexture(GameActivity.getCntxt(), R.drawable.enemy3);
    }

}
