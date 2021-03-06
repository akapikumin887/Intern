package com.codedynamix.pottyari.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.codedynamix.pottyari.BaseClass.BaseScene;
import com.codedynamix.pottyari.Object.HeroStatus;
import com.codedynamix.pottyari.System.StepCount;

import static com.codedynamix.pottyari.Activity.GameActivity.getActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener
{
    private GameActivity gameActivity;

    //歩数取得用
    private SensorManager sensorManager;
    private Sensor stepConterSensor;

//マクロ定義
    private static final int PERMISSION_REQUEST_CODE = 1;
    private final int ERROR_CODE = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        StepCount.setTtPhone(ERROR_CODE);   //この値から変化がなかったら読み込み失敗扱いにする

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
                            SensorManager.SENSOR_DELAY_UI);
                }
        }
        else
        {
            //ver28未満なら許可をもらわずに歩数をもらえる
            //センサーマネージャを取得
            sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
            //センサマネージャから TYPE_STEP_COUNTER についての情報を取得する
            stepConterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            //リスナー設定
            sensorManager.registerListener (this, stepConterSensor,
                    SensorManager.SENSOR_DELAY_UI);
        }

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //移動するviewを設定する
        gameActivity = new GameActivity(this,this);
        setContentView(gameActivity);

//        View view = this.getWindow().getDecorView();
//        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

    }

    @Override
    protected void onResume()
    {
        super.onResume();

        //歩数取得の権限をもらっていたら
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_GRANTED)
        {
            //センサーマネージャを取得
            sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
            //センサマネージャから TYPE_STEP_COUNTER についての情報を取得する
            stepConterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            //リスナー設定
            sensorManager.registerListener (this, stepConterSensor,
                    SensorManager.SENSOR_DELAY_UI);
        }

        //起動したときや復帰した時の処理
        BaseScene scene = BaseScene.getScene();
        if(scene != null)
            scene.init();

//        View view = this.getWindow().getDecorView();
//        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        gameActivity.onResume();
    }

    @Override
    protected void onDestroy()
    {
        //アプリが完全に削除された時の処理
        BaseScene scene = BaseScene.getScene();
        scene.uninit();
        BaseScene.setScene(null);
        StepCount.save();
        super.onDestroy();
    }

    @Override
    protected void onPause()
    {
        //アプリが中断された時の処理
        //StepCount.save();
        //HeroStatus.save();


        //gameActivity.onPause();
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
                            SensorManager.SENSOR_DELAY_UI);
                }
                else
                {
                    new AlertDialog.Builder(getActivity())
                            .setMessage("このアプリには歩数を利用するため、\n機能が正常に作動しない可能性がございます。")
                            .setPositiveButton("わかった", null)
                            .show();
                }
            }
            return;
        }
    }
}
