package com.example.androidstadio2;

// 敵クラスA
public class EnemyA extends Enemy
{
    @Override
    public void Update(float dt)
    {
        super.Update(dt);

        if(visible)
        {
            // 直線移動
            y -= 10 * GameView.BASE_FPS * dt;

            // 画面外に出たら無効化
            if (y < -(GameView.BASE_HEI / 2 + hei / 2)) {
                visible = false;
            }
        }
    }
}
