package com.codedynamix.pottyari.UI;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.BaseClass.Object;
import com.codedynamix.pottyari.Object.HeroStatus;
import com.codedynamix.pottyari.R;
import com.codedynamix.pottyari.System.Texture;
import com.codedynamix.pottyari.System.Vector2;

public class HeroUI extends Object
{
    private static Texture texture;

    private Vector2 texStartPoint;
    private Vector2 texSize;

    public HeroUI()
    {
        super();
        setLayer(Layer.LAYER_CHARE);
        texSize = new Vector2(0.3333f,1.0f);

        //レベルによって主人公の体型が変わる
        //20レベ以上で少し瘦せ、50レベ以上でスリムボディ
        if(HeroStatus.getLv() >= 50)
            texStartPoint = new Vector2();
        else if(HeroStatus.getLv() >= 20)
            texStartPoint = new Vector2(0.3334f,0.0f);
        else
            texStartPoint = new Vector2(0.6667f,0.0f);

    }

    @Override
    public void draw()
    {
        if(texture == null) return;
        texture.draw(pos,size,rotate,reverse,new Vector2(0.6667f,0.0f),new Vector2(0.3333f,1.0f),color);
    }


    public static void loadTexture()
    {
        texture = new Texture();
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.player);
    }

}
