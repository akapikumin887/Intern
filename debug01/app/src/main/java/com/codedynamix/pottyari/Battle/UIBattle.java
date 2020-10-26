package com.codedynamix.pottyari.Battle;

import android.view.MotionEvent;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.BaseClass.BaseScene;
import com.codedynamix.pottyari.BaseClass.Object;
import com.codedynamix.pottyari.Object.EnemyStatus;
import com.codedynamix.pottyari.Object.Figure;
import com.codedynamix.pottyari.Object.HeroStatus;
import com.codedynamix.pottyari.Scene.ProgressScene;
import com.codedynamix.pottyari.System.Vector2;
import com.codedynamix.pottyari.UI.BattleText;
import com.codedynamix.pottyari.UI.ChoiseBack;
import com.codedynamix.pottyari.UI.HpBar;
import com.codedynamix.pottyari.UI.MessageWindow;
import com.codedynamix.pottyari.UI.Status;

public class UIBattle extends Object
{
    //ステータスの文字
    private Status lv;
    private Status hp;
    private Status at;

    //ステータスの値
    private Figure stLv;
    private Figure stHp;
    private Figure stAt;

    //敵のHPゲージ
    private HpBar redBar;
    private HpBar greenBar;

    //敵のHP表示を簡単にするための変数たち
    private float phoneLeftWidth;
    private float remnantsHp;

    //メッセージウィンドウとボタン
    private MessageWindow window;
    private BattleCommand attack;
    private BattleCommand heal;

    //戻るボタン
    private ChoiseBack back;

    //ウィンドウに表示される文章
    private BattleText[] text;
    private final int TEXT_MAX = 4;
    private int nowTextNum; //今のテキストの数 updateで増える
    private int textNum;    //テキスト描画の数

    //戦闘テキストを描画しやすくするための変数たち
    private Vector2 textBasePos;
    private float wndTop;

    //ログ関係
    private int count;      //updateで増やしてフレーム制御
    private int cCount;     //countが一定数に達したら増やして制御
    private boolean isLog;
    private boolean isFinish;

    //何の行動をしたかを管理
    private enum Action
    {
        ACTION_ATTACK,
        ACTION_HEAL,
        ACTION_ENEMY_ATTACK,
        ACTION_ATTACK_APPO,
        ACTION_HEAL_APPO,
        ACTION_ENEMY_ATTACK_APPO,
        ACTION_NONE;
    }

    private Action action;

    public UIBattle()
    {
        super();
        setLayer(Layer.LAYER_BUTTON);
        phoneLeftWidth = -GameActivity.getBaseWid() / 2;

        //ステータスの文字
        lv = new Status(3);
        at = new Status(4);
        hp = new Status(5);

        //ステータスの値
        stLv = new Figure(HeroStatus.getLv(),0);
        stLv.setSize(new Vector2(lv.getSize().y,lv.getSize().y));
        stLv.setPosition(new Vector2(-GameActivity.getBaseWid() / 4,lv.getPosition().y - lv.getSize().y));
        stHp = new Figure(HeroStatus.getHp(),0);
        stHp.setSize(new Vector2(hp.getSize().y,hp.getSize().y));
        stHp.setPosition(new Vector2(-GameActivity.getBaseWid() / 4,hp.getPosition().y - hp.getSize().y));
        stAt = new Figure(HeroStatus.getAttack(),0);
        stAt.setSize(new Vector2(at.getSize().y,at.getSize().y));
        stAt.setPosition(new Vector2(-GameActivity.getBaseWid() / 4,at.getPosition().y - at.getSize().y));

        //敵の残りHP 100分率
        remnantsHp = 1.0f;

        //敵のHPバー
        redBar = new HpBar(1);
        greenBar = new HpBar(0);

        //メッセージウィンドウとボタン
        window = new MessageWindow(4);
        attack = new BattleCommand(0);
        attack.setPosition(new Vector2(-attack.getSize().x / 2 - 50.0f,window.getPosition().y + window.getSize().y / 2 + attack.getSize().y / 1.5f));
        heal   = new BattleCommand(1);
        heal.setPosition(new Vector2(attack.getSize().x / 2 + 50.0f,window.getPosition().y + window.getSize().y / 2 + attack.getSize().y / 1.5f));

        //テキストの1行目
        textBasePos = new Vector2(GameActivity.getBaseWid() / 6,((GameActivity.getBaseWid() / 3 * 2) / 16) * 1.5f);
        wndTop = window.getPosition().y + window.getSize().y / 2;

        //テキスト4行の初期化
        text = new BattleText[4];

        for(int i = 0; i < TEXT_MAX; i++)
        {
            text[i] = new BattleText(i);
            text[i].setPosition(new Vector2(textBasePos.x,wndTop - textBasePos.y * (i + 1)));

        }
        //初めは2行から開始
        textNum = 2;

        //初期化
        count = 0;
        cCount = 0;
        isLog = false;
        isFinish = false;
        action = Action.ACTION_NONE;

        //戻る
        back = new ChoiseBack(2);
    }

    @Override
    public void update()
    {
        stLv.update();
        stHp.update();
        stAt.update();

        count++;
        if(count > 40)
        {
            if(!isFinish)
            {
                cCount++;
                count = 0;
            }

            if(nowTextNum < textNum)
                nowTextNum++;

            //HPバーの減るタイミングとテキストの出るタイミングがおかしい
            //一応調整はできたけどこれでいいかは不明
            int num;
            switch (action)
            {
                case ACTION_ATTACK:

                    //自分の攻撃で敵を倒した時の処理
                    if(EnemyStatus.getEnemyHp() == 0)
                    {
                        textNum = nowTextNum = 1;
                        text[0] = new BattleText(10);
                        text[0].setPosition(new Vector2(textBasePos.x,wndTop - textBasePos.y));

                        action = Action.ACTION_NONE;
                        isFinish = true;
                        count = 0;
                    }
                    else
                        action = Action.ACTION_ENEMY_ATTACK;
                    break;
                case ACTION_ENEMY_ATTACK:
                    num = BattleSystem.enemyAttack();
                    stHp.setValue(stHp.getValue() - num);
                    HeroStatus.setHp(HeroStatus.getHp() - num);
                    action = Action.ACTION_NONE;
                    break;
                case ACTION_HEAL:
                    num = BattleSystem.playerHeal();
                    stHp.setValue(stHp.getValue() + num);
                    HeroStatus.setHp(HeroStatus.getHp() + num);
                    action = Action.ACTION_ENEMY_ATTACK;
                    break;
                case ACTION_ATTACK_APPO:
                    action = Action.ACTION_ATTACK;

                    num = BattleSystem.playerAttack();
                    EnemyStatus.setEnemyHp(EnemyStatus.getEnemyHp() - num);
                    remnantsHp = (float)EnemyStatus.getEnemyHp() / (float)EnemyStatus.getEnemyMaxHp();
                    greenBar.setSize(new Vector2(GameActivity.getBaseWid() * remnantsHp, greenBar.getSize().y));
                    greenBar.setPosition(new Vector2(phoneLeftWidth + greenBar.getSize().x * 0.5f, greenBar.getPosition().y));
                    greenBar.setTexSize(new Vector2( remnantsHp,0.3333f));

                    break;
                case ACTION_HEAL_APPO:
                    action = Action.ACTION_HEAL;
                    break;
                case ACTION_ENEMY_ATTACK_APPO:
                    action = Action.ACTION_ENEMY_ATTACK;
                    break;
            }


            //自分と相手の行動が終わったタイミング
            if(cCount > 3)
            {
                //自分が死んでいるとき
                if(HeroStatus.getHp() <= 0)
                {

                }
                else
                {
                    isLog = false;
                    text[0] = new BattleText(1);
                    text[0].setPosition(new Vector2(textBasePos.x,wndTop - textBasePos.y));
                    textNum = 1;
                    nowTextNum = 1;
                }
            }

        }

        //敵を倒した時
        if(isFinish && count > 90)
        {
            BaseScene.setnextScene(new ProgressScene());
            HeroStatus.setIsBattle(false);
        }
    }

    @Override
    public void draw()
    {
        window.draw();
        attack.draw();
        heal.draw();

        lv.draw();
        hp.draw();
        at.draw();

        stLv.draw();
        stHp.draw();
        stAt.draw();

        redBar.draw();
        greenBar.draw();

        //戻るボタン
        back.draw();

        for(int i = 0; i < nowTextNum; i++)
        {
            text[i].draw();
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
            //ログが流れていない時にコマンドが押せる
            if(!isLog)
            {
                //たたかう
                if(touchPos.x < attack.getPosition().x + attack.getSize().x / 2 && touchPos.x > attack.getPosition().x - attack.getSize().x / 2 &&
                        touchPos.y < attack.getPosition().y + attack.getSize().y / 2 && touchPos.y > attack.getPosition().y - attack.getSize().y / 2)
                {
                    text[0] = new BattleText(2);
                    text[0].setPosition(new Vector2(textBasePos.x,wndTop - textBasePos.y));
                    text[1] = new BattleText(3);
                    text[1].setPosition(new Vector2(textBasePos.x,wndTop - textBasePos.y * 2.0f));
                    text[2] = new BattleText(4);
                    text[2].setPosition(new Vector2(textBasePos.x,wndTop - textBasePos.y * 3.0f));
                    text[3] = new BattleText(5);
                    text[3].setPosition(new Vector2(textBasePos.x,wndTop - textBasePos.y * 4.0f));

                    textNum = 4;
                    nowTextNum = 1;
                    count = 0;
                    cCount = 0;
                    isLog = true;
                    action = Action.ACTION_ATTACK_APPO;
                }

                //回復
                if(touchPos.x < heal.getPosition().x + heal.getSize().x / 2 && touchPos.x > heal.getPosition().x - heal.getSize().x / 2 &&
                        touchPos.y < heal.getPosition().y + heal.getSize().y / 2 && touchPos.y > heal.getPosition().y - heal.getSize().y / 2)
                {
                    text[0] = new BattleText(6);
                    text[0].setPosition(new Vector2(textBasePos.x,wndTop - textBasePos.y));
                    text[1] = new BattleText(7);
                    text[1].setPosition(new Vector2(textBasePos.x,wndTop - textBasePos.y * 2.0f));
                    text[2] = new BattleText(4);
                    text[2].setPosition(new Vector2(textBasePos.x,wndTop - textBasePos.y * 3.0f));
                    text[3] = new BattleText(5);
                    text[3].setPosition(new Vector2(textBasePos.x,wndTop - textBasePos.y * 4.0f));

                    textNum = 4;
                    nowTextNum = 1;
                    cCount = 0;
                    isLog = true;
                    action = Action.ACTION_HEAL_APPO;
                }
                //戻るボタン
                if(touchPos.x < back.getPosition().x + back.getSize().x / 2 && touchPos.x > back.getPosition().x - back.getSize().x / 2 &&
                        touchPos.y < back.getPosition().y + back.getSize().y / 2 && touchPos.y > back.getPosition().y - back.getSize().y / 2)
                {
                    BaseScene.setnextScene(new ProgressScene());
                }
            }
            else
            {


            }
        }
    }

    public boolean getIsLog()
    {
        return isLog;
    }
}
