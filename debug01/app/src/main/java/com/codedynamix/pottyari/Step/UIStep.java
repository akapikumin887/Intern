package com.codedynamix.pottyari.Step;

import android.view.MotionEvent;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.BaseClass.BaseScene;
import com.codedynamix.pottyari.BaseClass.Object;
import com.codedynamix.pottyari.Object.Figure;
import com.codedynamix.pottyari.Progress.UIProgress;
import com.codedynamix.pottyari.Scene.ProgressScene;
import com.codedynamix.pottyari.System.StepCount;
import com.codedynamix.pottyari.System.Vector2;
import com.codedynamix.pottyari.UI.ChoiseBack;
import com.codedynamix.pottyari.UI.Game_stepword;

public class UIStep extends Object
{
    private ChoiseBack back;
    private Game_stepword word;
    private Game_stepword bossWord;
    private Game_stepword word_agter;
    private Game_stepword walk1;
    private Game_stepword walk2;
    private Figure step;
    private Figure step1;


    public  UIStep()
    {
        super();
        setLayer(Layer.LAYER_BUTTON);

        //戻る
        back = new ChoiseBack(2);

        //総歩数
        word = new Game_stepword(0);
        //ボスまで
        bossWord = new Game_stepword(1);
        //あと
        word_agter = new Game_stepword(2);
        //歩1
        walk1 = new Game_stepword(3);

        walk2 = new Game_stepword(4);


        //歩数
        step = new Figure(StepCount.getAll(),1);
        step.setSize(new Vector2(150.0f,150.0f));
        step.setPosition(new Vector2(walk1.getSize().x/2+80.0f,GameActivity.getBaseHei() / 2 - size.y / 0.165f));

        //ボスまでの歩数
        step1 = new Figure(UIProgress.getBossStep(),1);
        step1.setSize(new Vector2(150.0f,150.0f));
        step1.setPosition(new Vector2(walk1.getSize().x/2+80.0f,GameActivity.getBaseHei() / 2 - size.y / 0.0865f));

    }

    @Override
    public  void draw()
    {
        //戻るボタン
        back.draw();
        //総歩数
        word.draw();
        //ボスまで
        bossWord.draw();
        //あと
        //word_agter.draw();
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
