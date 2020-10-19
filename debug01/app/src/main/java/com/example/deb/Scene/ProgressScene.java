package com.example.deb.Scene;

import com.example.deb.BG.BGProgress;
import com.example.deb.BaseClass.BaseScene;
import com.example.deb.Progress.ProgressHero;
import com.example.deb.Progress.UIProgress;
import com.example.deb.UI.ChoiseBack;

public class ProgressScene extends BaseScene
{
    private BGProgress bgProgress;
    private UIProgress uiProgress;

    public ProgressScene()
    {
        bgProgress = new BGProgress();
        list.add(bgProgress);

        uiProgress = new UIProgress();
        list.add(uiProgress);
    }

    @Override
    public void uninit()
    {

        super.uninit();
    }

    @Override
    public void update()
    {

        super.update();
    }

    @Override
    public void back()
    {
        BaseScene.setnextScene(new HomeScene());
    }
}
