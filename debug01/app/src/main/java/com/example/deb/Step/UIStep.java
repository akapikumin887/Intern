package com.example.deb.Step;

import android.view.MotionEvent;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.BaseScene;
import com.example.deb.BaseClass.Object;
import com.example.deb.Object.Figure;
import com.example.deb.Scene.ProgressScene;
import com.example.deb.System.StepCount;
import com.example.deb.System.Vector2;
import com.example.deb.UI.ChoiseBack;
import com.example.deb.UI.Game_stepword;

public class UIStep extends Object
{
    private ChoiseBack back;
    private Game_stepword word;
    private Game_stepword bossword;
    private Game_stepword word_agter;
    private Game_stepword walk1;
    private Game_stepword walk2;
    private Figure step;
    private Figure step1;

    private  static  int MAX=0;

    public  UIStep()
    {
        super();
        setLayer(Layer.LAYER_BUTTON);

        //戻る
        back = new ChoiseBack(2);

        //総歩数
        word = new Game_stepword(0);
        //ボスまで
        bossword = new Game_stepword(1);
        //あと
        word_agter = new Game_stepword(2);
        //歩1
        walk1 = new Game_stepword(3);

        walk2 = new Game_stepword(4);


        //歩数 ここからはそのうち消す
        step = new Figure(StepCount.getAll(),1);
        step.setSize(new Vector2(250.0f,250.0f));
        step.setPosition(new Vector2(walk1.getSize().x/2+40.0f,GameActivity.getBaseHei() / 2 - size.y / 0.17f));


        int bossstep;
        MAX=10;
        bossstep=MAX-StepCount.getAll();
        if (bossstep<=0)
        {
            bossstep=0;
        }
        step1 = new Figure(bossstep,1);
        step1.setSize(new Vector2(250.0f,250.0f));
        step1.setPosition(new Vector2(walk1.getSize().x/2+40.0f,GameActivity.getBaseHei() / 2 - size.y / 0.086f));

    }

    @Override
    public  void draw()
    {
        //戻るボタン
        back.draw();
        //総歩数
        word.draw();
        //ボスまで
        bossword.draw();
        //あと
        word_agter.draw();
        //歩1
        walk1.draw();
        //歩2
        walk2.draw();
        //歩数
        step.draw();

        step1.draw();
    }

    @Override
    public void update()
    {
        super.update();
        step.setValue(StepCount.getAll());
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
            //ゲーム画面遷移
            if(touchPos.x < back.getPosition().x + back.getSize().x / 2 && touchPos.x > back.getPosition().x - back.getSize().x / 2 &&
                    touchPos.y < back.getPosition().y + back.getSize().y / 2 && touchPos.y > back.getPosition().y - back.getSize().y / 2)
            {
                BaseScene.setnextScene(new ProgressScene());
            }
        }

    }
}
