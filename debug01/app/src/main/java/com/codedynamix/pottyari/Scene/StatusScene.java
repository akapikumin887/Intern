package com.codedynamix.pottyari.Scene;

import android.view.MotionEvent;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.BG.BGStShGp;
import com.codedynamix.pottyari.BaseClass.BaseScene;
import com.codedynamix.pottyari.Status.UIStatus;
import com.codedynamix.pottyari.System.Vector2;
import com.codedynamix.pottyari.UI.HeroUI;

public class StatusScene extends BaseScene
{
    private BGStShGp bgStatus;
    private UIStatus uiStatus;
    private HeroUI heroStatus;

    //中断して戻ってもhomeに行かないようにしたい
    public StatusScene()
    {
        init();
    }

    @Override
    public void init()
    {
        //背景
        bgStatus = new BGStShGp();
        list.add(bgStatus);

        //UI
        uiStatus = new UIStatus();
        list.add(uiStatus);

        //勇者
        heroStatus = new HeroUI();
        list.add(heroStatus);
        heroStatus.setPosition(new Vector2(-GameActivity.getBaseWid() / 4,0.0f));
        heroStatus.setSize(new Vector2(600.0f,600.0f));
    }

    @Override
    public void uninit()
    {
        super.uninit();
    }

    @Override
    public  void touch(MotionEvent event)
    {
        super.touch(event);

    }

    @Override
    public void back()
    {
        BaseScene.setnextScene(new HomeScene());
    }
}
