package com.example.deb.UI;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;

public class Status extends Object
{
    private static Texture texture;

    private Vector2 texStartPoint;
    private Vector2 texSize;

    public Status(int num)
    {
        super();
        setLayer(Layer.LAYER_BUTTON);
        switch (num)
        {
            case 0:
                //黒レベル ステータス画面想定
                setSize(new Vector2(200.0f,50.0f));
                setPosition(new Vector2(GameActivity.getBaseWid() / 2 - size.x,50.0f + size.y));
                texStartPoint = new Vector2();
                texSize = new Vector2(0.5f,0.3333f);
                return;
            case 1:
                //黒攻撃力 ステータス画面想定
                setSize(new Vector2(200.0f,50.0f));
                setPosition(new Vector2(GameActivity.getBaseWid() / 2 - size.x,50.0f - size.y));
                texStartPoint = new Vector2(0.0f,0.3334f);
                texSize = new Vector2(0.5f,0.3333f);
                return;
            case 2:
                //黒HP ステータス画面想定
                setSize(new Vector2(200.0f,50.0f));
                setPosition(new Vector2(GameActivity.getBaseWid() / 2 - size.x,50.0f));
                texStartPoint = new Vector2(0.0f,0.6667f);
                texSize = new Vector2(0.5f,0.3333f);
                return;
            case 3:
                //白レベル ゲーム画面想定
                setSize(new Vector2(160.0f,40.0f));
                setPosition(new Vector2(-GameActivity.getBaseWid() / 2 + size.x / 1.3f,-GameActivity.getBaseHei() / 2 + size.y * 9.0f));
                texStartPoint = new Vector2(0.5f,0.0f);
                texSize = new Vector2(0.5f,0.3333f);
                return;
            case 4:
                //白攻撃力 ゲーム画面想定
                setSize(new Vector2(160.0f,40.0f));
                setPosition(new Vector2(-GameActivity.getBaseWid() / 2 + size.x / 1.3f,-GameActivity.getBaseHei() / 2 + size.y * 3.5f));
                texStartPoint = new Vector2(0.5f,0.3334f);
                texSize = new Vector2(0.5f,0.3333f);
                return;
            case 5:
                //白HP ゲーム画面想定
                setSize(new Vector2(160.0f,40.0f));
                setPosition(new Vector2(-GameActivity.getBaseWid() / 2 + size.x / 1.3f,-GameActivity.getBaseHei() / 2 + size.y * 6.25f));
                texStartPoint = new Vector2(0.5f,0.6667f);
                texSize = new Vector2(0.5f,0.3333f);
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
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.status_lv_po_hp);
    }
}
