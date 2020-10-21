package com.example.deb.UI;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;

public class ItemName extends Object
{
    private static Texture texture;

    public ItemName()
    {
        super();
        setLayer(Layer.LAYER_BUTTON);
        setSize(new Vector2(400.0f,200.0f));
        setPosition(new Vector2(0.0f,-GameActivity.getBaseHei() / 2 + size.y * 1.5f));
    }

    @Override
    public void draw()
    {
        texture.draw(pos,size,rotate,reverse,new Vector2(),new Vector2(1.0f,1.0f),color);
    }

    public static void loadTexture()
    {
        texture = new Texture();
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.item_name);
    }
}
