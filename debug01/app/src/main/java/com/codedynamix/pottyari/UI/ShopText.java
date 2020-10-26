package com.codedynamix.pottyari.UI;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.BaseClass.Object;
import com.codedynamix.pottyari.R;
import com.codedynamix.pottyari.System.Texture;
import com.codedynamix.pottyari.System.Vector2;

public class ShopText extends Object
{
    private static Texture texture;

    private int texNum;

    private Vector2 texStartPoint;
    private Vector2 texSize;

    public ShopText(int num)
    {
        super();
        setLayer(Object.Layer.LAYER_BUTTON);
        switch (num)
        {
            case 0:
                //pt
                setSize(new Vector2(200.0f,200.0f));
                setPosition(new Vector2(-GameActivity.getBaseWid() / 2 + size.x / 2,0.0f));
                texStartPoint = new Vector2();
                texSize = new Vector2(0.125f,0.25f);
                break;
            case 1:
                //ptが足りません
                setSize(new Vector2(200.0f,200.0f));
                setPosition(new Vector2(GameActivity.getBaseWid() / 2 - size.x / 2,0.0f));
                texStartPoint = new Vector2(0.0f,0.25f);
                texSize = new Vector2(1.0f,0.25f);
                break;
            case 2:
                //所持数
                setSize(new Vector2(120.0f,120.0f));
                setPosition(new Vector2(-GameActivity.getBaseWid() / 2 + size.x,GameActivity.getBaseHei() / 2 - size.y));
                texStartPoint = new Vector2(0.0f,0.75f);
                texSize = new Vector2(0.375f,0.25f);
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
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.shoptext2);
    }
}
