package com.example.deb.Scene;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BG.BGStShGp;
import com.example.deb.BaseClass.BaseScene;
import com.example.deb.Status.UIStatus;
import com.example.deb.Step.UIStep;
import com.example.deb.System.Vector2;
import com.example.deb.UI.HeroUI;

public class StepScene extends BaseScene
{
    private BGStShGp bgStep;
    private UIStep uiStep;

    public StepScene()
    {
        //背景
        bgStep = new BGStShGp();
        list.add(bgStep);

        //UI
        uiStep = new UIStep();
        list.add(uiStep);



    }



    @Override
    public void back()
    {
        BaseScene.setnextScene(new ProgressScene());
    }
}
