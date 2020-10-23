package com.example.deb.Battle;

import android.view.MotionEvent;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.Object.Figure;
import com.example.deb.Object.HeroStatus;
import com.example.deb.System.Vector2;
import com.example.deb.UI.Enemy;
import com.example.deb.UI.HpBar;
import com.example.deb.UI.Status;

public class UIBattle extends Object
{
    private Status lv;
    private Status hp;
    private Status at;

    private Figure stLv;
    private Figure stHp;
    private Figure stAt;

    private Enemy boss;
    private HpBar redBar;
    private HpBar greenBar;

    private float phoneLeftWidth;
    private float remnantsHp;      //緑のhpの長さ 名前変更予定

    public UIBattle()
    {
        super();
        setLayer(Layer.LAYER_BUTTON);
        phoneLeftWidth = -GameActivity.getBaseWid() / 2;

        lv = new Status(3);
        at = new Status(4);
        hp = new Status(5);

        stLv = new Figure(HeroStatus.getLv(),0);
        stLv.setSize(new Vector2(lv.getSize().y,lv.getSize().y));
        stLv.setPosition(new Vector2(-GameActivity.getBaseWid() / 4,lv.getPosition().y - lv.getSize().y));
        stHp = new Figure(HeroStatus.getHp(),0);
        stHp.setSize(new Vector2(hp.getSize().y,hp.getSize().y));
        stHp.setPosition(new Vector2(-GameActivity.getBaseWid() / 4,hp.getPosition().y - hp.getSize().y));
        stAt = new Figure(HeroStatus.getAttack(),0);
        stAt.setSize(new Vector2(at.getSize().y,at.getSize().y));
        stAt.setPosition(new Vector2(-GameActivity.getBaseWid() / 4,at.getPosition().y - at.getSize().y));

        remnantsHp = 1.0f;

        redBar = new HpBar(1);
        greenBar = new HpBar(0);

        boss = new Enemy(2);
    }

    @Override
    public void update()
    {
        boss.update();
        stLv.update();
        stHp.update();
        stAt.update();
    }

    @Override
    public void draw()
    {
        lv.draw();
        hp.draw();
        at.draw();

        stLv.draw();
        stHp.draw();
        stAt.draw();

        boss.draw();

        redBar.draw();
        greenBar.draw();
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

        }
    }
}
