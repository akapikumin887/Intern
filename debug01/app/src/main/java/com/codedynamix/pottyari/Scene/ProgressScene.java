package com.codedynamix.pottyari.Scene;

import android.content.Context;
import android.content.SharedPreferences;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.BG.BGProgress;
import com.codedynamix.pottyari.BaseClass.BaseScene;
import com.codedynamix.pottyari.Object.EnemyStatus;
import com.codedynamix.pottyari.Object.HeroStatus;
import com.codedynamix.pottyari.Progress.UIProgress;
import com.codedynamix.pottyari.System.NewEnter;
import com.codedynamix.pottyari.System.StepCount;

import java.util.Random;

public class ProgressScene extends BaseScene
{
    //ここで歩数分だけ乱数回して敵と遭遇する

    private BGProgress bgProgress;
    private UIProgress uiProgress;

    private static SharedPreferences nextEncountPrefs;  //次の遭遇までの歩数
    private static SharedPreferences stepPrefs;         //歩いた歩数
    private static SharedPreferences bossPrefs;         //次戦う敵がボスかどうか
    private static SharedPreferences maxPrefs;          //ボスまでの距離
    private static SharedPreferences enemyTypePrefs;    //今戦っている敵の種類

    private static boolean canBattle;  //今回バトルできるか
    private static boolean isBoss;     //次に戦う敵はボスなのか

    private int step;       //今回歩いた歩数
    private int count;      //updateカウンタ

    private static int road;       //次の敵までの距離
    private static int startStep;

    private static int max;
    private static int bossStep;

    private static boolean isBattle;

    //今戦っている敵の種類
    private static int enemyType;

//マクロ定義
    private final int LOAD_MAX = 100000;              //初期値の道の長さ
    private final int ENEMY_ENCOUNT_MAX = 10000;
    private final int ENEMY_ENCOUNT_MIN = 1000;

    public ProgressScene()
    {
        canBattle = false;

        //現在主人公が戦っているか
        if(HeroStatus.getIsBattle())
            isBattle = true;
        else
            isBattle = false;

        step = StepCount.getTtStep();   //今回歩いた歩数

        nextEncountPrefs = GameActivity.getActivity().getSharedPreferences("encount", Context.MODE_PRIVATE);
        road = nextEncountPrefs.getInt("int",0);        //次の敵までの距離

        stepPrefs = GameActivity.getActivity().getSharedPreferences("step", Context.MODE_PRIVATE);
        startStep = stepPrefs.getInt("int",0);   //ゲーム内で歩いた歩数

        bossPrefs = GameActivity.getActivity().getSharedPreferences("boss", Context.MODE_PRIVATE);
        isBoss = bossPrefs.getBoolean("boolean",false);   //次に戦う敵はボスかどうか

        maxPrefs = GameActivity.getActivity().getSharedPreferences("max", Context.MODE_PRIVATE);
        max = maxPrefs.getInt("int",LOAD_MAX);           //初期値からボスまでの距離

        enemyTypePrefs = GameActivity.getActivity().getSharedPreferences("enemyType", Context.MODE_PRIVATE);
        enemyType = enemyTypePrefs.getInt("int",0);      //今戦っている敵の種類

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
                //
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

        save();
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
        //説明するまでエンカウントは待ってもらう
        if(!NewEnter.getInformScene(3))
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
                Random random = new Random();

                int enemyFlavor = 0;
                if(EnemyStatus.getBossDeadCount() > 5)
                {
                    //ボスを5回以上倒していたらイチゴ味解禁
                    enemyFlavor = random.nextInt(3);
                }
                else if(EnemyStatus.getBossDeadCount() > 3)
                {
                    //ボスを3回以上倒していたらチョコ味解禁
                    enemyFlavor = random.nextInt(2);
                }
                //！が出てから60カウント後に戦闘開始
                if(isBoss)
                {
                    switch(enemyFlavor)
                    {
                        case 0:
                            enemyType = 2;
                            break;
                        case 1:
                            enemyType = 5;
                            break;
                        case 2:
                            enemyType = 8;
                            break;
                    }

                    BaseScene.setnextScene(new BattleScene(enemyType));
                    isBoss = true;

                    SharedPreferences.Editor editor = bossPrefs.edit();
                    editor.putBoolean("boolean", isBoss);
                    editor.apply();

                    editor = enemyTypePrefs.edit();
                    editor.putInt("int",enemyType);
                    editor.apply();
                }
                else
                {
                    switch(enemyFlavor)
                    {
                        case 0:
                            enemyType = random.nextInt(2);
                            break;
                        case 1:
                            enemyType = random.nextInt(2) + 3;
                            break;
                        case 2:
                            enemyType = random.nextInt(2) + 6;
                            break;
                    }

                    BaseScene.setnextScene(new BattleScene(enemyType));

                    SharedPreferences.Editor editor = enemyTypePrefs.edit();
                    editor.putInt("int",enemyType);
                    editor.apply();
                }
            }
        }
    }

    //次の敵の座標を決める
    public void setNextEnemyLocation()
    {
        Random random = new Random();
        //最小値1000 : 最大値10000の歩数歩くと敵と遭遇する
        int max = ENEMY_ENCOUNT_MAX;
        int min = ENEMY_ENCOUNT_MIN;
        road = min + random.nextInt(max - min);

        //エンカウントの敵よりボスのほうが近ければ次に戦う敵はボスになる
        if (road > bossStep)
        {
            road = bossStep;
            isBoss = true;
        }
    }

    public static void save()
    {
        SharedPreferences.Editor editor;

        editor = nextEncountPrefs.edit();
        editor.putInt("int",road);
        editor.apply();

        editor = stepPrefs.edit();
        editor.putInt("int",startStep);
        editor.apply();

        editor = bossPrefs.edit();
        editor.putBoolean("boolean", isBoss);
        editor.apply();

        editor = maxPrefs.edit();
        editor.putInt("int",max);
        editor.apply();
    }

    @Override
    public void back() {BaseScene.setnextScene(new HomeScene());}

    public static int getBossStep(){return bossStep;}
    public static int getMAX(){return max;}
    public static boolean getIsBattle(){return isBattle;}
    public static boolean getCanBattle(){return canBattle;}

    public static int getEnemyType(){return enemyType;}
    public static boolean getIsBoss(){return isBoss;}
    public static void setStartStep(int num){startStep = num;}

}
