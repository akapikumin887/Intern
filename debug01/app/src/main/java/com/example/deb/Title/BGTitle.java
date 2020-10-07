package com.example.deb.Title;

import com.example.deb.BaseClass.Object;
import com.example.deb.System.TextureDrawer;
import com.example.deb.System.TextureInfo;

import javax.microedition.khronos.opengles.GL10;

public class BGTitle extends Object
{
    public BGTitle(TextureInfo info)
    {
        //マルチスレッドでぶん投げ隊
        super();
        layer = Layer.LAYER_BG;
        texInfo = info;
        size = new Vector2(1.0f,1.0f);
    }

    @Override
    public void draw(GL10 gl)
    {
        //例外処理
        if(texInfo == null) return;

        //描画 ここ書けば描画できそう
        TextureDrawer.drawTexture
                (   gl,
                        texInfo.texId,
                        pos.x, pos.y, 768.0f, 1600.0f,
                        rotate, reverse,
                        0.0f,
                        0.0f ,
                        1.0f, 1.0f,
                        color.a, color.g, color.b, color.a
                );
    }

    @Override
    public void update(float dt)
    {
        super.update(dt);

    }

}
