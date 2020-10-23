package com.example.deb.Scene;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BG.BGProgress;
import com.example.deb.BaseClass.BaseScene;
import com.example.deb.Object.EnemyStatus;
import com.example.deb.Progress.UIProgress;
import com.example.deb.System.StepCount;

import java.util.Random;

public class ProgressScene extends BaseScene
{
    //ここで歩数分だけ乱数回して敵と遭遇する

    private BGProgress bgProgress;
    private UIProgress uiProgress;

    private SharedPreferences nextEncountPrefs;  //次の遭遇までの歩数

    private int step;       //今回歩いた歩数

    private Random random;      //乱数

    public ProgressScene()
    {
        random = new Random();

        bgProgress = new BGProgress();
        list.add(bgProgress);

        uiProgress = new UIProgress();
        list.add(uiProgress);

        step = StepCount.getTtStep();

        nextEncountPrefs = GameActivity.getActivity().getSharedPreferences("encount", Context.MODE_PRIVATE);
        int road = nextEncountPrefs.getInt("int",0);

        //次の敵までの距離が決まってなかったら設定する
        if(road == 0)
        {
            //歩数は確実に0になるのでエンカウントの最大値と最小値を出しておき、最小値以上なら遭遇までに何歩歩いたかを求めればよい
            //次の敵までの距離を出しておき、そこに接触したら戦闘になる。
            //最小値1000 : 最大値10000で今回は計算してみる

            int min = 1000;
            int max = 10000;
            road = min + random.nextInt(max - min);
        }
        road -= step;
        if(road < 0)
        {
            //今回で戦闘が始まる
            road = 0;
            EnemyStatus enemy = new EnemyStatus();
        }
        //次の敵までの値を忘れずに保存しておく
        SharedPreferences.Editor editor = nextEncountPrefs.edit();
        editor.putInt("int",road);
        editor.apply();
    }

    @Override
    public void uninit()
    {

        StepCount.resetTtStep();        //今回歩いた歩数リセット
        super.uninit();
    }

    @Override
    public void update()
    {
        //戦闘に入るまで少しくらいタイムラグが欲しい
        //10000歩でも10秒くらいで、1000歩でも5秒くらいほしい

        super.update();
    }

    @Override
    public void back()
    {
        BaseScene.setnextScene(new HomeScene());
    }
}
