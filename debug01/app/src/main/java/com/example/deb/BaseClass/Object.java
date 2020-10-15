package com.example.deb.BaseClass;

import android.view.MotionEvent;

import com.example.deb.System.Vector2;
import com.example.deb.System.Color;

/*
* javaの命名規則について(C++との違い)
* メソッド(関数)の頭文字も小文字を扱う
* 例:getPosition
* ここだけ気を付けて良きjavaLifeを!
* */


//ゲームオブジェクト
//継承用
public class Object
{
//protected:
    //レイヤーはオブジェクト側で管理する
    protected enum Layer
    {
        LAYER_BG(0),
        LAYER_CHARE(1),
        LAYER_BUTTON(2),
        LAYER_MESSAGE(3),
        LAYER_MAX(4);

        private final int id;

        private Layer(int num) {this.id = num; }

        public int getId(){return this.id;}
    }

    //draw関数で使う変数群
    protected Vector2       pos;        //描画座標 左上基準
    protected Vector2       size;       //描画サイズ

    protected float         rotate;     //回転 ゲームによってはあまり使わない
    protected boolean       reverse;    //反転 同上

    protected float         animCnt;    //アニメーションの状態遷移を管理
    protected float         interval;   //アニメーションの休憩時間
    protected int           pattern;    //アニメーションが何パターンあるか

    protected Vector2       texRange;   //使う画像の範囲指定

    protected Color         color;      //色 all1.0fで描画される


    protected Layer layer;
//public:

    //コンストラクタ
    public Object()
    {
        pos = new Vector2();
        size = new Vector2(100.0f,100.0f);

        rotate = 0.0f;
        reverse = false;

        animCnt = 0.0f;
        interval = 0;
        pattern = 1;

        texRange = new Vector2(1.0f,1.0f);

        color = new Color();
    }

    //純粋仮想関数ぽいものを実装
    public void init() {}
    public void uninit() {}

    public void update(){}
    public void draw() {}

    //スマホっぽい関数 これも継承して使う
    public void touch(MotionEvent event){}
    public void button(int id, MotionEvent event){}

    //ゲッターとセッター
    public Vector2 getPosition()                {return pos;}
    public void setPosition(Vector2 num)        {pos = num;}

    public Vector2 getSize()                    {return size;}
    public void setSize(Vector2 num)            {size = num;}

    public Vector2 getTexRange()                {return texRange;}
    public void setTexRange(Vector2 num)        {texRange = num;}

    public int getLayer()                       {return layer.id;}
    public void setLayer(Layer num)             {layer = num;}
}
