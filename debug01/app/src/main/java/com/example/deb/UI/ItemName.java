package com.example.deb.UI;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;

public class ItemName extends Object
{
    private static Texture texture;

    private Vector2 texStartPoint;
    private Vector2 texSize;

    public ItemName(int num)
    {
       /* super();
        setLayer(Layer.LAYER_BUTTON);
        setSize(new Vector2(400.0f,200.0f));
        setPosition(new Vector2(0.0f,-GameActivity.getBaseHei() / 2 + size.y * 1.5f));*/
            super();
            setLayer(Layer.LAYER_BUTTON);
            switch (num) {
                case 0:
                    //野菜ジュース
                    setSize(new Vector2(800.0f, 120.0f));
                    setPosition(new Vector2(GameActivity.getBaseWid() / 2 - size.x/2.5f,-GameActivity.getBaseHei()/2 +size.y/0.3f));
                    texStartPoint = new Vector2();
                    texSize = new Vector2(1.0f, 0.3333f);
                    break;
                case 1:
                    //栄養ドリンク
                    setSize(new Vector2(800.0f, 120.0f));
                    setPosition(new Vector2(GameActivity.getBaseWid() / 2 - size.x/2.5f,-GameActivity.getBaseHei()/2 +size.y/0.3f));
                    texStartPoint = new Vector2(0.333f,0.333f);
                    texSize = new Vector2(1.0f, 0.3333f);
                    break;
                case 2:
                    //武器強化
                    setSize(new Vector2(500.0f, 50.0f));
                    setPosition(new Vector2(GameActivity.getBaseWid() / 2 - size.x, 50.0f + size.y));
                    texStartPoint = new Vector2(0.666f,0.666f);
                    texSize = new Vector2(1.0f, 0.3333f);
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
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.shoptext);
    }
}
