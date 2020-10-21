package com.example.deb.Battle;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;

public class BattleCommand extends Object
{
    private static Texture texture;

    private Vector2 texStartPoint;
    private Vector2 texSize;

    public BattleCommand(int num)
    {
        super();
        setLayer(Layer.LAYER_BUTTON);
        switch(num)
        {
            case 0:
                //攻撃コマンド
                setSize(new Vector2(GameActivity.getBaseWid() / 3,150.0f));
                setPosition(new Vector2(0.0f,GameActivity.getBaseHei() / 2 - size.y / 3));
                texStartPoint = new Vector2();
                texSize = new Vector2(1.0f,0.5f);
                break;
            case 1:
                //回復コマンド
                setSize(new Vector2(GameActivity.getBaseWid() / 3,150.0f));
                setPosition(new Vector2(0.0f,GameActivity.getBaseHei() / 2 - size.y / 3));
                texStartPoint = new Vector2(0.0f,0.5f);
                texSize = new Vector2(1.0f,0.5f);
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
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.command);
    }

}
