package com.example.androidstadio2;

import android.view.MotionEvent;

// Spriteクラスを継承したPlayerクラス
public class Player extends Sprite
{
	private Vector2 velocity = new Vector2();

	public void Init(Texture tex)
	{
		// 基本クラスにクラスの固有情報を渡す
		super.Init(tex, 0, 0, 128, 128, 2, 0.5f);
	}
	
	@Override
	public void Update(float dt)
	{
		// 基本クラスの処理
		super.Update(dt);

		// 操作、当たり判定など固有処理を記述

		// 移動処理
		x += velocity.x * dt;
		y += velocity.y * dt;

		// 移動速度を更新
		velocity.x -= velocity.x * 5 * dt;
		velocity.y -= velocity.y * 5 * dt;
	}

	// タッチイベント処理
	void Touch(MotionEvent event)
	{
		// タッチ座標をゲームシステム座標に変換
		float ratio = GameView.BASE_WID / MainActivity.screenWid;
		float tx = ( event.getX() - MainActivity.screenWid / 2) * ratio;
		float ty = (-event.getY() + MainActivity.screenHei / 2) * ratio;

		// 移動速度を更新
		velocity.x += (tx - x) * 0.5f;
		velocity.y += (ty - y) * 0.5f;
	}
}
