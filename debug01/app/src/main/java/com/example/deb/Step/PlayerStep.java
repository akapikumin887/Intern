package com.example.deb.Step;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.Progress.UIProgress;
import com.example.deb.System.StepCount;
import com.example.deb.UI.GameWay;
import com.example.deb.UI.Game_stepword;

public class PlayerStep extends Object
{
    private  UIStep uistep;
    private UIProgress uiProgress;
    private static float kyorihosuu;
    private static float kyori;

    public  PlayerStep()
    {
        uistep = new UIStep();
    if (UIProgress.getbossstep()==0)
    {
        kyori=40.0f;
    }
    else
    {
        kyori = (float)UIProgress.getMAX() / (float)UIProgress.getbossstep();
        kyori = (GameActivity.getBaseWid() - 80.0f) / kyori;
    }

    }

    @Override
    public void update()
    {

    }

    public static float getkyorihosuu(){return kyorihosuu;}
    public static float getkyori(){return kyori;}
}
