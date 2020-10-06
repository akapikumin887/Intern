package com.example.deb.Activity;


import android.content.Context;
import android.opengl.GLSurfaceView;

import com.example.deb.BaseClass.BaseScene;
import com.example.deb.Scene.HomeScene;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

//ゲームループと描画をここで行う
//イメージ的にはここを描画し続けなければならない
public class GameActivity extends GLSurfaceView implements GLSurfaceView.Renderer
{
    private BaseScene scene;

    private GL10 gl10;
    private Context context;

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
            // アルファブレンド有効
            gl.glEnable(GL10.GL_BLEND);
            gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

            // ディザを無効化
            gl.glDisable(GL10.GL_DITHER);
            // カラーとテクスチャ座標の補間精度を、最も効率的なものに指定
            gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);

            // バッファ初期化時のカラー情報をセット
            gl.glClearColor(0, 0, 0, 1);

            // 片面表示を有効に
            gl.glEnable(GL10.GL_CULL_FACE);
            // カリング設定をCCWに
            gl.glFrontFace(GL10.GL_CCW);

            // 深度テストを無効に
            gl.glDisable(GL10.GL_DEPTH_TEST);

            // フラットシェーディングにセット
            gl.glShadeModel(GL10.GL_FLAT);

            //glをもらってきたものに上書きする
            gl10 = gl;
        }

        //シーン設定、上書きは直接行うつもり
        BaseScene scene = new HomeScene(gl10,context);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height)
    {
        //横縦切り替え時 おそらく使わない
    }

    @Override
    public void onDrawFrame(GL10 gl)
    {
        //毎f呼び出される処理なのでここで欲しいものを呼び出す
        //フレーム処理を忘れずに入れておく
        update();
        draw();
    }
    protected void update()
    {
        scene.update();
    }
    protected void draw()
    {
        scene.update();
    }
}
