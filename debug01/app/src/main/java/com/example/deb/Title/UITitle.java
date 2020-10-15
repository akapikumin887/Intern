package com.example.deb.Title;

import android.view.MotionEvent;

import com.example.deb.Activity.GameActivity;
import com.example.deb.Activity.MainActivity;
import com.example.deb.BaseClass.BaseScene;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.Scene.StatusScene;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;

public class UITitle extends Object
{
    private static Texture texture;

    private Vector2 settingPos;
    private Vector2 settingSize;

    private Vector2 tipsPos;
    private Vector2 tipsSize;

    private Vector2 startPos;
    private Vector2 startSize;

    public UITitle()
    {
        super();
        setLayer(Layer.LAYER_BUTTON);

        settingSize = tipsSize = new Vector2(120.0f,120.0f);
        startSize = new Vector2(GameActivity.getBaseWid() - 200.0f,GameActivity.getBaseHei() / 6);

        settingPos = new Vector2(GameActivity.getBaseWid() / 2 - settingSize.x / 1.8f, GameActivity.getBaseHei() / 2 - settingSize.y);
        tipsPos    = new Vector2(GameActivity.getBaseWid() / 2 - tipsSize.x / 1.8f, GameActivity.getBaseHei() / 2 - tipsSize.y * 2.2f);
        startPos  = new Vector2(0.0f,-GameActivity.getBaseHei() / 2 + startSize.y / 2 + 100.0f);
    }

    @Override
    public void draw()
    {
        //設定ボタンの描画
        texture.draw(settingPos,settingSize,rotate,reverse, new Vector2(0.25f,0.0f),new Vector2(0.25f,1.0f),color);

        //ヒントボタンの描画
        texture.draw(tipsPos,tipsSize,rotate,reverse, new Vector2(0.0f,0.0f),new Vector2(0.25f,1.0f),color);

        //冒険ボタンの描画
        texture.draw(startPos,startSize,rotate,reverse, new Vector2(0.5f,0.0f),new Vector2(0.5f,1.0f),color);
    }

    @Override
    public void update()
    {

    }


    public static void loadTexture()
    {
        texture = new Texture();
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.home_button);
    }

    @Override
    public void touch(MotionEvent event)
    {
        Vector2 ratio = new Vector2(GameActivity.getBaseWid() / GameActivity.getSurfaceWid(),
                GameActivity.getBaseHei() / GameActivity.getSurfaceHei());

        Vector2 touchPos = new Vector2(event.getX() * ratio.x - GameActivity.getBaseWid() / 2,(-event.getY() * ratio.y + GameActivity.getBaseHei() / 2));
        if(event.getAction() == MotionEvent.ACTION_DOWN)    //trigger
        {
            //当たり判定取得(めんどい)
            if(touchPos.x < startPos.x + startSize.x / 2 && touchPos.x > startPos.x - startSize.x / 2 &&
                    touchPos.y < startPos.y + startSize.y / 2 && touchPos.y > startPos.y - startSize.y / 2)
            {
                //シーン遷移
                BaseScene.setnextScene(new StatusScene());
            }

        }
    }
}
