package com.codedynamix.pottyari.UI;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.BaseClass.Object;
import com.codedynamix.pottyari.R;
import com.codedynamix.pottyari.System.Texture;
import com.codedynamix.pottyari.System.Vector2;

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
        switch (num)
        {
            case 0:
                //左矢印 ショップ想定
                setSize(new Vector2(200.0f,200.0f));
                setPosition(new Vector2(-GameActivity.getBaseWid() / 2 + size.x / 2,0.0f));
                texStartPoint = new Vector2();
                texSize = new Vector2(0.25f,0.3333f);
                break;
            case 1:
                //右矢印 ショップ想定
                setSize(new Vector2(200.0f,200.0f));
                setPosition(new Vector2(GameActivity.getBaseWid() / 2 - size.x / 2,0.0f));
                texStartPoint = new Vector2(0.25f,0.0f);
                texSize = new Vector2(0.25f,0.3333f);
                break;
            case 2:
                //戻るボタン 様々な場所で使うが場所は変わらないはずなのでそこを基準に
                setSize(new Vector2(120.0f,120.0f));
                setPosition(new Vector2(-GameActivity.getBaseWid() / 2 + size.x,GameActivity.getBaseHei() / 2 - size.y));
                texStartPoint = new Vector2(0.5f,0.0f);
                texSize = new Vector2(0.25f,0.3334f);
                break;
            case 3:
                //メッセージ選択矢印 どこだろう
                setSize(new Vector2(120.0f,120.0f));
                setPosition(new Vector2(-GameActivity.getBaseWid() / 2 + size.x,GameActivity.getBaseHei() / 2 - size.y));
                texStartPoint = new Vector2(0.5f,0.0f);
                texSize = new Vector2(0.25f,0.3334f);
                break;
            case 4:
                //はい ショップ想定
                setSize(new Vector2(GameActivity.getBaseWid() / 4,100.0f));
                setPosition(new Vector2());
                texStartPoint = new Vector2(0.0f,0.3334f);
                texSize = new Vector2(0.5f,0.3333f);
                break;
            case 5:
                //いいえ ショップ想定
                setSize(new Vector2(GameActivity.getBaseWid() / 4 / 2 * 3,100.0f));
                setPosition(new Vector2());
                texStartPoint = new Vector2(0.0f,0.6667f);
                texSize = new Vector2(0.75f,0.3333f);
                break;
        }

    }

    @Override
    public void draw()
    {
        if(texture == null) return;
        texture.draw(pos,size,rotate,reverse,texStartPoint,texSize,color);
    }

    public static void loadTexture()
    {
        texture = new Texture();
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.choices_back);
    }

}
