package com.example.deb.Scene;

import com.example.deb.BG.BGProgress;
import com.example.deb.BaseClass.BaseScene;
import com.example.deb.Progress.UIProgress;

public class ProgressScene extends BaseScene
{
    //ここで歩数分だけ乱数回して敵と遭遇する

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
