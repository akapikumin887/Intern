package com.codedynamix.pottyari.Shop;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.MotionEvent;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.BaseClass.BaseScene;
import com.codedynamix.pottyari.BaseClass.Object;
import com.codedynamix.pottyari.Battle.BattleSystem;
import com.codedynamix.pottyari.Object.Figure;
import com.codedynamix.pottyari.Object.HeroStatus;
import com.codedynamix.pottyari.Object.Item;
import com.codedynamix.pottyari.Scene.StatusScene;
import com.codedynamix.pottyari.System.NewEnter;
import com.codedynamix.pottyari.System.Vector2;
import com.codedynamix.pottyari.UI.ChoiseBack;
import com.codedynamix.pottyari.UI.Information;
import com.codedynamix.pottyari.UI.ItemName;
import com.codedynamix.pottyari.UI.MessageWindow;
import com.codedynamix.pottyari.UI.Reinforcement;
import com.codedynamix.pottyari.UI.ShopText;
import com.codedynamix.pottyari.UI.Status;
import com.codedynamix.pottyari.UI.StatusButton;


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

    private ShopText pt;
    private ShopText not_enough;
    private ShopText possession;

    private MessageWindow window;
    private MessageWindow window1;
    private MessageWindow yeswnd;
    private MessageWindow nownd;
    private MessageWindow not_enoughwin;
    private boolean isWindow;
    private boolean isnot_enoughwin;

    private int itemSelect;

    private final int VEGETABLES_DRINK_VALUE = 500;
    private final int ENERGY_DRINK_VALUE = 1000;
    private Figure vetables;
    private Figure energy;

    private Status level;
    private Status attack;
    private Figure wpLv;
    private Figure wp;

    private int bukiPo;
    private Figure bukipo;

    private Figure Heal;
    private Figure Revive;

    private Information info;

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
        shopbutton.setSize(new Vector2(760.0f,190.0f));
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

        //消費ｐｔ
        pt  =   new ShopText(0);
        //pt不足
        not_enough  =   new ShopText(1);
        //所持数
        possession  =   new ShopText(2);

        vetables = new Figure(VEGETABLES_DRINK_VALUE,1);
        vetables.setSize(new Vector2(100.0f,100.0f));
        vetables.setPosition(new Vector2(pt.getSize().x/2-size.x/0.6f,GameActivity.getBaseHei() / 2- size.y/0.185f));

        energy = new Figure(ENERGY_DRINK_VALUE,1);
        energy.setSize(new Vector2(100.0f,100.0f));
        energy.setPosition(new Vector2(pt.getSize().x/2-size.x/0.8f,GameActivity.getBaseHei() / 2- size.y/0.185f));

        //ウィンドウと選択肢
        window = new MessageWindow(2);
        window1 =new MessageWindow(0);
        yeswnd = new MessageWindow(3);
        yeswnd.setPosition(new Vector2(-yeswnd.getSize().x / 2,yeswnd.getPosition().y));
        nownd = new MessageWindow(3);
        nownd.setPosition(new Vector2(nownd.getSize().x / 2,nownd.getPosition().y));
        not_enoughwin = new MessageWindow(3);
        not_enoughwin.setSize(new Vector2(GameActivity.getBaseWid() - 100.0f,400.0f));
        not_enoughwin.setPosition(new Vector2());

        yes = new ChoiseBack(4);
        yes.setPosition(yeswnd.getPosition());
        no = new ChoiseBack(5);
        no.setPosition(nownd.getPosition());

        level = new Status(0);
        level.setSize(new Vector2(360.0f,120.0f));
        level.setPosition(new Vector2(-GameActivity.getBaseWid() / 2 + size.x/0.5f,-GameActivity.getBaseHei() / 2+ size.y/0.5f));

        attack= new Status(1);
        attack.setSize(new Vector2(360.0f,120.0f));
        attack.setPosition(new Vector2(-GameActivity.getBaseWid() / 2 + size.x/0.5f,-GameActivity.getBaseHei() / 2+ size.y/1.9f));

        wpLv = new Figure(HeroStatus.getWeaponLv(),1);
        wpLv.setSize(new Vector2(level.getSize().y,level.getSize().y));
        wpLv.setPosition(new Vector2(GameActivity.getBaseWid() / 2 - wpLv.getSize().x / 2,-GameActivity.getBaseHei() / 2+ size.y/0.5f));

        wp = new Figure(HeroStatus.getWp(),1);
        wp.setSize(new Vector2(attack.getSize().y,attack.getSize().y));
        wp.setPosition(new Vector2(GameActivity.getBaseWid() / 2 - wp.getSize().x / 2,-GameActivity.getBaseHei() / 2+ size.y/1.9f));

        //武器のレベル上げにかかるptは 300 + (レベルの値-1) * 150 である
        bukiPo = 300 + 150 * (int)(HeroStatus.getWeaponLv() - 1);
        bukipo = new Figure(bukiPo,1);
        bukipo.setSize(new Vector2(100.0f,100.0f));
        bukipo.setPosition(new Vector2(pt.getSize().x/2-size.x/0.8f,GameActivity.getBaseHei() / 2- size.y/0.185f));

        Heal = new Figure(HeroStatus.getHealCnt(),1);
        Heal.setSize(new Vector2(120.0f,120.0f));
        Heal.setPosition(new Vector2(GameActivity.getBaseWid() / 2 - size.x / 2,-GameActivity.getBaseHei() / 2+ size.y/0.5f));

        Revive = new Figure(HeroStatus.getReviveCnt(),1);
        Revive.setSize(new Vector2(120.0f,120.0f));
        Revive.setPosition(new Vector2(GameActivity.getBaseWid() / 2 - size.x / 2,-GameActivity.getBaseHei() / 2+ size.y/0.5f));

        isWindow = false;

        itemSelect = 0;

        info = new Information();
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

        pt.draw();

        //アイテムボタン
        switch(itemSelect)
        {
            case 0:
                heal.draw();
                yasai.draw();
                possession.draw();
                vetables.draw();
                Heal.draw();
                break;
            case 1:
                resurrection.draw();
                eiyou.draw();
                possession.draw();
                energy.draw();
                Revive.draw();
                break;
            case 2:
                reinforcement.draw();
                buki.draw();
                wpLv.draw();
                wp.draw();
                level.draw();
                attack.draw();
                bukipo.draw();
                break;
        }



        //ウィンドウ表示(選んだ時のみ)
        if(isWindow)
        {
            if(itemSelect==2)
            {
                window1.draw();
            }
            else
            {
                window.draw();
            }
            yeswnd.draw();
            nownd.draw();
            yes.draw();
            no.draw();
        }

        if (isnot_enoughwin)
        {
            not_enoughwin.draw();
            not_enough.draw();
        }

        info.draw(2);
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
            //infoが見えてる間はそこを進めることしかできない
            if(NewEnter.getInformScene(2))
            {
                info.addTouch();
            }
            else
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
                                point.setValue(point.getValue() - VEGETABLES_DRINK_VALUE);
                                HeroStatus.setHealCnt(HeroStatus.getHealCnt() + 1);
                                Heal.setValue(HeroStatus.getHealCnt());
                                break;
                            case 1:
                                point.setValue(point.getValue() - ENERGY_DRINK_VALUE);
                                HeroStatus.setReviveCnt(HeroStatus.getReviveCnt() + 1);

                                if(HeroStatus.getHp() <= 0)
                                    HeroStatus.setHp(BattleSystem.playerResurrection());

                                Revive.setValue(HeroStatus.getReviveCnt());
                                break;
                            case 2:
                                if(HeroStatus.getWeaponLv() < 99)
                                {
                                    BattleSystem.weaponGrow();
                                    point.setValue(point.getValue() - bukiPo );
                                    wpLv.setValue(HeroStatus.getWeaponLv());
                                    wp.setValue(HeroStatus.getWp());
                                    bukiPo = 300 + 150 * (int)(HeroStatus.getWeaponLv() - 1);
                                    bukipo.setValue(bukiPo);
                                }
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

                    if(isnot_enoughwin)
                    {
                        isnot_enoughwin=false;
                    }
                    else
                    {
                        //アイテム購入
                        if (touchPos.x < heal.getPosition().x + heal.getSize().x / 2 && touchPos.x > heal.getPosition().x - heal.getSize().x / 2 &&
                                touchPos.y < heal.getPosition().y + heal.getSize().y / 2 && touchPos.y > heal.getPosition().y - heal.getSize().y / 2) {
                            switch (itemSelect) {
                                case 0:
                                    if (point.getValue() >= VEGETABLES_DRINK_VALUE) {
                                        isWindow = true;
                                        break;
                                    } else {
                                        isnot_enoughwin = true;
                                        break;
                                    }
                                case 1:
                                    if (point.getValue() >= ENERGY_DRINK_VALUE) {
                                        isWindow = true;
                                        break;
                                    } else {
                                        isnot_enoughwin = true;
                                        break;
                                    }
                                case 2:
                                    if (point.getValue() >= bukiPo) {
                                        isWindow = true;
                                        break;
                                    } else {
                                        isnot_enoughwin = true;
                                        break;
                                    }
                            }
                        }
                    }
                }
            }
        }
    }
}
