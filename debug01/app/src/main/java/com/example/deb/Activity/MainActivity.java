package com.example.deb.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.deb.R;
import com.example.deb.Scene.HomeScene;

public class MainActivity extends AppCompatActivity
{
    GameActivity gameActivity;
    public static float screenWid, screenHei;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //移動するviewを設定する
        gameActivity = new GameActivity(this,this);
        setContentView(gameActivity);

        View view = this.getWindow().getDecorView();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        // WindowManagerのインスタンス取得
        WindowManager wm = getWindowManager();

        // Displayのインスタンス取得
        Display display = wm.getDefaultDisplay();

        Point point = new Point();
        display.getRealSize(point);
        screenWid = point.x;
        screenHei = point.y;
    }

    @Override
    // 画面タッチ時処理
    public boolean onTouchEvent(MotionEvent event)
    {
        gameActivity.touch(event);
        return true;
    }
}
