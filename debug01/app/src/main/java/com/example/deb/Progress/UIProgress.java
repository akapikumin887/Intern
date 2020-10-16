package com.example.deb.Progress;

import android.view.MotionEvent;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BG.BGProgress;
import com.example.deb.BaseClass.BaseScene;
import com.example.deb.BaseClass.Object;
import com.example.deb.Scene.HomeScene;
import com.example.deb.Scene.ShopScene;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;
import com.example.deb.UI.ChoiseBack;

public class UIProgress extends Object
{
    private ProgressHero progsHero;

    private ChoiseBack back;

    public UIProgress()
    {
        super();
        setLayer(Layer.LAYER_BUTTON);

        progsHero = new ProgressHero();

        back = new ChoiseBack(2);
    }

    @Override
    public void draw()
    {
        progsHero.draw();
        back.draw();
    }

    @Override
    public void update()
    {
        progsHero.update();
    }

    @Override
    public void touch(MotionEvent event)
    {
        //getXYと描画画面との誤差を修正
        Vector2 ratio = new Vector2(GameActivity.getBaseWid() / GameActivity.getSurfaceWid(),
                GameActivity.getBaseHei() / GameActivity.getSurfaceHei());

        Vector2 touchPos = new Vector2(event.getX() * ratio.x - GameActivity.getBaseWid() / 2,(-event.getY() * ratio.y + GameActivity.getBaseHei() / 2));

        if(event.getAction() == MotionEvent.ACTION_DOWN)    //trigger
        {
            //Home遷移
            if(touchPos.x < back.getPosition().x + back.getSize().x / 2 && touchPos.x > back.getPosition().x - back.getSize().x / 2 &&
                    touchPos.y < back.getPosition().y + back.getSize().y / 2 && touchPos.y > back.getPosition().y - back.getSize().y / 2)
            {
                BaseScene.setnextScene(new HomeScene());
            }
        }
    }

}
