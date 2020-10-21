package com.example.deb.UI;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;

public class HomeButton extends Object
{
    private static Texture texture;

    private Vector2 texStartPoint;
    private Vector2 texSize;

    public HomeButton(int num)
    {
        super();
        setLayer(Layer.LAYER_BUTTON);
        switch (num)
        {
            case 0:
                //？ボタン
                setSize(new Vector2(120.0f,120.0f));
                setPosition(new Vector2(GameActivity.getBaseWid() / 2 - size.x / 1.8f, GameActivity.getBaseHei() / 2 - size.y * 2.2f));
                texStartPoint = new Vector2();
                texSize = new Vector2(0.25f,1.0f);
                return;
            case 1:
                //設定ボタン
                setSize(new Vector2(120.0f,120.0f));
                setPosition(new Vector2(GameActivity.getBaseWid() / 2 - size.x / 1.8f, GameActivity.getBaseHei() / 2 - size.y));
                texStartPoint = new Vector2(0.25f,0.0f);
                texSize = new Vector2(0.25f,1.0f);
                return;
            case 2:
                //冒険ボタン
                setSize(new Vector2(550.0f,250.0f));
                setPosition(new Vector2(0.0f,-GameActivity.getBaseHei() / 2 + size.y / 2 + 100.0f));
                texStartPoint = new Vector2(0.5f,0.0f);
                texSize = new Vector2(0.5f,1.0f);
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
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.home_button);
    }

}
