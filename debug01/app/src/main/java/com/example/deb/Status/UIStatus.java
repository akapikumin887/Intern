package com.example.deb.Status;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.MotionEvent;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.BaseScene;
import com.example.deb.BaseClass.Object;
import com.example.deb.Battle.BattleSystem;
import com.example.deb.Object.Figure;
import com.example.deb.Object.HeroStatus;
import com.example.deb.Scene.HomeScene;
import com.example.deb.Scene.ShopScene;
import com.example.deb.System.StepCount;
import com.example.deb.System.Vector2;
import com.example.deb.UI.ChoiseBack;
import com.example.deb.UI.MessageWindow;
import com.example.deb.UI.Status;
import com.example.deb.UI.StatusButton;

public class UIStatus extends Object
{
    private Status level;
    private Status attack;
    private Status hitPoint;

    private ChoiseBack back;
    private ChoiseBack yes;
    private ChoiseBack no;

    private MessageWindow window;
    private MessageWindow yeswnd;
    private MessageWindow nownd;
    private boolean isWindow;

    private StatusButton pt;
    private StatusButton lvUp;
    private StatusButton shop;

    private Figure point;
    private Figure stLv;
    private Figure stHp;
    private Figure stAt;


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

        //ウィンドウと選択肢
        window = new MessageWindow(1);

        yeswnd = new MessageWindow(3);
        yeswnd.setPosition(new Vector2(-yeswnd.getSize().x / 2,yeswnd.getPosition().y));
        nownd = new MessageWindow(3);
        nownd.setPosition(new Vector2(nownd.getSize().x / 2,nownd.getPosition().y));

        yes = new ChoiseBack(4);
        yes.setPosition(yeswnd.getPosition());
        no = new ChoiseBack(5);
        no.setPosition(nownd.getPosition());


        //ボタン
        pt = new StatusButton(0);
        lvUp = new StatusButton(1);
        shop = new StatusButton(2);

        //ポイント(数値)
        SharedPreferences pointPrefs= GameActivity.getActivity().getSharedPreferences("point", Context.MODE_PRIVATE);
        int p = pointPrefs.getInt("int",0) + StepCount.getTtPoint() / 10;  //今回歩いた歩数から今回得られるポイントを取得
        if(StepCount.getTtPoint() % 10 > 4)    //四捨五入
            p++;
        StepCount.resetTtPoint();   //ちゃんとポイントをゲットしたので値を初期化しておく

        //ポイントの変更をすぐ保存しておく
        SharedPreferences.Editor editor = pointPrefs.edit();
        editor.putInt("int",p);
        editor.apply();

        point = new Figure(p,1);
        point.setSize(new Vector2(100.0f,100.0f));
        point.setPosition(new Vector2(GameActivity.getBaseWid() / 2 - 100.0f,GameActivity.getBaseHei() / 2 - point.getSize().y * 1.5f));

        //ステータス(数値)
        stLv = new Figure(HeroStatus.getLv(),1);
        stLv.setSize(new Vector2(level.getSize().y,level.getSize().y));
        stLv.setPosition(new Vector2(GameActivity.getBaseWid() / 2 - stLv.getSize().x / 2,level.getPosition().y));

        stHp = new Figure(HeroStatus.getHp(),1);
        stHp.setSize(new Vector2(hitPoint.getSize().y,hitPoint.getSize().y));
        stHp.setPosition(new Vector2(GameActivity.getBaseWid() / 2 - stHp.getSize().x / 2,hitPoint.getPosition().y));

        stAt = new Figure(HeroStatus.getAttack(),1);
        stAt.setSize(new Vector2(attack.getSize().y,attack.getSize().y));
        stAt.setPosition(new Vector2(GameActivity.getBaseWid() / 2 - stAt.getSize().x / 2,attack.getPosition().y));

        isWindow = false;
    }

    @Override
    public void update()
    {
        point.update();
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

        //ポイント
        point.draw();

        //status数値
        stLv.draw();
        stHp.draw();
        stAt.draw();

        if(isWindow)
        {
            window.draw();
            yeswnd.draw();
            nownd.draw();
            yes.draw();
            no.draw();
        }
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
            if(isWindow)
            {
                //はい
                if(touchPos.x < yeswnd.getPosition().x + yeswnd.getSize().x / 2 && touchPos.x > yeswnd.getPosition().x - yeswnd.getSize().x / 2 &&
                        touchPos.y < yeswnd.getPosition().y + yeswnd.getSize().y / 2 && touchPos.y > yeswnd.getPosition().y - yeswnd.getSize().y / 2)
                {
                    BattleSystem.playerGrow();
                    stLv.setValue(HeroStatus.getLv());
                    stHp.setValue(HeroStatus.getHp());
                    stAt.setValue(HeroStatus.getAt());

                    isWindow = false;
                }
                //いいえ
                if(touchPos.x < nownd.getPosition().x + nownd.getSize().x / 2 && touchPos.x > nownd.getPosition().x - nownd.getSize().x / 2 &&
                        touchPos.y < nownd.getPosition().y + nownd.getSize().y / 2 && touchPos.y > nownd.getPosition().y - nownd.getSize().y / 2)
                {
                    isWindow = false;
                }
            }
            else
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

                //レベルアップ
                if(touchPos.x < lvUp.getPosition().x + lvUp.getSize().x / 2 && touchPos.x > lvUp.getPosition().x - lvUp.getSize().x / 2 &&
                        touchPos.y < lvUp.getPosition().y + lvUp.getSize().y / 2 && touchPos.y > lvUp.getPosition().y - lvUp.getSize().y / 2)
                {
                    isWindow = true;
                }
            }

        }
    }
}
