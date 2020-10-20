package com.example.deb.BG;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;

public class BGProgress extends Object
{
    private static Texture texture;

    private Vector2 loopPos;    //無限ループを行うための2爪の画像の描画位置

    public BGProgress()
    {
        super();
        setLayer(Layer.LAYER_BG);
        setSize(new Vector2(GameActivity.getBaseHei() / 3 * 2,GameActivity.getBaseHei()));
        setPosition(new Vector2(0.0f - (size.x / 2 - GameActivity.getBaseWid() / 2),0.0f));

        loopPos = new Vector2(pos.x - size.x,0.0f);
    }

    @Override
    public void draw()
    {
        texture.draw(pos,size,
                rotate,reverse,
                new Vector2(),
                new Vector2(1.0f,1.0f),
                color);

        texture.draw(loopPos,size,
                rotate,reverse,
                new Vector2(),
                new Vector2(1.0f,1.0f),
                color);
    }

    @Override
    public void update()
    {
        pos.x += 0.5f;
        loopPos.x += 0.5f;

        if(pos.x - size.x / 2 > GameActivity.getBaseWid() / 2)
            pos.x = loopPos.x - size.x;
        if(loopPos.x - size.x / 2 > GameActivity.getBaseWid() / 2)
            loopPos.x = pos.x - size.x;
    }

    public static void loadTexture()
    {
        texture = new Texture();
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.game_background);
    }

}
