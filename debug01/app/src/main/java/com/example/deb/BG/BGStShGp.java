package com.example.deb.BG;


import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;

//ステータス ショップ ゲーム進行画面の背景
public class BGStShGp extends Object
{
    private static Texture texture;

    public BGStShGp()
    {
        super();
        setLayer(Layer.LAYER_BG);
        setSize(new Vector2(GameActivity.getBaseWid(),GameActivity.getBaseHei()));
    }

    @Override
    public void draw()
    {
        texture.draw(pos,size,
                rotate,reverse,
                new Vector2(),
                new Vector2(1.0f,1.0f),
                color);
    }

    public static void loadTexture()
    {
        texture = new Texture();
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.s_s_g_background);
    }

}
