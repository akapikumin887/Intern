package com.example.deb.BG;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;

public class BGProgress extends Object
{
    private static Texture texture;

    public BGProgress()
    {
        super();
        setLayer(Layer.LAYER_BG);
        setSize(new Vector2(GameActivity.getBaseHei() / 3 * 40,GameActivity.getBaseHei()));
        setPosition(new Vector2(-GameActivity.getBaseHei() / 3 * 19,0.0f));
    }

    @Override
    public void draw()
    {
        texture.draw(pos,size,
                rotate,reverse,
                new Vector2(),
                new Vector2(20.0f,1.0f),
                color);
    }

    @Override
    public void update()
    {
        pos.x += 0.5f;
    }

    public static void loadTexture()
    {
        texture = new Texture();
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.game_background);
    }

}
