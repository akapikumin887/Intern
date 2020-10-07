package com.example.androidstadio2;

// Spriteクラスを継承したEffectクラス
public class Effect extends Sprite
{
    public void Init(Texture tex, float x, float y)
    {
        // 基本クラスにクラスの固有情報を渡す
        super.Init(tex, x, y, 128, 128, 8, 1.0f / GameView.BASE_FPS);
    }

    @Override
    // 更新処理(Spriteクラスの処理をオーバーライド)
    public void Update(float dt)
    {
        super.Update(dt);

        // アニメーションが1ループしたら無効化
        if(loop > 0) visible = false;
    }
}
