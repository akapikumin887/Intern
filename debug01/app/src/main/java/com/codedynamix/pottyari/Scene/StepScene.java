package com.codedynamix.pottyari.Scene;

import com.codedynamix.pottyari.BG.BGStShGp;
import com.codedynamix.pottyari.BaseClass.BaseScene;
import com.codedynamix.pottyari.Step.UIStep;

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
