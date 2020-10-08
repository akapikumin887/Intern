package com.example.deb.Activity;


import android.content.Context;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import android.app.Activity;
import android.view.Display;
import android.view.MotionEvent;

import com.example.deb.BaseClass.BaseScene;
import com.example.deb.Scene.HomeScene;
import com.example.deb.Scene.StatusScene;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

//ゲームループと描画をここで行う
//イメージ的にはここを描画し続けなければならない
public class GameActivity extends GLSurfaceView implements GLSurfaceView.Renderer
{
    private BaseScene scene;

    private static GL10 gl10;
    public static Context context;

    //解像度らしい 直接値入れているのはあまりよくわからない
    public static float BASE_WID = 768;
    public static float BASE_HEI = 0;
    // サーフェイスの幅・高さ
    public static int surfaceWid;
    public static int surfaceHei;

    //画面のサイズ取得？？
    public static Point getDisplaySize(Activity activity)
    {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        return point;
    }

    //コンストラクタ
    public GameActivity(Context context, MainActivity activity)
    {
        super(context);
        this.context = context;

        setRenderer(this);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config)
    {
        //init


        //OpenGLの初期化
        {
            //背景色のクリア
            gl.glClearColor(0.5f,1.0f,0.5f,1.0f);

            // アルファブレンド有効
            gl.glEnable(GL10.GL_BLEND);
            gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

            // ディザを無効化
            gl.glDisable(GL10.GL_DITHER);
            //深度テストを無効化
            gl.glDisable(GL10.GL_DEPTH_TEST);
            //ライティングを無効化
            gl.glDisable(GL10.GL_LIGHTING);
            // カラーとテクスチャ座標の補間精度を、最も効率的なものに指定
            gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);

            // 片面表示を有効に
            gl.glEnable(GL10.GL_CULL_FACE);
            // カリング設定をCCWに
            gl.glFrontFace(GL10.GL_CCW);

            // フラットシェーディングにセット
            gl.glShadeModel(GL10.GL_FLAT);

            //glをもらってきたものに上書きする
            gl10 = gl;
        }

        //シーン設定、上書きは直接行うつもり
        scene = new HomeScene();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height)
    {
        //横縦切り替え時 画面初期化時に通る
        gl10 = gl;

        // サーフェイスの幅・高さを更新
        surfaceWid = width;
        surfaceHei = height;

        // ビューポートをサイズに合わせてセットしなおす
        gl.glViewport(0, 0, width, height);

        // 射影行列を選択
        gl.glMatrixMode(GL10.GL_PROJECTION);
        // 現在選択されている行列(射影行列)に、単位行列をセット
        gl.glLoadIdentity();
        // 平行投影用のパラメータをセット
        if(BASE_WID == 0) {
            BASE_WID = (float)width * BASE_HEI / height;
        }
        else {
            BASE_HEI = (float)height * BASE_WID / width;
        }
        GLU.gluOrtho2D(gl, -BASE_WID / 2, BASE_WID / 2, -BASE_HEI / 2, BASE_HEI / 2);
    }

    @Override
    public void onDrawFrame(GL10 gl)
    {
        //毎f呼び出される処理なのでここで欲しいものを呼び出す
        //フレーム処理を忘れずに入れておく
        update();

        // 描画用バッファをクリア
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        draw();
    }

    public void touch(MotionEvent event)
    {
        //このタッチの処理はplessと同じ扱い
        scene.touch(event);
        scene.uninit();
        scene = new StatusScene();
        if(event.getAction() == MotionEvent.ACTION_UP)
        {
            // 離した際の処理(releace)

        }
        else if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            // 押した際の処理（trigger）

        }

    }

    protected void update()
    {
        scene.update();
    }
    protected void draw()
    {
        scene.draw();
    }

    public static GL10 getGL(){return gl10;}
    public static Context getCntxt(){return context;}       //contextのgetterだがなにやら別の場所で同名が定義されているため名前を一部変更
}