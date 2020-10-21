package com.example.deb.Scene;

import com.example.deb.BG.BGProgress;
import com.example.deb.BaseClass.BaseScene;
import com.example.deb.Progress.UIProgress;
import com.example.deb.System.StepCount;

import java.util.Random;

public class ProgressScene extends BaseScene
{
    //ここで歩数分だけ乱数回して敵と遭遇する

    private BGProgress bgProgress;
    private UIProgress uiProgress;

    private int step;

    private Random random;      //乱数
    private float encounter;
    private int count;
    private int stepCount;

    public ProgressScene()
    {
        random = new Random();

        bgProgress = new BGProgress();
        list.add(bgProgress);

        uiProgress = new UIProgress();
        list.add(uiProgress);

        step = StepCount.getTtStep();
        encounter = 0.0f;
        count = 0;
        stepCount = 0;
    }

    @Override
    public void uninit()
    {

        StepCount.resetTtStep();
        super.uninit();
    }

    @Override
    public void update()
    {
        count++;

        //20fに1度歩数計を進める
        if(count % 20 == 0)
        {
            if(step > 0)
            {
                step--;
                stepCount++;
                //20歩で1度エンカウント確率を上げていく
                if(stepCount % 20 == 0)
                    encounter += (float)random.nextInt(5) * 0.01f;     //200歩で大体1/3くらいになる

                //バトル発生確率
                float b = encounter * count / 200;

                if(b * (float)random.nextInt(100) > 15.0f)
                {
                    //バトルするぞ
                    BaseScene.setnextScene(new BattleScene());
                    step = 0;
                }
            }
        }

        super.update();
    }

    @Override
    public void back()
    {
        BaseScene.setnextScene(new HomeScene());
    }
}
