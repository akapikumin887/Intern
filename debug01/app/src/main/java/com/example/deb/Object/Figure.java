package com.example.deb.Object;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;

public class Figure extends Object
{
    //private:
    private static Texture texture;

    private int digit;      //桁数
    private int value;      //値
    private float texColor; //色 0が白で1が黒

    public Figure(int num,int texturecolor)
    {
        super();
        value = num;
        texColor = (float)texturecolor * 0.5f;
        int v = value;
        do
        {
            v /= 10;
            digit++;
        }while(v != 0);
        setLayer(layer.LAYER_MESSAGE);
    }

    //描画
    @Override
    public void draw()
    {
        int v = value;
        int cnt = 0;
        float x = pos.x;

        do
        {
            // 一桁ずつ数字を表示
            //ここでいうサイズは1桁分
            //xyは中心座表
            texture.draw(new Vector2(x,pos.y),size,
                    rotate,reverse,
                    new Vector2((v % 10) * 0.1f,texColor),  //始点、左上
                    new Vector2(0.1f,0.5f),             //幅と高さ
                    color);

            // 表示桁・座標更新
            v /= 10;
            x -= size.x / 2;
        }while(++cnt < digit || v != 0);
    }

    @Override
    public void update()
    {
        //桁数が増えても対応するやつ 毎秒更新する必要あるかは不明
        digit = 0;
        int v = value;
        do
        {
            v /= 10;
            digit++;
        }while(v != 0);
    }

    //テクスチャロード
    public static void loadTexture()
    {
        texture = new Texture();
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.number);
    }

    //ゲッターとセッター
    public void setValue(int num)   {value = num;}
    public int getValue()           {return value;}
}
