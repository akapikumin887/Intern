package com.example.deb.System;

//javaにはVector2が用意されていないので自分で用意する
public class Vector2
{
    public float x;
    public float y;

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
