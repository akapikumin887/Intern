package com.codedynamix.pottyari.Progress;

import android.view.MotionEvent;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.BaseClass.BaseScene;
import com.codedynamix.pottyari.BaseClass.Object;
import com.codedynamix.pottyari.Object.EnemyStatus;
import com.codedynamix.pottyari.Object.Figure;
import com.codedynamix.pottyari.Object.HeroStatus;
import com.codedynamix.pottyari.Scene.BattleScene;
import com.codedynamix.pottyari.Scene.HomeScene;
import com.codedynamix.pottyari.Scene.ProgressScene;
import com.codedynamix.pottyari.Scene.StepScene;
import com.codedynamix.pottyari.Step.PlayerStep;
import com.codedynamix.pottyari.Step.UIStep;
import com.codedynamix.pottyari.System.Vector2;
import com.codedynamix.pottyari.UI.BattleMark;
import com.codedynamix.pottyari.UI.ChoiseBack;
import com.codedynamix.pottyari.UI.Exclamation;
import com.codedynamix.pottyari.UI.GameWay;
import com.codedynamix.pottyari.UI.MessageWindow;
import com.codedynamix.pottyari.UI.Status;

public class UIProgress extends Object
{
    private ProgressHero progsHero;
    private Coffin coffin;

    private MessageWindow messageWindow;
    private ChoiseBack back;
    private GameWay way;
    private GameWay boss_icom;
    private GameWay player_icom;
    private UIStep uiStep;
    private PlayerStep playerStep;
    private Exclamation exclamation;
    private BattleMark battleMark;

    //ステータスの文字
    private Status lv;
    private Status hp;
    private Status at;

    //ステータスの値
    private Figure stLv;
    private Figure stHp;
    private Figure stAt;

    public UIProgress()
    {
        super();
        setLayer(Layer.LAYER_BUTTON);

        progsHero = new ProgressHero();
        coffin = new Coffin();

        messageWindow = new MessageWindow(4);

        back = new ChoiseBack(2);
        battleMark=new BattleMark();

        playerStep=new PlayerStep();

        way = new GameWay(0);
        player_icom = new GameWay(1);
        boss_icom = new GameWay(2);

        exclamation = new Exclamation();

        exclamation.setPosition(new Vector2(0.0f,progsHero.getPosition().y + progsHero.getSize().y / 1.5f));

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
    }


    @Override
    public void draw()
    {
        //勇者が死んでいたらもちろん背景は動かない
        if(HeroStatus.getHp() <= 0)
            coffin.draw();
        else
            progsHero.draw();

        messageWindow.draw();
        back.draw();

        if (HeroStatus.getIsBattle() && HeroStatus.getHp() > 0)
        {
            battleMark.draw();
        }

        way.draw();
        boss_icom.draw();
        player_icom.draw();

        if(ProgressScene.getIsBattle())
            exclamation.draw();

        lv.draw();
        hp.draw();
        at.draw();

        stLv.draw();
        stHp.draw();
        stAt.draw();
    }

    @Override
    public void update()
    {
        progsHero.update();

        stLv.update();
        stHp.update();
        stAt.update();
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
            //今回バトルしないならば
            if(!ProgressScene.getCanBattle())
            {
                //Home遷移
                if(touchPos.x < back.getPosition().x + back.getSize().x / 2 && touchPos.x > back.getPosition().x - back.getSize().x / 2 &&
                        touchPos.y < back.getPosition().y + back.getSize().y / 2 && touchPos.y > back.getPosition().y - back.getSize().y / 2)
                {
                    BaseScene.setnextScene(new HomeScene());
                }

                //Step遷移
                if(touchPos.x < way.getPosition().x + way.getSize().x / 2 && touchPos.x > way.getPosition().x - way.getSize().x / 2 &&
                        touchPos.y < way.getPosition().y + way.getSize().y / 2 && touchPos.y > way.getPosition().y - way.getSize().y / 2)
                {
                    BaseScene.setnextScene(new StepScene());
                }
            }

            //Battle遷移
            if(touchPos.x < battleMark.getPosition().x + battleMark.getSize().x / 2 && touchPos.x > battleMark.getPosition().x - battleMark.getSize().x / 2 &&
                    touchPos.y < battleMark.getPosition().y + battleMark.getSize().y / 2 && touchPos.y > battleMark.getPosition().y - battleMark.getSize().y / 2)
            {
                if (HeroStatus.getIsBattle() && HeroStatus.getHp() > 0)
                {
                    BaseScene.setnextScene(new BattleScene(ProgressScene.getEnemyType()));
                    EnemyStatus.init();
                }
            }
        }
    }
}
