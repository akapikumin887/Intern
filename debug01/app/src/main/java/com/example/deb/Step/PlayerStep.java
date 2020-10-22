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

        kyorihosuu=GameActivity.getBaseWid() / 2/UIProgress.getbossstep();

        kyorihosuu=kyorihosuu*100;
        kyorihosuu = (float)Math.floor(kyorihosuu);
        kyorihosuu=kyorihosuu/100;

            kyori = (float) kyorihosuu / 100;
            kyori= (float) (kyori/1.69);
    }

    @Override
    public void update()
    {

    }

    public static float getkyorihosuu(){return kyorihosuu;}
    public static float getkyori(){return kyori;}
}
