package com.example.deb.Scene;


import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.BaseScene;
import com.example.deb.Object.Figure;
import com.example.deb.System.FPSManager;
import com.example.deb.System.StepCount;
import com.example.deb.System.Vector2;
import com.example.deb.BG.BGTitle;
import com.example.deb.Title.UITitle;
import com.example.deb.UI.HeroUI;

public class HomeScene extends BaseScene
{
    private BGTitle bgTitle;
    private UITitle uiTitle;
    private HeroUI heroTitle;
    private Figure fps;
    private Figure step;


    public HomeScene()
    {
//インスタンス初期化
        //背景
        bgTitle = new BGTitle();
        list.add(bgTitle);

        //UI
        uiTitle = new UITitle();
        list.add(uiTitle);

        //勇者
        heroTitle = new HeroUI();
        list.add(heroTitle);

        //fps ここからはそのうち消す
        fps = new Figure(0,1);
        list.add(fps);
        fps.setSize(new Vector2(100.0f,100.0f));
        fps.setPosition(new Vector2(GameActivity.getBaseWid() / 2 - 50.0f,GameActivity.getBaseHei() / 2 - 50.0f));


        //歩数 ここからはそのうち消す
        step = new Figure(StepCount.getAll(),1);
        list.add(step);
        step.setSize(new Vector2(75.0f,75.0f));
        step.setPosition(new Vector2(GameActivity.getBaseWid() / 2 - 50.0f,0.0f));
    }

    @Override
    public void uninit()
    {
        //データを保存する
/*
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("int",touchCount.getValue());
        editor.apply();
*/

        super.uninit();
    }

    @Override
    public void update()
    {
        super.update();
        step.setValue(StepCount.getAll());

        fps.setValue((int)FPSManager.getFPS());
    }
}
