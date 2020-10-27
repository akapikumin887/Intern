package com.codedynamix.pottyari.UI;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.BaseClass.Object;
import com.codedynamix.pottyari.R;
import com.codedynamix.pottyari.System.Vector2;
import com.codedynamix.pottyari.System.Texture;

public class Game_stepword extends Object {
    private static Texture texture;

    private Vector2 texStartPoint;
    private Vector2 texSize;

    public Game_stepword(int num) {
        super();
        setLayer(Layer.LAYER_BUTTON);

        switch (num) {
            case 0:
                //総歩数
                setSize(new Vector2(450.0f, 150.0f));
                setPosition(new Vector2(-GameActivity.getBaseWid() / 2 + size.x/2, GameActivity.getBaseHei() / 2 - size.y / 0.35f));
                texStartPoint = new Vector2();
                texSize = new Vector2(0.75f, 0.3333f);
                break;
            case 1:
                //ボスまで
                setSize(new Vector2(600.0f, 150.0f));
                setPosition(new Vector2(-GameActivity.getBaseWid() / 2 + size.x/2, GameActivity.getBaseHei() / 2 - size.y / 0.15f));
                texStartPoint = new Vector2(0.0f, 0.3333f);
                texSize = new Vector2(1.0f, 0.3333f);
                break;
            case 2:
                //あと
                setSize(new Vector2(80.0f, 80.0f));
                setPosition(new Vector2(GameActivity.getBaseWid() / 2 - size.x , GameActivity.getBaseHei() / 2 - size.y / 0.2f));
                texStartPoint = new Vector2(0.0f, 0.6666f);
                texSize = new Vector2(0.5f, 0.3333f);
                break;
            case 3:
                //歩1
                setSize(new Vector2(150.0f, 150.0f));
                setPosition(new Vector2(-GameActivity.getBaseWid() / 2 + size.x/0.23f, GameActivity.getBaseHei() / 2 - size.y / 0.25f));
                texStartPoint = new Vector2(0.5f, 0.6666f);
                texSize = new Vector2(0.25f, 0.3333f);
                break;
            case 4:
                //歩2
                setSize(new Vector2(150.0f, 150.0f));
                setPosition(new Vector2(-GameActivity.getBaseWid() / 2 + size.x/0.23f, GameActivity.getBaseHei() / 2 - size.y / 0.13f));
                texStartPoint = new Vector2(0.5f, 0.6666f);
                texSize = new Vector2(0.25f, 0.3333f);
                break;
        }
    }

    @Override
    public void draw()
    {
        if(texture == null) return;
        texture.draw(pos, size, rotate, reverse, texStartPoint, texSize, color);
    }

    public static void loadTexture()
    {
        texture = new Texture();
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.game_stepsword);
    }
}