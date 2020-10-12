package com.example.deb.Object;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;

public class Figure extends Object
{
    //マクロ定義
    private final float TEXTURE_WIDTH  = 5115.0f;

    //private:
    private static Texture texture;

    private int digit;      //桁数
    private int value;      //値

    public Figure(int num)
    {
        super();
        value = num;
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
            texture.draw(new Vector2(x,pos.y),size,
                    rotate,reverse,
/*
                    new Vector2((v % 10) * 0.1f,0.0f),
                    new Vector2((v % 10) * 0.1f + 0.1f,1.0f),
*/
                    //参照する場所がおかしい候補
                    new Vector2(0.1f,0.0f),
                    new Vector2(1.0f,1.0f),
                    color);

            // 表示桁・座標更新
            v /= 10;
            x -= size.x;
        }
        while(++cnt < digit || v != 0);
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
