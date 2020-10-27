package com.codedynamix.pottyari.UI;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.BaseClass.Object;
import com.codedynamix.pottyari.R;
import com.codedynamix.pottyari.System.Texture;
import com.codedynamix.pottyari.System.Vector2;

public class Waku extends Object
{
    private static Texture texture;

    private Vector2 texStartPoint;
    private Vector2 texSize;
    public Waku()
    {
        super();
        setLayer(Object.Layer.LAYER_BUTTON);

        setSize(new Vector2(  600.0f, 150.0f));
        setPosition(new Vector2(-GameActivity.getBaseWid() / 2 + size.x/1.9f,GameActivity.getBaseHei() / 2 - size.y/1.5f));
        texStartPoint = new Vector2();
        texSize = new Vector2(1.0f, 1.0f);
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
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.waku);
    }
}
