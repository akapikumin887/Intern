package com.example.deb.Progress;

import android.view.MotionEvent;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.BaseScene;
import com.example.deb.BaseClass.Object;
import com.example.deb.Scene.HomeScene;
import com.example.deb.Scene.StepScene;
import com.example.deb.Step.PlayerStep;
import com.example.deb.Step.UIStep;
import com.example.deb.System.StepCount;
import com.example.deb.System.Vector2;
import com.example.deb.UI.ChoiseBack;
import com.example.deb.UI.GameWay;
import com.example.deb.UI.MessageWindow;

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

    private  static int MAX=0;

    private static int bossstep;

    public UIProgress()
    {
        super();
        setLayer(Layer.LAYER_BUTTON);

        progsHero = new ProgressHero();
        coffin = new Coffin();

        messageWindow = new MessageWindow(4);
        back = new ChoiseBack(2);


        MAX=100000;
        bossstep=MAX- StepCount.getAll();

        if (bossstep<=0)
        {
            bossstep=0;
        }

        playerStep=new PlayerStep();

        way = new GameWay(0);
        player_icom = new GameWay(1);
        boss_icom = new GameWay(2);

    }

    @Override
    public void draw()
    {
        progsHero.draw();
        //coffin.draw();

        messageWindow.draw();
        back.draw();

        way.draw();
        boss_icom.draw();
        player_icom.draw();

    }

    @Override
    public void update()
    {
        progsHero.update();
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

            //Step遷移
            if(touchPos.x < way.getPosition().x + way.getSize().x / 2 && touchPos.x > way.getPosition().x - way.getSize().x / 2 &&
                    touchPos.y < way.getPosition().y + way.getSize().y / 2 && touchPos.y > way.getPosition().y - way.getSize().y / 2)
            {
                BaseScene.setnextScene(new StepScene());
            }
        }
    }
    public static int getbossstep(){return bossstep;}
}
