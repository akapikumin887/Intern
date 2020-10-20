package com.example.deb.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.deb.BaseClass.BaseScene;
import com.example.deb.System.StepCount;

import static android.Manifest.permission.ACTIVITY_RECOGNITION;
import static android.content.pm.PackageManager.PERMISSION_DENIED;

public class MainActivity extends AppCompatActivity implements SensorEventListener
{
    GameActivity gameActivity;

    //歩数取得用
    private SensorManager sensorManager;
    private Sensor stepConterSensor;
    private Sensor stepDetectorSensor;

//マクロ定義
    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //SDKVer29以降だと歩数にも権限リクエストがいる
        if(Build.VERSION.SDK_INT >= 29)
        {
                //今回は歩数が欲しい
                if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACTIVITY_RECOGNITION) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(this,new String[]{
                            Manifest.permission.ACTIVITY_RECOGNITION},PERMISSION_REQUEST_CODE);
                }
                else
                {
                    //既に権限の許可をもらっている場合
                    //センサーマネージャを取得
                    sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
                    //センサマネージャから TYPE_STEP_COUNTER についての情報を取得する
                    stepConterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
                    //リスナー設定
                    sensorManager.registerListener (this, stepConterSensor,
                            SensorManager.SENSOR_DELAY_FASTEST);
                }
        }

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //移動するviewを設定する
        gameActivity = new GameActivity(this,this);
        setContentView(gameActivity);

        View view = this.getWindow().getDecorView();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
/*
        sensorManager.registerListener (this,
                accSensor,
                SensorManager.SENSOR_DELAY_FASTEST);

        sensorManager.registerListener (this,
                stepDetectorSensor,
                SensorManager.SENSOR_DELAY_FASTEST);
*/
    }

    @Override
    protected void onDestroy()
    {
        BaseScene scene = BaseScene.getScene();
        scene.uninit();
        StepCount.save();
        super.onDestroy();
    }

    @Override
    protected void onPause()
    {
        StepCount.save();

        super.onPause();
    }

    @Override
    protected void onStop()
    {

        super.onStop();
    }

    @Override
    // 画面タッチ時処理
    public boolean onTouchEvent(MotionEvent event)
    {
        gameActivity.touch(event);
        return true;
    }

    @Override
    public void onBackPressed()
    {
        gameActivity.back();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){}

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        Sensor sensor = event.sensor;
        //TYPE_STEP_COUNTER
        if(sensor.getType() == Sensor.TYPE_STEP_COUNTER)
        {
            // sensor からの値を取得するなどの処理を行う
            StepCount.setTtPhone(event.values[0]);
        }
        StepCount.init();
    }

    //権限リクエストした結果が返ってくるところ
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permission, int[] grantResult)
    {
        if(grantResult.length <= 0){return;}
        switch(requestCode)
        {
            case PERMISSION_REQUEST_CODE:
            {
                if(grantResult[0] == PackageManager.PERMISSION_GRANTED)
                {
                    //許可が取れた時の処理
                    //センサーマネージャを取得
                    sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
                    //センサマネージャから TYPE_STEP_COUNTER についての情報を取得する
                    stepConterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
                    //リスナー設定
                    sensorManager.registerListener (this, stepConterSensor,
                            SensorManager.SENSOR_DELAY_FASTEST);
                }
                else
                {
                    //ダメだった時の処理
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("このアプリには歩数を利用するため、許可してください。").setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            // ボタンをクリックしたときの動作
                        }});
                    builder.show();
                }
            }
            return;
        }
    }
}
