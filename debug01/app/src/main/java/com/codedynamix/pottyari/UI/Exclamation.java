package com.codedynamix.pottyari.UI;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.BaseClass.Object;
import com.codedynamix.pottyari.R;
import com.codedynamix.pottyari.System.Texture;
import com.codedynamix.pottyari.System.Vector2;

public class Exclamation extends Object
{
    private static Texture texture;

    public Exclamation()
    {
        super();
        setLayer(Layer.LAYER_CHARE);
        setSize(new Vector2(100.0f,100.0f));
    }

    @Override
    public void draw()
    {
        if(texture == null) return;
        texture.draw(pos,size,rotate,reverse,new Vector2(),new Vector2(1.0f,1.0f),color);
    }


    public static void loadTexture()
    {
        texture = new Texture();
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.exclamation);
    }

}
