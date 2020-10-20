package com.example.deb.UI;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;

public class GameWay extends Object
{
    private static Texture texture;

    private Vector2 texStartPoint;
    private Vector2 texSize;

    public GameWay(int num)
    {
        super();
        setLayer(Layer.LAYER_BUTTON);

        switch (num)
        {
            case 0:
                //画面上の進行度を表す道
                setSize(new Vector2(GameActivity.getBaseWid()+70.0f,200.0f));
                setPosition(new Vector2(GameActivity.getBaseWid() / 2 -GameActivity.getBaseWid() / 2,GameActivity.getBaseHei() / 2 - size.y / 0.7f));
                texStartPoint = new Vector2(0.0f,0.5f);
                texSize = new Vector2(1.0f,0.5f);
                return;
            case 1:
                //player_icom (青)
                setSize(new Vector2(80.0f,80.0f));
                setPosition(new Vector2(GameActivity.getBaseWid() / 2 - size.x / 1.8f, GameActivity.getBaseHei() / 2 - size.y/0.28f));
                texStartPoint = new Vector2();
                texSize = new Vector2(0.25f,0.5f);
                return;
            case 2:
                //boss_icom(赤)
                setSize(new Vector2(80.0f,80.0f));
                setPosition(new Vector2(GameActivity.getBaseWid() / 2 - size.x / 0.11f, GameActivity.getBaseHei() / 2 - size.y/0.28f));
                texStartPoint = new Vector2(0.25f,0.0f);
                texSize = new Vector2(0.25f,0.5f);
                return;
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
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.game_pl_bo_way);
    }


}
