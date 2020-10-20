package com.example.deb.UI;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;

public class ChoiseBack extends Object
{
    private static Texture texture;

    private int texNum;

    private Vector2 texStartPoint;
    private Vector2 texSize;

    public ChoiseBack(int num)
    {
        super();
        setLayer(Layer.LAYER_BUTTON);
        texNum = num;
        switch (texNum)
        {
            case 0:
                //左矢印 ショップ想定
                setSize(new Vector2(200.0f,200.0f));
                setPosition(new Vector2(-GameActivity.getBaseWid() / 2 + size.x / 2,0.0f));
                texStartPoint = new Vector2();
                texSize = new Vector2(0.25f,0.3333f);
                return;
            case 1:
                //右矢印 ショップ想定
                setSize(new Vector2(200.0f,200.0f));
                setPosition(new Vector2(GameActivity.getBaseWid() / 2 - size.x / 2,0.0f));
                texStartPoint = new Vector2(0.25f,0.0f);
                texSize = new Vector2(0.25f,0.3333f);
                return;
            case 2:
                //戻るボタン 様々な場所で使うが場所は変わらないはずなのでそこを基準に
                setSize(new Vector2(120.0f,120.0f));
                setPosition(new Vector2(-GameActivity.getBaseWid() / 2 + size.x,GameActivity.getBaseHei() / 2 - size.y));
                texStartPoint = new Vector2(0.5f,0.0f);
                texSize = new Vector2(0.25f,0.3334f);
                return;
            case 3:
                //メッセージ選択矢印 どこだろう
                setSize(new Vector2(120.0f,120.0f));
                setPosition(new Vector2(-GameActivity.getBaseWid() / 2 + size.x,GameActivity.getBaseHei() / 2 - size.y));
                texStartPoint = new Vector2(0.5f,0.0f);
                texSize = new Vector2(0.25f,0.3334f);
                return;
            case 4:
                //はい ショップ想定
                setSize(new Vector2(GameActivity.getBaseWid() / 4,100.0f));
                setPosition(new Vector2());
                texStartPoint = new Vector2(0.0f,0.3334f);
                texSize = new Vector2(0.5f,0.3333f);
                return;
            case 5:
                //いいえ ショップ想定
                setSize(new Vector2(GameActivity.getBaseWid() / 4 / 2 * 3,100.0f));
                setPosition(new Vector2());
                texStartPoint = new Vector2(0.0f,0.6667f);
                texSize = new Vector2(0.75f,0.3333f);
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
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.choices_back);
    }

}
