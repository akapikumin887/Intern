package com.example.deb.Status;

import android.view.MotionEvent;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.BaseScene;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.Scene.StatusScene;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;

public class UIStatus extends Object
{
    private static Texture texStatus;
    private static Texture texBack;
    private static Texture texButton;

    private Vector2 levelPos;
    private Vector2 attackPos;
    private Vector2 hitpointPos;
    private Vector2 statusSize;


    public UIStatus()
    {
        super();
        setLayer(Layer.LAYER_BUTTON);

        {

        }
        statusSize = new Vector2(200.0f,50.0f);

        //基準 こいつからどれだけ離れてるかで座標を表す
        attackPos = new Vector2(GameActivity.getBaseWid() / 2 - statusSize.x,50.0f);

        levelPos = new Vector2(attackPos.x,attackPos.y + statusSize.y);
        hitpointPos = new Vector2(attackPos.x,attackPos.y - statusSize.y);
    }

    @Override
    public void draw()
    {
        //status組
        {
            texStatus.draw(levelPos,statusSize,rotate,reverse,new Vector2(),new Vector2(0.5f,0.3333f),color);   //レベル
            texStatus.draw(attackPos,statusSize,rotate,reverse,new Vector2(0.0f,0.3334f),new Vector2(0.5f,0.3333f),color);   //攻撃力
            texStatus.draw(hitpointPos,statusSize,rotate,reverse,new Vector2(0.0f,0.6667f),new Vector2(0.5f,0.3333f),color);   //体力
        }
    }

    @Override
    public void update()
    {

    }


    public static void loadTexture()
    {
        texStatus = new Texture();
        texStatus.loadTexture(GameActivity.getCntxt(), R.drawable.status_lv_po_hp);

        texBack = new Texture();
        texBack.loadTexture(GameActivity.getCntxt(), R.drawable.choices_back);

        texButton = new Texture();
        texButton.loadTexture(GameActivity.getCntxt(), R.drawable.status_button);
    }

    @Override
    public void touch(MotionEvent event)
    {
        //getXYと描画画面との誤差を修正
        Vector2 ratio = new Vector2(GameActivity.getBaseWid() / GameActivity.getSurfaceWid(),
                GameActivity.getBaseHei() / GameActivity.getSurfaceHei());

        Vector2 touchPos = new Vector2(event.getX() * ratio.x - GameActivity.getBaseWid() / 2,(-event.getY() * ratio.y + GameActivity.getBaseHei() / 2));

        if(event.getAction() == MotionEvent.ACTION_DOWN)    //trigger
        {
            //当たり判定取得(めんどい)
/*
            if(touchPos.x < startPos.x + startSize.x / 2 && touchPos.x > startPos.x - startSize.x / 2 &&
                    touchPos.y < startPos.y + startSize.y / 2 && touchPos.y > startPos.y - startSize.y / 2)
            {
                //シーン遷移
                BaseScene.setnextScene(new StatusScene());
            }
*/

        }
    }
}
