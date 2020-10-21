package com.example.deb.UI;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;

public class HpBar extends Object
{
    private static Texture texture;

    private Vector2 texStartPoint;
    private Vector2 texSize;

    public HpBar(int num)
    {
        super();
        setLayer(Layer.LAYER_BUTTON);
        switch(num)
        {
            case 0:
                //緑色のHP
                setSize(new Vector2(GameActivity.getBaseWid(),200.0f));
                setPosition(new Vector2(0.0f,GameActivity.getBaseHei() / 2 - size.y / 3));
                texStartPoint = new Vector2();
                texSize = new Vector2(1.0f,0.3333f);
                break;
            case 1:
                //赤いHP
                setSize(new Vector2(GameActivity.getBaseWid(),200.0f));
                setPosition(new Vector2(0.0f,GameActivity.getBaseHei() / 2 - size.y / 3));
                texStartPoint = new Vector2(0.0f,0.3334f);
                texSize = new Vector2(1.0f,0.3333f);
                break;
            case 2:
                //HPの枠 使う予定なし
                setSize(new Vector2(GameActivity.getBaseWid(),100.0f));
                setPosition(new Vector2(0.0f,GameActivity.getBaseHei() / 2 - size.y));
                texStartPoint = new Vector2(0.0f,0.6667f);
                texSize = new Vector2(1.0f,0.3333f);
                break;
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
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.enemy_hp);
    }

    public void setTexSize(Vector2 size){texSize = size;}
}
