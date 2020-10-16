package com.example.deb.UI;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;

public class StatusButton extends Object
{
    private static Texture texture;

    private Vector2 texStartPoint;
    private Vector2 texSize;

    public StatusButton(int num)
    {
        super();
        setLayer(Layer.LAYER_BUTTON);
        switch (num)
        {
            case 0:
                //pt ステータス画面想定
                setSize(new Vector2(500.0f,200.0f));
                setPosition(new Vector2(GameActivity.getBaseWid() / 2 - size.x / 1.8f,GameActivity.getBaseHei() / 2 - size.y / 1.3f));
                texStartPoint = new Vector2();
                texSize = new Vector2(1.0f,0.3333f);
                return;
            case 1:
                //lvUp ステータス画面想定
                setSize(new Vector2(400.0f,150.0f));
                setPosition(new Vector2(GameActivity.getBaseWid() / 2 - size.x / 2,50.0f- size.y * 2.0f));
                texStartPoint = new Vector2(0.0f,0.3334f);
                texSize = new Vector2(1.0f,0.3333f);
                return;
            case 2:
                //ショップ ステータス画面想定
                setSize(new Vector2(800.0f,220.0f));
                setPosition(new Vector2(0.0f,-GameActivity.getBaseHei() / 2 + size.y / 2 + 100.0f));
                texStartPoint = new Vector2(0.0f,0.6667f);
                texSize = new Vector2(1.0f,0.3333f);
                return;
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
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.status_button);
    }
}
