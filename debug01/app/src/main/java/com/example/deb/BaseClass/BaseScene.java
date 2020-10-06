package com.example.deb.BaseClass;

import javax.microedition.khronos.opengles.GL10;

public class BaseScene
{
    //描画の際に必要
    protected GL10 gl10;

    //コンストラクタ
    public void BaseScene()
    {

    }

    //純粋仮想関数のつもり
    public void update() {}
    public void draw() {}
}
