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
    private SharedPreferences stepPrefs;         //歩いた歩数
    private SharedPreferences bossPrefs;         //次戦う敵がボスかどうか
    private SharedPreferences maxPrefs;          //ボスまでの距離

    private static boolean canBattle;  //今回バトルできるか
    private static boolean isBoss;     //次に戦う敵はボスなのか

    private int step;       //今回歩いた歩数
    private int count;      //updateカウンタ
    private int road;       //次の敵までの距離

    private static int max;
    private static int bossStep;

    private static boolean isBattle;

    private static int enemyType;

//マクロ定義
    private final int ENEMY_TYPE = 2;

    public ProgressScene()
    {
        canBattle = false;

        step = StepCount.getTtStep();   //今回歩いた歩数

        nextEncountPrefs = GameActivity.getActivity().getSharedPreferences("encount", Context.MODE_PRIVATE);
        road = nextEncountPrefs.getInt("int",0);        //次の敵までの距離

        stepPrefs = GameActivity.getActivity().getSharedPreferences("step", Context.MODE_PRIVATE);
        int startStep = stepPrefs.getInt("int",0);   //ゲーム内で歩いた歩数

        bossPrefs = GameActivity.getActivity().getSharedPreferences("boss", Context.MODE_PRIVATE);
        isBoss = bossPrefs.getBoolean("boolean",false);   //次に戦う敵はボスかどうか

        maxPrefs = GameActivity.getActivity().getSharedPreferences("max", Context.MODE_PRIVATE);
        max = maxPrefs.getInt("int",5);           //初期値からボスまでの距離

        {
            //ボスまでの歩数を最大値から歩いた分だけ減らして求める
            bossStep = max - startStep;


            //次の敵までの距離を決める
            if(road == 0)   setNextEnemyLocation();


            //バトル中でなければプレイヤーは進む
            if (!HeroStatus.getIsBattle())
            {
                startStep += step;          //近くの敵と歩いた歩数で小さいほうが加算される
                road -= step;               //敵と戦っていない間なので歩数も進める
                bossStep -= step;           //今回進んでいた分を引いておく

                //敵と遭遇していたら差分を戻しておく
                if(road <= 0)
                {
                    bossStep -= road;
                    startStep += road;
                }
            }

            //ボスを通り過ぎてしまった場合は立ち止まってもらう
            if (bossStep <= 0) bossStep = 0;

            //敵とエンカウント
            if(road <= 0)
            {
                if(!isBattle)
                {
                    //遭遇した敵がボスだったら次の準備をしておく
                    if(bossStep == 0)
                    {
                        isBoss = true;
                        max *= 1.1f;
                        //オーバーフロー対策
                        if(max < 0)
                            max = 1 << 15;
                    }
                    else
                    {
                        isBoss = false;
                    }

                    //今回で戦闘が始まる
                    canBattle = true;
                    road = 0;   //歩数をリセットして次の敵に備える
                    EnemyStatus.setEnemy();
                }
            }
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

        //ボスかどうかを保存
        editor = bossPrefs.edit();
        editor.putBoolean("boolean", isBoss);
        editor.apply();

        //距離の最大値
        editor = maxPrefs.edit();
        editor.putInt("int",max);
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
                isBoss = false;

                bossPrefs = GameActivity.getActivity().getSharedPreferences("boss", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = bossPrefs.edit();
                editor.putBoolean("boolean", isBoss);
                editor.apply();
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

    //次の敵の座標を決める
    public void setNextEnemyLocation()
    {
        Random random = new Random();
        //最小値1000 : 最大値10000の歩数歩くと敵と遭遇する
        int max = 3;
        int min = 1;
        road = min + random.nextInt(max - min);

        //エンカウントの敵よりボスのほうが近ければ次に戦う敵はボスになる
        if (road > bossStep)
        {
            road = bossStep;
            isBoss = true;
        }
    }

    @Override
    public void back() {BaseScene.setnextScene(new HomeScene());}

    public static int getBossStep(){return bossStep;}
    public static int getMAX(){return max;}
    public static boolean getIsBattle(){return isBattle;}
    public static boolean getCanBattle(){return canBattle;}

    public static int getEnemyType(){return enemyType;}
    public static boolean getIsBoss(){return isBoss;}


}
