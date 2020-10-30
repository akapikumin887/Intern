package com.codedynamix.pottyari.Status;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.MotionEvent;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.BaseClass.BaseScene;
import com.codedynamix.pottyari.BaseClass.Object;
import com.codedynamix.pottyari.Battle.BattleSystem;
import com.codedynamix.pottyari.Object.Figure;
import com.codedynamix.pottyari.Object.HeroStatus;
import com.codedynamix.pottyari.Scene.HomeScene;
import com.codedynamix.pottyari.Scene.ShopScene;
import com.codedynamix.pottyari.System.StepCount;
import com.codedynamix.pottyari.System.Vector2;
import com.codedynamix.pottyari.UI.ChoiseBack;
import com.codedynamix.pottyari.UI.MessageWindow;
import com.codedynamix.pottyari.UI.ShopText;
import com.codedynamix.pottyari.UI.Status;
import com.codedynamix.pottyari.UI.StatusButton;

import static java.lang.Math.round;

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

    private ShopText not_enough;
    private MessageWindow not_enoughwin;

    private boolean isWindow;
    private boolean isnot_enoughwin;

    private StatusButton pt;
    private StatusButton lvUp;
    private StatusButton shop;

    private Figure point;
    private Figure stLv;
    private Figure stHp;
    private Figure stAt;
    private Figure lvPoint;

    SharedPreferences pointPrefs;
    SharedPreferences lvpointPrefs;

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

        not_enough  =   new ShopText(1);
        not_enoughwin = new MessageWindow(3);
        not_enoughwin.setSize(new Vector2(GameActivity.getBaseWid() - 100.0f,400.0f));
        not_enoughwin.setPosition(new Vector2());


        //ボタン
        pt = new StatusButton(0);
        lvUp = new StatusButton(1);
        shop = new StatusButton(2);


        //レベルアップで使用するポイント数
        lvpointPrefs= GameActivity.getActivity().getSharedPreferences("lvPoint", Context.MODE_PRIVATE);
        int lp = lvpointPrefs.getInt("int",10);

        lvPoint = new Figure(lp,0);
        lvPoint.setSize(new Vector2(100.0f,100.0f));
        lvPoint.setPosition(new Vector2(0.0f,lvPoint.getSize().y / 1.5f));


        //ポイント(数値)
        pointPrefs= GameActivity.getActivity().getSharedPreferences("point", Context.MODE_PRIVATE);
        int p = pointPrefs.getInt("int",0) + StepCount.getTtPoint() / 10;  //今回歩いた歩数から今回得られるポイントを取得
        if(StepCount.getTtPoint() % 10 > 4)    //四捨五入
            p++;

        StepCount.resetTtPoint();   //ちゃんとポイントをゲットしたので値を初期化しておく
        StepCount.save();           //念を入れて保存

        //ポイントの変更をすぐ保存しておく
        SharedPreferences.Editor editor = pointPrefs.edit();
        editor.putInt("int",p);
        editor.apply();

        //表示が重ならないように調整
        point = new Figure(Math.min(p,999999),1);
        point.setSize(new Vector2(100.0f,100.0f));
        point.setPosition(new Vector2(GameActivity.getBaseWid() / 2 - 100.0f,GameActivity.getBaseHei() / 2 - point.getSize().y * 1.5f));

        //ステータス(数値)
        stLv = new Figure(HeroStatus.getLv(),1);
        stLv.setSize(new Vector2(level.getSize().y,level.getSize().y));
        stLv.setPosition(new Vector2(GameActivity.getBaseWid() / 2 - stLv.getSize().x / 2,level.getPosition().y/1.4f));

        stHp = new Figure(HeroStatus.getHp(),1);
        stHp.setSize(new Vector2(hitPoint.getSize().y,hitPoint.getSize().y));
        stHp.setPosition(new Vector2(GameActivity.getBaseWid() / 2 - stHp.getSize().x / 2,hitPoint.getPosition().y/2-30.0f));

        stAt = new Figure(HeroStatus.getAttack(),1);
        stAt.setSize(new Vector2(attack.getSize().y,attack.getSize().y));
        stAt.setPosition(new Vector2(GameActivity.getBaseWid() / 2 - stAt.getSize().x / 2,attack.getPosition().y/0.4f));

        isWindow = false;
        isnot_enoughwin = false;
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

        //購入確認
        if(isWindow)
        {
            window.draw();
            lvPoint.draw();
            yeswnd.draw();
            nownd.draw();
            yes.draw();
            no.draw();
        }

        //ポイントが足りなかったときに出すやつ
        if (isnot_enoughwin)
        {
            not_enoughwin.draw();
            not_enough.draw();
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
                    if(point.getValue() >= lvPoint.getValue())
                    {
                        //ポイントを払う
                        point.setValue(Math.min((point.getValue() - lvPoint.getValue()),999999));
                        //次に必要なポイントを上げる
                        lvPoint.setValue((int)((float)lvPoint.getValue() * 1.1f));

                        //ステータスを上げる
                        BattleSystem.playerGrow();
                        stLv.setValue(HeroStatus.getLv());
                        stHp.setValue(HeroStatus.getHp());
                        stAt.setValue(HeroStatus.getAttack());

                        //情報を保存
                        SharedPreferences.Editor editor = pointPrefs.edit();
                        editor.putInt("int",point.getValue());
                        editor.apply();

                        editor = lvpointPrefs.edit();
                        editor.putInt("int",lvPoint.getValue());
                        editor.apply();
                    }
                    else
                    {
                        //ptが足りなかったら
                        isnot_enoughwin = true;
                    }

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

                //ptが足りませんのウィンドウが出ていたら
                if(isnot_enoughwin)
                    isnot_enoughwin = false;
                else
                {
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
}
