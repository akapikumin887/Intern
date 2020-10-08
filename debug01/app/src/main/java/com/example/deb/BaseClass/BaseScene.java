package com.example.deb.BaseClass;

import android.view.MotionEvent;

import com.example.deb.Activity.GameActivity;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

public class BaseScene
{
//定数定義
    private final int LAYER_MAX = 4;

//protected
    //リスト ここでシーン内のオブジェクトを管理する
    protected List<Object> list = new ArrayList<Object>();

    //コンストラクタ
    public void BaseScene()
    {

    }
    //純粋及び仮想関数
    public void init(){}
    public void uninit(){}

    public void update()
    {
        //拡張型for文 list内の全要素を参照できる
        for(Object o : list)
            o.update(0.5f);
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
}
