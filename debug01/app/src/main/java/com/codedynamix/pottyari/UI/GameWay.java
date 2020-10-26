package com.codedynamix.pottyari.UI;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.BaseClass.Object;
import com.codedynamix.pottyari.R;
import com.codedynamix.pottyari.Step.PlayerStep;
import com.codedynamix.pottyari.System.Texture;
import com.codedynamix.pottyari.System.Vector2;

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
                break;
            case 1:
                //player_icom (青)
                setSize(new Vector2(80.0f,80.0f));
                setPosition(new Vector2(-GameActivity.getBaseWid() / 2 + size.x / 2 + PlayerStep.getkyori() , GameActivity.getBaseHei() / 2 - size.y/0.28f));
                texStartPoint = new Vector2();
                texSize = new Vector2(0.25f,0.5f);
                break;
            case 2:
                //boss_icom(赤)
                setSize(new Vector2(80.0f,80.0f));
                setPosition(new Vector2(-GameActivity.getBaseWid() / 2 + size.x / 2, GameActivity.getBaseHei() / 2 - size.y/0.28f));
                texStartPoint = new Vector2(0.25f,0.0f);
                texSize = new Vector2(0.25f,0.5f);
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
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.game_pl_bo_way);
    }

}
