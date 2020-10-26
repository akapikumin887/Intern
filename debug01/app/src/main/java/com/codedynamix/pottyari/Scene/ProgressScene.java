package com.codedynamix.pottyari.Scene;

import android.content.Context;
import android.content.SharedPreferences;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.BG.BGProgress;
import com.codedynamix.pottyari.BaseClass.BaseScene;
import com.codedynamix.pottyari.Object.EnemyStatus;
import com.codedynamix.pottyari.Object.HeroStatus;
import com.codedynamix.pottyari.Progress.UIProgress;
import com.codedynamix.pottyari.System.StepCount;

import java.util.Random;

public class ProgressScene extends BaseScene
{
    //ここで歩数分だけ乱数回して敵と遭遇する

    private BGProgress bgProgress;
    private UIProgress uiProgress;

    private SharedPreferences nextEncountPrefs;  //次の遭遇までの歩数
    private SharedPreferences stepPrefs;         //スタート地点から何歩進んだか
    private boolean canBattle;  //今回バトルできるか
    private boolean isBoss;     //次に戦う敵はボスなのか

    private int step;       //今回歩いた歩数
    private int count;

    private  static int MAX = 100000;
    private static int bossStep;

    private Random random;      //乱数

    public ProgressScene()
    {
        random = new Random();

        step = StepCount.getTtStep();
        isBoss = false;

        nextEncountPrefs = GameActivity.getActivity().getSharedPreferences("encount", Context.MODE_PRIVATE);
        int road = nextEncountPrefs.getInt("int",0);

        stepPrefs = GameActivity.getActivity().getSharedPreferences("step", Context.MODE_PRIVATE);
        int startStep = stepPrefs.getInt("int",0) + step;

        //次の敵までの距離が決まってなかったら設定する
        if(road == 0)
        {
            //エンカウントの最大値と最小値を出しておき、最小値以上なら遭遇までに何歩歩いたかを求めればよい
            //次の敵までの距離を出しておき、そこに接触したら戦闘になる。
            //最小値1000 : 最大値10000で今回は計算してみる

            //デバッグで500歩以上1000歩以下で戦闘に入るようにしておく
            int max = 1000;
            int min = 500;
            road = min + random.nextInt(max - min);

            //エンカウントの敵よりボスのほうが近ければ次に戦う敵はボスになる
            if(bossStep < road)
            {
                road = bossStep;
                isBoss = true;
            }
        }

        bossStep = MAX;

        //進めた歩数をbossStepに渡してあげる
        if(!HeroStatus.getIsBattle())
        {
            if(road < step)
                startStep += road;
            else
                startStep += step;
            road -= step;
        }

        bossStep -= startStep;

        if (bossStep <= 0)
            bossStep = 0;

        if(road < 0)
        {
            if(!HeroStatus.getIsBattle())
            {
                if(bossStep == 0)
                    isBoss = true;

                //今回で戦闘が始まる
                canBattle = true;
                road = 0;
                EnemyStatus enemy = new EnemyStatus();
            }
            else
            {
                //既に戦闘が始まっている
                canBattle = false;
            }
        }


        bgProgress = new BGProgress();
        list.add(bgProgress);

        uiProgress = new UIProgress();
        list.add(uiProgress);

        //次の敵までの値を忘れずに保存しておく
        SharedPreferences.Editor editor = nextEncountPrefs.edit();
        editor.putInt("int",road);
        editor.apply();

        //ボスまでの距離も保存
        editor = stepPrefs.edit();
        editor.putInt("int",startStep);
        editor.apply();

        count = 0;
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
        count++;

        if(count > 120)
        {
            //バトル開始
            if(canBattle)
            {
                if(isBoss)
                {
                    BaseScene.setnextScene(new BattleScene(2));
                    HeroStatus.setIsBattle(true);
                }
                else
                {
                    BaseScene.setnextScene(new BattleScene(0));
                    HeroStatus.setIsBattle(true);
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

    public static int getBossStep(){return bossStep;}
    public static int getMAX(){return MAX;}

}
