package com.example.androidstadio2;

// スプライトクラス
public class Sprite {

    private Texture texture;    // テクスチャクラス

    public float x, y;      // X、Y座標
    public float wid, hei;  // 横幅、縦幅
    public float rotate;    // 回転角度
    public boolean reverse; // 反転表示
    public float r;         // カラーR成分
    public float g;         // カラーG成分
    public float b;         // カラーB成分
    public float a;         // アルファ値
    public int pattern;     // アニメパターン数
    public float interval;  // アニメ間隔(ms)
    public int loop;        // ループカウンタ
    public boolean visible; // 可視状態

    protected float animCnt;   // アニメカウンタ

    // 初期化(アニメーションなし)
    public void Init(
            Texture tex,            // 表示テクスチャ
            float x,                // X座標
            float y,                // Y座標
            float wid,              // 横幅
            float hei               // 縦幅
    )
    {
        // オーバーロード関数を呼び出す
        Init(tex, x, y, wid, hei, 0, false, 1, 1, 1, 1, 1, 1);
    }

    // 初期化(簡易設定)
    public void Init(
            Texture tex,            // 表示テクスチャ
            float x,                // X座標
            float y,                // Y座標
            float wid,              // 横幅
            float hei,              // 縦幅
            int pattern,
            float interval
    )
    {
        // オーバーロード関数を呼び出す
        Init(tex, x, y, wid, hei, 0, false, 1, 1, 1, 1, pattern, interval);
    }

    // 初期化(詳細設定)
    public void Init(
            Texture tex,            // 表示テクスチャ
            float x,                // X座標
            float y,                // Y座標
            float wid,              // 横幅
            float hei,              // 縦幅
            float rotate,           // 回転角度
            boolean reverse,        // 反転表示
            float r,                // カラーR成分
            float g,                // カラーG成分
            float b,                // カラーB成分
            float a,                // アルファ値
            int pattern,            // アニメパターン数
            float interval          // アニメ間隔
        )
    {
        this.texture = tex;
        this.x = x;
        this.y = y;
        this.wid = wid;
        this.hei = hei;
        this.rotate = rotate;
        this.reverse = reverse;
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        this.pattern = pattern;
        this.interval = interval;
        this.loop = 0;
        this.visible = true;

        if(this.interval <= 0) this.interval = 1;
        animCnt = 0;
    }

    // 終了
    public void Uninit()
    {
    }

    // 更新
    public void Update(float dt)
    {
        if(!visible) return;

        // アニメカウンタ更新
        animCnt += dt;
        while(animCnt >= interval * pattern)
        {
            animCnt -= interval * pattern;
            loop++;
        }
    }

    // 描画
    public void Draw()
    {
        // 読込失敗時
        if(texture == null) return;
        if(!visible) return;

        // テクスチャ描画
        texture.Draw(GameView.gl10,
                x, y, wid, hei,
                rotate, reverse,
                (float)((int)(animCnt / interval) % pattern) / pattern, 0, 1.0f / pattern, 1,
                r, g, b, a
        );
    }

    // 距離の2乗(当たり判定用)
    public float Distance2(Vector2 v)
    {
        float dx = v.x - x, dy = v.y - y;
        return dx * dx + dy * dy;
    }

}
