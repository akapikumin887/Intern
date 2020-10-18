package com.example.deb.Status;

import android.view.MotionEvent;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.BaseScene;
import com.example.deb.BaseClass.Object;
import com.example.deb.Scene.HomeScene;
import com.example.deb.Scene.ShopScene;
import com.example.deb.System.Vector2;
import com.example.deb.UI.ChoiseBack;
import com.example.deb.UI.Status;
import com.example.deb.UI.StatusButton;

public class UIStatus extends Object
{
    private Status level;
    private Status attack;
    private Status hitPoint;

    private ChoiseBack back;

    private StatusButton pt;
    private StatusButton lvUp;
    private StatusButton shop;

    public UIStatus()
    {
        super();
        setLayer(Layer.LAYER_BUTTON);

        //ステータス
        level = new Status(0);
        attack = new Status(1);
        hitPoint = new Status(2);

        //戻る
        back = new ChoiseBack(2);

        //ボタン
        pt = new StatusButton(0);
        lvUp = new StatusButton(1);
        shop = new StatusButton(2);
    }

    @Override
    public void draw()
    {
        //status組
        level.draw();
        attack.draw();
        hitPoint.draw();

        //戻るボタン
        back.draw();

        //ボタン
        pt.draw();
        lvUp.draw();
        shop.draw();
    }

    @Override
    public void update()
    {

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
            //Home遷移
            if(touchPos.x < back.getPosition().x + back.getSize().x / 2 && touchPos.x > back.getPosition().x - back.getSize().x / 2 &&
                    touchPos.y < back.getPosition().y + back.getSize().y / 2 && touchPos.y > back.getPosition().y - back.getSize().y / 2)
            {
                BaseScene.setnextScene(new HomeScene());
            }

            //ショップ遷移
            if(touchPos.x < shop.getPosition().x + shop.getSize().x / 2 && touchPos.x > shop.getPosition().x - shop.getSize().x / 2 &&
                    touchPos.y < shop.getPosition().y + shop.getSize().y / 2 && touchPos.y > shop.getPosition().y - shop.getSize().y / 2)
            {
                BaseScene.setnextScene(new ShopScene());
            }

        }
    }
}