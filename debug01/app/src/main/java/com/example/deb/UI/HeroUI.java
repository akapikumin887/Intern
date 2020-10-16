package com.example.deb.UI;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;

public class HeroUI extends Object
{
    private static Texture texture;

    //重量でテクスチャを変える、レベルが上がると痩せる
    //private static int HeroWeight;

    public HeroUI()
    {
        super();
        setLayer(Layer.LAYER_CHARE);
        //HeroWeight = weight;
    }

    @Override
    public void draw()
    {
        texture.draw(pos,size,rotate,reverse,new Vector2(0.6667f,0.0f),new Vector2(0.3333f,1.0f),color);
    }


    public static void loadTexture()
    {
        texture = new Texture();
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.player);
    }

}
