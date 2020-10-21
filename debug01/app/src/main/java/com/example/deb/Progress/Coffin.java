package com.example.deb.Progress;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;

public class Coffin extends Object
{
    private static Texture texture;

    public Coffin()
    {
        super();
        setLayer(Layer.LAYER_CHARE);
        setSize(new Vector2(200.0f,200.0f));
    }

    @Override
    public void draw()
    {
        texture.draw(pos,size,rotate,reverse, new Vector2(),new Vector2(0.5f,1.0f),color);
    }

    public static void loadTexture()
    {
        texture = new Texture();
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.coffin);
    }


}
