package com.example.deb.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        if(ContextCompat.checkSelfPermission(this,ACTIVITY_RECOGNITION) == PERMISSION_DENIED)
        {
            shouldShowRequestPermissionRationale(ACTIVITY_RECOGNITION);
            //RequestPermission
        }
        super.onCreate(savedInstanceState);

        //センサーマネージャを取得
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        //センサマネージャから TYPE_STEP_COUNTER についての情報を取得する
        stepConterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        //リスナー設定
        sensorManager.registerListener (this, stepConterSensor,
                                         SensorManager.SENSOR_DELAY_FASTEST);

/*
        //加速度も取得してみる
        accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
*/
        //加速度も取得してみる
        stepDetectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //移動するviewを設定する
        gameActivity = new GameActivity(this,this);
        setContentView(gameActivity);

        View view = this.getWindow().getDecorView();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


/*
        sensorManager.registerListener(new SensorEventListener()
        {
            @Override
            public void onSensorChanged(SensorEvent event)
            {
                StepCount.setTtPhone(event.values[0]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        }, stepConterSensor, SensorManager.SENSOR_DELAY_UI);
*/
    }

    @Override
    protected void onResume()
    {
        super.onResume();
/*
        sensorManager.registerListener (this,
                accSensor,
                SensorManager.SENSOR_DELAY_FASTEST);
*/
        sensorManager.registerListener (this,
                stepDetectorSensor,
                SensorManager.SENSOR_DELAY_FASTEST);

    }

    @Override
    protected void onDestroy()
    {
        BaseScene scene = BaseScene.getScene();
        scene.uninit();
        StepCount.uninit();
        super.onDestroy();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        StepCount.getSensorManager().unregisterListener(this,StepCount.getSensor());

        BaseScene scene = BaseScene.getScene();
        scene.uninit();

        StepCount.uninit();
        super.onPause();
    }

    @Override
    protected void onStop()
    {
        BaseScene scene = BaseScene.getScene();
        scene.uninit();

        //StepCount.uninit();
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
    public void onAccuracyChanged(Sensor sensor, int accuracy){}

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        Sensor sensor = event.sensor;
        //TYPE_STEP_COUNTER
        if(sensor.getType() == Sensor.TYPE_STEP_COUNTER)
        {
            // sensor からの値を取得するなどの処理を行う
            StepCount.setTtPhone(StepCount.getAll() + event.values[0]);
        }
        if(sensor.getType() == Sensor.TYPE_STEP_DETECTOR)
        {
            // sensor からの値を取得するなどの処理を行う
            StepCount.setTtPhone(StepCount.getAll() + event.values[0]);
        }
    }
}
