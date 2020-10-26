package com.codedynamix.pottyari.UI;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.BaseClass.Object;
import com.codedynamix.pottyari.R;
import com.codedynamix.pottyari.System.Texture;
import com.codedynamix.pottyari.System.Vector2;

public class MessageWindow extends Object
{
    private static Texture texture;

    private int texNum;

    private Vector2 texStartPoint;
    private Vector2 texSize;

    public MessageWindow(int num)
    {
        super();
        setLayer(Layer.LAYER_BUTTON);
        texNum = num;
        switch (texNum)
        {
            case 0:
                //ptを消費して武器の強化を行いますか？ ショップ想定
                setSize(new Vector2(GameActivity.getBaseWid() - 100.0f,400.0f));
                setPosition(new Vector2());
                texStartPoint = new Vector2();
                texSize = new Vector2(0.5f,0.3333f);
                break;
            case 1:
                //ptを消費してlevelをあげますか？ ステータス画面想定
                setSize(new Vector2(GameActivity.getBaseWid() - 100.0f,400.0f));
                setPosition(new Vector2());
                texStartPoint = new Vector2(0.0f,0.3334f);
                texSize = new Vector2(0.5f,0.3333f);
                break;
            case 2:
                //ptを消費してアイテムを購入しますか？ ショップ想定
                setSize(new Vector2(GameActivity.getBaseWid() - 100.0f,400.0f));
                setPosition(new Vector2());
                texStartPoint = new Vector2(0.0f,0.6667f);
                texSize = new Vector2(0.5f,0.3333f);
                break;
            case 3:
                //横長無地 ショップ想定
                setSize(new Vector2(GameActivity.getBaseWid() / 5 * 2,200.0f));
                setPosition(new Vector2(0.0f,-400.0f));
                texStartPoint = new Vector2(0.5f,0.3334f);
                texSize = new Vector2(0.5f,0.3333f);
                break;
            case 4:
                //横長 進行画面想定
                setSize(new Vector2(GameActivity.getBaseWid() - 10.0f,(GameActivity.getBaseWid() - 10.0f) / 2.0f));
                setPosition(new Vector2(0.0f, -GameActivity.getBaseHei() / 2 + size.y / 1.8f));
                texStartPoint = new Vector2(0.5f,0.6667f);
                texSize = new Vector2(0.5f,0.3333f);
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
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.window);
    }

}
