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
    private int count;      //updateカウンタ

    private  static int MAX = 20;
    private static int bossStep;

    private Random random;      //乱数
    private static boolean isBattle;

    private static int enemyType;

//マクロ定義
    private final int ENEMY_TYPE = 2;

    public ProgressScene()
    {
        random = new Random();

        step = StepCount.getTtStep();   //今回歩いた歩数
        isBoss = false;

        nextEncountPrefs = GameActivity.getActivity().getSharedPreferences("encount", Context.MODE_PRIVATE);
        int road = nextEncountPrefs.getInt("int",0);        //次の敵までの距離

        stepPrefs = GameActivity.getActivity().getSharedPreferences("step", Context.MODE_PRIVATE);
        int startStep = stepPrefs.getInt("int",0);   //スタート地点から何歩進んだか


        //バトル中でなければプレイヤーは進む
        if(!HeroStatus.getIsBattle())
        {
            //近くの敵と歩いた歩数で小さいほうが加算される
            startStep += Math.min(road, step);
            road -= step;               //敵と戦っていない間なので歩数も進める
        }

        //ボスまでの歩数を最大値から歩いた分だけ減らして求める
        bossStep = MAX - startStep;

        //ボスを通り過ぎてしまった場合は立ち止まってもらう
        if (bossStep <= 0) bossStep = 0;


        //次の敵までの距離が決まってなかったら設定する
        if(road == 0)
        {
            //最小値1000 : 最大値10000の歩数歩くと敵と遭遇する

            int max = 5;
            int min = 1;
            road = min + random.nextInt(max - min);

            //エンカウントの敵よりボスのほうが近ければ次に戦う敵はボスになる
            if(bossStep < road)
            {
                road = bossStep;
                isBoss = true;
            }
        }

        //敵とエンカウント
        if(road < 0)
        {
            if(bossStep == 0)
                isBoss = true;

            //今回で戦闘が始まる
            canBattle = true;
            road = 0;   //歩数をリセットして次の敵に備える
            EnemyStatus.setEnemy();
        }

        //背景とUIのコンストラクタ 上で計算した値を使用するのでこのタイミング
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

        //step側も上書き保存
        StepCount.save();

        //初期化
        count = 0;
        isBattle = false;
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

        //！が出るまでの間
        if(count > 120)
        {
            //バトル開始
            if(canBattle)
            {
                isBattle = true;
                count = 0;
            }
        }

        if(!isBattle)
            super.update();
        else if(count > 60)
        {
            //！が出てから60カウント後に戦闘開始
            if(isBoss)
            {
				enemyType = 2;
                BaseScene.setnextScene(new BattleScene(2));
                HeroStatus.setIsBattle(true);
            }
            else
            {
				Random random = new Random();
				enemyType = random.nextInt(ENEMY_TYPE - 1);
                BaseScene.setnextScene(new BattleScene(enemyType));
                HeroStatus.setIsBattle(true);
            }
        }
    }


    @Override
    public void back() {BaseScene.setnextScene(new HomeScene());}

    public static int getBossStep(){return bossStep;}
    public static int getMAX(){return MAX;}
    public static boolean getIsBattle(){return isBattle;}

    public static int getEnemyType(){return enemyType;}

}
