package com.codedynamix.pottyari.Shop;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.MotionEvent;



public class UIShop extends Object
{
    private ChoiseBack left;
    private ChoiseBack right;
    private ChoiseBack back;
    private ChoiseBack yes;
    private ChoiseBack no;

    private StatusButton pointbutton;
    private StatusButton shopbutton;
    private Figure point;
    private static SharedPreferences pointPrefs;

    private Item heal;
    private Item resurrection;

    private Reinforcement reinforcement;

    private ItemName yasai;
    private ItemName eiyou;
    private ItemName buki;

    private MessageWindow window;
    private MessageWindow yeswnd;
    private MessageWindow nownd;
    private boolean isWindow;

    private int itemSelect;

    public UIShop()
    {
        super();
        setLayer(Layer.LAYER_BUTTON);

        //左右ボタン
        left = new ChoiseBack(0);
        right = new ChoiseBack(1);

        //戻る
        back = new ChoiseBack(2);

        //ポイントとショップ
        pointbutton = new StatusButton(0);
        shopbutton = new StatusButton(2);
        shopbutton.setPosition(new Vector2(0.0f,back.getPosition().y - shopbutton.getSize().y * 1.5f));

        pointPrefs = GameActivity.getActivity().getSharedPreferences("point", Context.MODE_PRIVATE);
        point = new Figure(pointPrefs.getInt("int",1000),1);
        point.setSize(new Vector2(100.0f,100.0f));
        point.setPosition(new Vector2(GameActivity.getBaseWid() / 2 - 100.0f,GameActivity.getBaseHei() / 2 - point.getSize().y * 1.5f));

        //アイテム
        heal = new Item(0.5f);
        resurrection = new Item(0.75f);

        //武器強化
        reinforcement=new Reinforcement();

        //野菜ジュース
        yasai = new ItemName(0);
        //栄養ドリンク
        eiyou = new ItemName(1);
        //武器強化
        buki  = new ItemName(2);

        //ウィンドウと選択肢
        window = new MessageWindow(2);

        yeswnd = new MessageWindow(3);
        yeswnd.setPosition(new Vector2(-yeswnd.getSize().x / 2,yeswnd.getPosition().y));
        nownd = new MessageWindow(3);
        nownd.setPosition(new Vector2(nownd.getSize().x / 2,nownd.getPosition().y));

        yes = new ChoiseBack(4);
        yes.setPosition(yeswnd.getPosition());
        no = new ChoiseBack(5);
        no.setPosition(nownd.getPosition());


        isWindow = false;

        itemSelect = 0;
    }

    @Override
    public void draw()
    {
        //左右ボタン
        if(itemSelect != 0)
            left.draw();
        if(itemSelect != 2)
            right.draw();


        //戻るボタン
        back.draw();

        //ポイントとショップ
        pointbutton.draw();
        shopbutton.draw();
        point.draw();

        //アイテムボタン
        switch(itemSelect)
        {
            case 0:
                heal.draw();
                yasai.draw();
                break;
            case 1:
                resurrection.draw();
                eiyou.draw();
                break;
            case 2:
                reinforcement.draw();
                buki.draw();
                break;
        }



        //ウィンドウ表示(選んだ時のみ)
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
    public void update()
    {
        point.update();
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
            //windowが出ているときのみ
            if(isWindow)
            {
                //買う
                if(touchPos.x < yeswnd.getPosition().x + yeswnd.getSize().x / 2 && touchPos.x > yeswnd.getPosition().x - yeswnd.getSize().x / 2 &&
                        touchPos.y < yeswnd.getPosition().y + yeswnd.getSize().y / 2 && touchPos.y > yeswnd.getPosition().y - yeswnd.getSize().y / 2)
                {
                    isWindow = false;
                    switch(itemSelect)
                    {
                        case 0:
                            point.setValue(point.getValue() - 200);
                            HeroStatus.setHealCnt(HeroStatus.getHealCnt() + 1);
                            break;
                        case 1:
                            point.setValue(point.getValue() - 300);
                            HeroStatus.setReviveCnt(HeroStatus.getReviveCnt() + 1);
                            break;
                        case 2:
                            point.setValue(point.getValue() - 400);
                            HeroStatus.setReviveCnt(HeroStatus.getReviveCnt() + 1);
                            break;
                    }
                    SharedPreferences.Editor editor = pointPrefs.edit();
                    editor.putInt("int",point.getValue());
                    editor.apply();
                }
                //買わない
                if(touchPos.x < nownd.getPosition().x + nownd.getSize().x / 2 && touchPos.x > nownd.getPosition().x - nownd.getSize().x / 2 &&
                        touchPos.y < nownd.getPosition().y + nownd.getSize().y / 2 && touchPos.y > nownd.getPosition().y - nownd.getSize().y / 2)
                {
                    isWindow = false;
                }
            }
            else
            {
                //Status遷移
                if(touchPos.x < back.getPosition().x + back.getSize().x / 2 && touchPos.x > back.getPosition().x - back.getSize().x / 2 &&
                        touchPos.y < back.getPosition().y + back.getSize().y / 2 && touchPos.y > back.getPosition().y - back.getSize().y / 2)
                {
                    BaseScene.setnextScene(new StatusScene());
                }

                //左ボタン
                if(touchPos.x < left.getPosition().x + left.getSize().x / 2 && touchPos.x > left.getPosition().x - left.getSize().x / 2 &&
                        touchPos.y < left.getPosition().y + left.getSize().y / 2 && touchPos.y > left.getPosition().y - left.getSize().y / 2)
                {
                    if(itemSelect >=1&&itemSelect<3)
                    {
                        itemSelect--;
                    }
                }

                //右ボタン
                if(touchPos.x < right.getPosition().x + right.getSize().x / 2 && touchPos.x > right.getPosition().x - right.getSize().x / 2 &&
                        touchPos.y < right.getPosition().y + right.getSize().y / 2 && touchPos.y > right.getPosition().y - right.getSize().y / 2)
                {
                    if (itemSelect >=0&&itemSelect<2)
                    {
                        itemSelect++;
                    }
                }

                //アイテム購入
                if(touchPos.x < heal.getPosition().x + heal.getSize().x / 2 && touchPos.x > heal.getPosition().x - heal.getSize().x / 2 &&
                        touchPos.y < heal.getPosition().y + heal.getSize().y / 2 && touchPos.y > heal.getPosition().y - heal.getSize().y / 2)
                {
                    switch(itemSelect)
                    {
                        case 0:
                            if(point.getValue() >= 200)
                                isWindow = true;
                            //else

                            break;
                        case 1:
                            if(point.getValue() >= 300)
                                isWindow = true;
                            //else

                            break;

                    }
                }
            }

        }
    }
}
