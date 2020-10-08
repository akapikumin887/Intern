package com.example.deb.System;

//カラー型
public class Color
{
    public float r;
    public float g;
    public float b;
    public float a;

    //初期化式
    public Color()
    {
        r = 1.0f;
        g = 1.0f;
        b = 1.0f;
        a = 1.0f;
    }

    //代入式
    public Color(float R,float G,float B, float A)
    {
        r = R;
        g = G;
        b = B;
        a = A;
    }
}
