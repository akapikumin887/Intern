package com.example.androidstadio2;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.view.MotionEvent;

import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

//Alt+Shift+Enter

public class GameView<Sprite> extends GLSurfaceView implements GLSurfaceView.Renderer {

    public static GL10 gl10;
    public static Context context;

    // 描画基準とする解像度を指定
    // 縦横比は端末により異なるため、幅/高さの一方だけを指定し
    // もう一方は0にしておいて計算で求める
    public static float BASE_WID = 768;
    public static float BASE_HEI = 0;

    // 目標FPS
    public static final int BASE_FPS = 30;

    // サーフェイスの幅・高さ
    public static int surfaceWid;
    public static int surfaceHei;

    // FPS計測
    protected FPSManager fpsManager;
    // 毎フレーム何ミリ秒か
    private long frameTime;
    // Sleep時間
    private long sleepTime;
    // フレームスキップを行うかどうか
    private boolean frameSkipEnable;
    // フレームスキップが無効かどうか
    private boolean frameSkipState;
    //敵の最大数
    private final int ENEMY_MAX = 100;

    Texture tex_bg;

    //クラス定義
    Texture tex_player;
    Player player;

    Bg bg[];

    Enemy[] enemy;

    Texture tex_effect;
    Effect[] effect;

    Texture tex_Score;
    Score score;

    public GameView(Context context, boolean fs_enable)
    {
        super(context);
        this.context = context;

        setRenderer(this);

        // FPS周りの初期化
        frameSkipEnable = fs_enable;
        frameSkipState = false;
        fpsManager = new FPSManager(10);
        sleepTime = 0;
        frameTime = (long) (1000.0f / BASE_FPS);
    }

    @Override
    public void onDrawFrame(GL10 gl)
    {
        fpsManager.calcFPS();

        // フレームスキップ有効時のみ処理
        if (frameSkipState)
        {
            // 前回呼び出し時からの経過時間を取得
            long elapsedTime = fpsManager.getElapsedTime();
            // 直前のSleep時間を引く
            elapsedTime -= sleepTime;

            // 設定されている単位時間より小さければ、差分だけSleepし、経過時間を0に
            if (elapsedTime < frameTime && elapsedTime > 0l)
            {
                sleepTime = frameTime - elapsedTime;
                try
                {
                    Thread.sleep(sleepTime);
                }
                catch (InterruptedException e)
                {
                }
                elapsedTime = 0;
            }
            else
            {
                // スリープ時間を0に
                sleepTime = 0;
                // 単位時間を引く
                elapsedTime -= frameTime;
            }
            // それでもまだ、単位時間より経過時間が大きければ
            if (elapsedTime >= frameTime)
            {
                // フレームスキップ(更新処理を全部一度に実行してしまう)
                if (frameSkipEnable)
                {
                    for (; elapsedTime >= frameTime; elapsedTime -= frameTime)
                        Update((float)frameTime / 1000);
                }
            }
        }
        else
        {
            // 次回のフレームから有効に
            frameSkipState = true;
        }

        Update((float)frameTime / 1000);

        // 描画用バッファをクリア
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        Draw();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height)
    {
        gl10 = gl;

        // 大きな遅延が起こるので、次回フレーム処理時のフレームスキップを無効に
        frameSkipState = false;

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

        tex_bg = new Texture();
        tex_bg.Load(gl10, context, R.drawable.bg000);

        //描画に使うリソースをロード
        tex_player = new Texture();
        tex_player.Load(gl10,context,R.drawable.player001);

        //Playerクラスのインスタンス作成、初期化
        player = new Player();
        player.Init(tex_player);

        // 2枚使ってスクロール
        bg = new Bg[2];
        // 表示上の幅・高さを画面サイズと元画像の縦横比から計算
        float wid = BASE_WID;
        float hei = wid * tex_bg.hei / tex_bg.wid;
        for(int i = 0; i < 2; i++)
        {
            bg[i].Init(tex_bg, 0, i * hei, wid, hei);
            bg[i] = new Bg();
        }

        //敵の初期化処理
        Enemy.LoadTexture();
        enemy = new Enemy[ENEMY_MAX];

        tex_effect = new Texture();
        tex_effect.Load(gl10, context, R.drawable.effect000);
        effect = new Effect[ENEMY_MAX];
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config)
    {
        // 大きな遅延が起こるので、次回フレーム処理時のフレームスキップを無効に
        frameSkipState = false;

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
    }

    public void onPause()
    {
        // 大きな遅延が起こるので、次回フレーム処理時のフレームスキップを無効に
        frameSkipState = false;
    }

    public void onResume()
    {
        // 大きな遅延が起こるので、次回フレーム処理時のフレームスキップを無効に
        frameSkipState = false;
    }

    // 更新
    private void Update(float dt)
    {
        for(int i = 0; i < 2; i++)  bg[i].Update(dt);
        player.Update(dt);

        // 爆発エフェクト更新
        for(int i = 0; i < ENEMY_MAX; i++)
        {
            if(effect[i] != null)
            {
                effect[i].Update(dt);

                // 再生し終わったら解放(Javaにはdeleteはなく、nullをセットすることで解放)
                if(!effect[i].visible) effect[i] = null;
            }
        }

        // 敵更新
        for(int i = 0; i < ENEMY_MAX; i++)
        {
            if(enemy[i] != null)
            {
                enemy[i].Update(dt);

                // プレイヤーとの当たり判定
                float r = enemy[i].HitSize() / 2;
                if(enemy[i].Distance2(new Vector2(player.x, player.y)) <= r * r)
                {
                    // 体当たりで敵を破壊した
                    for(int j = 0; j < ENEMY_MAX; j++)
                    {
                        // 配列の空き領域に爆発エフェクトインスタンス生成
                        if(effect[j] == null)
                        {
                            effect[j] = new Effect();
                            effect[j].Init(tex_effect, enemy[i].x, enemy[i].y);
                            break;
                        }
                    }
                    // スコア加算
                    score.score += enemy[i].Score();

                    // 敵を消滅・解放(Javaにはdeleteはなく、nullをセットすることで解放)
                    enemy[i] = null;

                    // SE再生
                    //Sound.PlaySE(R.raw.explosion000);
                }
                else
                 {
                    // 画面外に出たら解放(Javaにはdeleteはなく、nullをセットすることで解放)
                    if (!enemy[i].visible) enemy[i] = null;
                 }
            }
        }

        // 大体、1秒ごとのランダムで敵生成
        Random rand = new Random();
        int r = rand.nextInt(BASE_FPS);
        if(r == 0) SetEnemy();
    }




    // 描画
    private void Draw()
    {
        for(int i = 0; i < 2; i++)
            bg[i].Draw();
        for(int i = 0; i < ENEMY_MAX; i++)
        {
            if(enemy[i] != null)
            {
                enemy[i].Draw();
            }
        }
        player.Draw();
    }

    public void Touch(MotionEvent event)
    {
        player.Touch(event);
        if(event.getAction() == MotionEvent.ACTION_UP)
        {
            // 離した際の処理

        }
        else if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            // 押した際の処理（トリガー処理）

        }
    }

    //敵の描画
    private void SetEnemy()
    {
        for(int i = 0; i < ENEMY_MAX; i++)
        {
            // 配列の空き領域に敵インスタンス生成
            if(enemy[i] == null)
            {
                // ランダムな種類を生成
                Random rand = new Random();
                int r = rand.nextInt(Enemy.TYPE_MAX);
                switch(r)
                {
                    case 0: enemy[i] = new EnemyA(); break;
                    case 1: enemy[i] = new EnemyB(); break;
                }

                // X座標はランダム、Y座標は画面上部
                enemy[i].Init(rand.nextInt((int)BASE_WID) - BASE_WID / 2, BASE_HEI / 2);
                break;
            }
        }
    }
}
