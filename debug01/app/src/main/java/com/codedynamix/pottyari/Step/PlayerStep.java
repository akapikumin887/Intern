package com.codedynamix.pottyari.Step;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.BaseClass.Object;
import com.codedynamix.pottyari.Progress.UIProgress;

public class PlayerStep extends Object
{
    private  UIStep uistep;
    private static float kyori;

    public  PlayerStep()
    {
        uistep = new UIStep();
        if (UIProgress.getBossStep() == 0)
        {
            kyori=0.0f;
        }
        else
        {
            kyori = (float)UIProgress.getMAX() / (float)UIProgress.getBossStep();
            kyori = (GameActivity.getBaseWid() - 80.0f) / kyori;
        }

    }

    public static float getkyori(){return kyori;}
}
