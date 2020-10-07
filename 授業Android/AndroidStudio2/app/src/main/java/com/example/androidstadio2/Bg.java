package com.example.androidstadio2;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.view.MotionEvent;

public class Bg extends Sprite
{
    @Override
    // 更新処理(Spriteクラスの処理をオーバーライド)
    public void Update( float dt )
    {
        super.Update(dt);

        // 下にスクロール
        y -= 2 * GameView.BASE_FPS * dt;

        // 画面外に出たら2枚分上に移動
        if(y < -(GameView.BASE_HEI / 2 + hei / 2))
        {
            y += hei * 2;
        }
    }
}
