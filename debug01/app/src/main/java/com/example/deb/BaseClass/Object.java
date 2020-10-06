package com.example.deb.BaseClass;

import android.view.MotionEvent;

import com.example.deb.System.TextureInfo;

import javax.microedition.khronos.opengles.GL10;

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
    //javaにはVector2が用意されていないので自分で用意する
    protected class Vector2
    {
        float x;
        float y;

        //初期化式
        public Vector2()
        {
            x = 0.0f;
            y = 0.0f;
        }
        //代入式
        public Vector2(float X,float Y)
        {
            x = X;
            y = Y;
        }
    }

    protected int animCnt;      //アニメーションの状態遷移を管理
    protected int interval;     //アニメーションの休憩時間
    protected int pattern;      //アニメーションが何パターンあるかを取得

    //Drawメソッドを使う際にこれ一つで全ての引数になる便利class
    //初期化は忘れずに
    protected class TexVal
    {
        GL10 gl10;
        int texId;

        //座標 左上を目安
        float x;
        float y;

        //大きさ
        float width;
        float height;
        //回転量
        float rotate;
        //反転しているかどうか
        boolean reverse;

        //テクスチャのどこを使うか(？)4角すべての情報が欲しい
        float u;
        float v;
        float w;
        float h;

        //色
        float r;
        float g;
        float b;
        float a;
        protected TexVal
                (
                        GL10 gl, int texId,
                        float x, float y,
                        float w, float h,
                        float rotate, boolean reverce,
                        float u, float v, float texW, float texH,
                        float r, float g, float b, float a
                )
        {


        }
    }

    protected TextureInfo texInfo;      //テクスチャ情報

    protected Vector2 position;         //ポジション

//public:
    //純粋仮想関数もどき 基本継承して自分のしたいことすればいいと思う
    public void init() {}
    public void uninit() {texInfo = null;}
    public void update() {}
    public void draw() {}

    //スマホっぽい関数 これも継承して使う
    public void touch(MotionEvent event){}
    public void button(int id, MotionEvent event){}

    //ゲッターとセッター
    public Vector2 getPosition()           {return position;}
    public void setPosition(Vector2 pos)   {position = pos;}
}
