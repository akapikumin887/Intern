package com.example.deb.BaseClass;

import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BaseScene
{
//peivate
    private static BaseScene nowScene  = null;
    private static BaseScene nextScene = null;

//定数定義
    private final int LAYER_MAX = 4;

//protected
    //リスト ここでシーン内のオブジェクトを管理する
    protected List<Object> list = new CopyOnWriteArrayList<Object>();

    //コンストラクタ
    public BaseScene() {}
    //純粋及び仮想関数
    public void init(){}
    public void uninit()
    {
        //拡張型for文 list内の全要素を参照できる
        for(Object o : list)
            o.uninit();
        list.clear();
    }

    public void update()
    {
        //拡張型for文 list内の全要素を参照できる
        for(Object o : list)
            o.update();
    }

    public void draw()
    {
        //2Dでもレイヤーに合わせて描画順番を変更
        for(int i = 0; i < LAYER_MAX; i++)
        {
            //拡張型for文 list内の全要素を参照できる
            for(Object o : list)
            {
                if(i == o.getLayer())
                    o.draw();
            }
        }
    }

    //画面を触ったら勝手に処理してくれる update等に入れる必要なし
    public void touch(MotionEvent event)
    {
        //拡張型for文 list内の全要素を参照できる
        for(Object o : list)
            o.touch(event);
    }

    public void back()
    {
        for(Object o : list)
            o.onBackkPlessed();
    }

    //ゲッターとセッター
    public static void setScene(BaseScene scene)
    {
        if(nowScene == scene)
            return;

        if(nowScene != null)
            nowScene.uninit();

        if(nextScene == null)
            nowScene = scene;
        else
        {
            nowScene = nextScene;
            nextScene = null;
        }
    }

    public static BaseScene getScene(){return nowScene;}

    public static BaseScene getnextScene(){return nextScene;}
    public static void setnextScene(BaseScene scene){nextScene = scene;}

}
