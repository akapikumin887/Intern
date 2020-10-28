package com.codedynamix.pottyari.UI;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.BaseClass.Object;
import com.codedynamix.pottyari.R;
import com.codedynamix.pottyari.System.Texture;
import com.codedynamix.pottyari.System.Vector2;

public class BattleText extends Object
{
    private static Texture texture;

    private Vector2 texStartPoint;
    private Vector2 texSize;

    public BattleText(int num)
    {
        super();
        setLayer(Layer.LAYER_BUTTON);

        //positionについてはnewしてからsetしてもらう
        switch (num)
        {
            case 0:
                //敵が現れた
                setSize(new Vector2(GameActivity.getBaseWid() / 3 * 2,(GameActivity.getBaseWid() / 3 * 2) / 16));
                texStartPoint = new Vector2();
                break;
            case 1:
                //どうする？
                setSize(new Vector2(GameActivity.getBaseWid() / 3 * 2,(GameActivity.getBaseWid() / 3 * 2) / 16));
                texStartPoint = new Vector2(0.0f,0.083f);
                break;
            case 2:
                //勇者の攻撃
                setSize(new Vector2(GameActivity.getBaseWid() / 3 * 2,(GameActivity.getBaseWid() / 3 * 2) / 16));
                texStartPoint = new Vector2(0.0f,0.166f);
                break;
            case 3:
                //ダメージ与えた
                setSize(new Vector2(GameActivity.getBaseWid() / 3 * 2,(GameActivity.getBaseWid() / 3 * 2) / 16));
                texStartPoint = new Vector2(0.0f,0.249f);
                break;
            case 4:
                //相手の攻撃
                setSize(new Vector2(GameActivity.getBaseWid() / 3 * 2,(GameActivity.getBaseWid() / 3 * 2) / 16));
                texStartPoint = new Vector2(0.0f,0.332f);
                break;
            case 5:
                //ダメージを受けた
                setSize(new Vector2(GameActivity.getBaseWid() / 3 * 2,(GameActivity.getBaseWid() / 3 * 2) / 16));
                texStartPoint = new Vector2(0.0f,0.415f);
                break;
            case 6:
                //野菜ジュースを飲んだ
                setSize(new Vector2(GameActivity.getBaseWid() / 3 * 2,(GameActivity.getBaseWid() / 3 * 2) / 16));
                texStartPoint = new Vector2(0.0f,0.498f);
                break;
            case 7:
                //回復した
                setSize(new Vector2(GameActivity.getBaseWid() / 3 * 2,(GameActivity.getBaseWid() / 3 * 2) / 16));
                texStartPoint = new Vector2(0.0f,0.581f);
                break;
            case 8:
                //生き返った
                setSize(new Vector2(GameActivity.getBaseWid() / 3 * 2,(GameActivity.getBaseWid() / 3 * 2) / 16));
                texStartPoint = new Vector2(0.0f,0.664f);
                break;
            case 9:
                //死んでしまった
                setSize(new Vector2(GameActivity.getBaseWid() / 3 * 2,(GameActivity.getBaseWid() / 3 * 2) / 16));
                texStartPoint = new Vector2(0.0f,0.747f);
                break;
            case 10:
                //敵を倒した
                setSize(new Vector2(GameActivity.getBaseWid() / 3 * 2,(GameActivity.getBaseWid() / 3 * 2) / 16));
                texStartPoint = new Vector2(0.0f,0.830f);
                break;
            case 11:
                //野菜ジュースが足りない
                setSize(new Vector2(GameActivity.getBaseWid() / 3 * 2,(GameActivity.getBaseWid() / 3 * 2) / 16));
                texStartPoint = new Vector2(0.0f,0.913f);
                break;
        }
        texSize = new Vector2(1.0f,0.09f);

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
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.text);
    }
}
