package com.codedynamix.pottyari.UI;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.BaseClass.Object;
import com.codedynamix.pottyari.R;
import com.codedynamix.pottyari.System.Texture;
import com.codedynamix.pottyari.System.Vector2;

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
