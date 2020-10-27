package com.codedynamix.pottyari.UI;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.BaseClass.Object;
import com.codedynamix.pottyari.R;
import com.codedynamix.pottyari.System.Texture;
import com.codedynamix.pottyari.System.Vector2;

public class ItemName extends Object
{
    private static Texture texture;

    private Vector2 texStartPoint;
    private Vector2 texSize;

    public ItemName(int num)
    {
            super();
            setLayer(Layer.LAYER_BUTTON);
            switch (num) {
                case 0:
                    //野菜ジュース
                    setSize(new Vector2(750.0f, 150.0f));
                    setPosition(new Vector2(GameActivity.getBaseWid() / 2 - size.x/2.1f,-GameActivity.getBaseHei()/2 +size.y/0.3f));
                    texStartPoint = new Vector2();
                    texSize = new Vector2(0.92f, 0.3333f);
                    break;
                case 1:
                    //栄養ドリンク
                    setSize(new Vector2(750.0f, 150.0f));
                    setPosition(new Vector2(GameActivity.getBaseWid() / 2 - size.x/2.3f,-GameActivity.getBaseHei()/2 +size.y/0.3f));
                    texStartPoint = new Vector2(0.0f,0.333f);
                    texSize = new Vector2(1.0f, 0.3333f);
                    break;
                case 2:
                    //武器強化
                    setSize(new Vector2(750.0f, 150.0f));
                    setPosition(new Vector2(GameActivity.getBaseWid() / 2 - size.x/2.5f,-GameActivity.getBaseHei()/2 +size.y/0.3f));
                    texStartPoint = new Vector2(0.0f,0.666f);
                    texSize = new Vector2(1.0f, 0.3333f);
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
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.shoptext);
    }

}
