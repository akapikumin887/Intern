package com.example.deb.Object;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;

public class Item extends Object
{
    private static Texture texture;

    private float itemType;

//マクロ定義
    private static final int ITEM_MAX = 2;
    private final Vector2 TEX_SIZE = new Vector2(0.5f,0.25f);

    public Item(float type)
    {
        super();
        itemType = type;
        setLayer(Layer.LAYER_CHARE);
        setSize(new Vector2(300.0f,300.0f));
    }

    @Override
    public void draw()
    {
        //描画 ここ書けば描画できそう
        texture.draw(pos,size,rotate,reverse, new Vector2(0.5f,itemType),TEX_SIZE,color);
    }

    public static void loadTexture()
    {
        texture = new Texture();
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.item);
    }

    public static int getItemMax(){return ITEM_MAX;}
}
