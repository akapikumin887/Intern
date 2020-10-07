package com.example.androidstadio2;

import java.util.regex.Pattern;

// 敵情報クラス(構造体の代わり)
class EnemyInfo
{
    String className;   // クラス名
    int resId;          // リソースID
    float wid, hei;     // 横幅・縦幅
    int pattern;        // アニメパターン数
    float interval;     // アニメ間隔
    float hit;          // 当たり判定サイズ
    int score;          // 得点

    public EnemyInfo(String className, int resId, float wid, float hei, int pattern, float interval, float hit, int score)
    {
        this.className = className;
        this.resId = resId;
        this.wid = wid;
        this.hei = hei;
        this.pattern = pattern;
        this.interval = interval;
        this.hit = hit;
        this.score = score;
    }
}

// 敵ベースクラス
public class Enemy extends Sprite
{
    //敵の最大数
    private final int ENEMY_MAX = 100;

    // 敵情報テーブル(敵を作る際ここに追加)
    private static EnemyInfo info[] = {
            new EnemyInfo(
                    "EnemyA",               // クラス名
                    R.drawable.enemy001,    // 画像リソースID
                    100, 100,               // 横幅・縦幅
                    2,                      // アニメパターン数
                    0.1f,                   // アニメ間隔
                    80,                         // 当たり判定サイズ
                    100                     // 得点
            ),
            new EnemyInfo(
                    "EnemyB",               // クラス名
                    R.drawable.enemy000,    // 画像リソースID
                    100, 100,               // 横幅・縦幅
                    2,                      // アニメパターン数
                    0.1f,                   // アニメ間隔
                    80,                         // 当たり判定サイズ
                    200                     // 得点
            ),
    };

    // 敵の種類数＝テーブルのサイズ
    public static final int TYPE_MAX = info.length;

    // テクスチャ情報テーブル
    private static Texture[] tex = new Texture[info.length];

    // 敵の種類(テーブルのインデックス)
    private int type;

    // 使用画像を全て読み込み
    public static void LoadTexture()
    {
        for(int i = 0; i < info.length; i++)
        {
            tex[i] = new Texture();
            tex[i].Load(GameView.gl10, GameView.context, info[i].resId);
        }
    }

    // 初期化
    public void Init(
            float x,        // X座標
            float y         // Y座標
    )
    {
        // クラス名を取得し情報テーブルを使用して初期化
        String[] s = this.getClass().getName().split(Pattern.quote("."));
        for(int i = 0; i < info.length; i++)
        {
            if(s[s.length - 1].equals(info[i].className))
            {
                Init(tex[i], x, y, info[i].wid, info[i].hei, info[i].pattern, info[i].interval);
                visible = true;
                type = i;
                break;
            }
        }
    }

    //敵の配置

    // 得点をテーブルから取得
    public int Score()
    {
        return info[type].score;
    }

    // 当たり判定サイズを取得
    public float HitSize() { return info[type].hit; }
}
