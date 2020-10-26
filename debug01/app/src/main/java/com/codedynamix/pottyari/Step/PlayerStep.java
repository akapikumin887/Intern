package com.codedynamix.pottyari.Step;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.BaseClass.Object;
import com.codedynamix.pottyari.Scene.ProgressScene;

public class PlayerStep extends Object
{
    private  UIStep uistep;
    private static float kyori;

    public  PlayerStep()
    {
        uistep = new UIStep();
        if (ProgressScene.getBossStep() == 0)
        {
            kyori=0.0f;
        }
        else
        {
            kyori = (float)ProgressScene.getMAX() / (float)ProgressScene.getBossStep();
            kyori = (GameActivity.getBaseWid() - 80.0f) / kyori;
        }

    }

    public static float getkyori(){return kyori;}
}
