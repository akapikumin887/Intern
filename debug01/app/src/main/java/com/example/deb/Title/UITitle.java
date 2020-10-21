package com.example.deb.Title;

import android.view.MotionEvent;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.BaseScene;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.Scene.BattleScene;
import com.example.deb.Scene.ProgressScene;
import com.example.deb.Scene.StatusScene;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;
import com.example.deb.UI.HeroUI;
import com.example.deb.UI.HomeButton;

public class UITitle extends Object
{
    HomeButton hint;
    HomeButton setting;
    HomeButton adventure;

    HeroUI heroTitle;

    public UITitle()
    {
        super();
        setLayer(Layer.LAYER_BUTTON);

        hint = new HomeButton(0);
        setting = new HomeButton(1);
        adventure = new HomeButton(2);

        heroTitle = new HeroUI();
        heroTitle.setSize(new Vector2(800.0f,800.0f));
        heroTitle.setPosition(new Vector2(0.0f,100.0f));
    }

    @Override
    public void draw()
    {
        heroTitle.draw();
        hint.draw();
        setting.draw();
        adventure.draw();
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
            if(touchPos.x < adventure.getPosition().x + adventure.getSize().x / 2 && touchPos.x > adventure.getPosition().x - adventure.getSize().x / 2 &&
                    touchPos.y < adventure.getPosition().y + adventure.getSize().y / 2 && touchPos.y > adventure.getPosition().y - adventure.getSize().y / 2)
            {
                //冒険する を押したらゲーム画面へ
                BaseScene.setnextScene(new ProgressScene());
            }

            if(touchPos.x < hint.getPosition().x + hint.getSize().x / 2 && touchPos.x > hint.getPosition().x - hint.getSize().x / 2 &&
                    touchPos.y < hint.getPosition().y + hint.getSize().y / 2 && touchPos.y > hint.getPosition().y - hint.getSize().y / 2)
            {
                //デバッグ用 はてなを押したらバトル画面へ
                BaseScene.setnextScene(new BattleScene());
            }

            if(touchPos.x < heroTitle.getPosition().x + heroTitle.getSize().x / 4 && touchPos.x > heroTitle.getPosition().x - heroTitle.getSize().x / 4 &&
                    touchPos.y < heroTitle.getPosition().y + heroTitle.getSize().y / 4 && touchPos.y > heroTitle.getPosition().y - heroTitle.getSize().y / 3)
            {
                //プレイヤーを押したらステータス確認画面へ
                BaseScene.setnextScene(new StatusScene());
            }
        }
    }
}
