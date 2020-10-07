package com.example.androidstadio2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity
{
    GameView gameSurfaceView;

    public static int screenWid, screenHei;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        gameSurfaceView = new GameView(this, true);
        setContentView(gameSurfaceView);

        // WindowManagerのインスタンス取得
        WindowManager wm = getWindowManager();
        // Displayのインスタンス取得
        Display display = wm.getDefaultDisplay();
        screenWid = display.getWidth();
        screenHei = display.getHeight();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        gameSurfaceView.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        gameSurfaceView.onPause();
    }

    @Override
    // 画面タッチ時処理
    public boolean onTouchEvent(MotionEvent event)
    {
        gameSurfaceView.Touch(event);
        return true;
    }

}
