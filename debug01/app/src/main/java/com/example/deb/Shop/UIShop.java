package com.example.deb.Shop;

import android.view.MotionEvent;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.BaseScene;
import com.example.deb.BaseClass.Object;
import com.example.deb.Scene.HomeScene;
import com.example.deb.Scene.StatusScene;
import com.example.deb.System.Texture;
import com.example.deb.System.Vector2;
import com.example.deb.UI.ChoiseBack;

public class UIShop extends Object
{
    private ChoiseBack left;
    private ChoiseBack write;
    private ChoiseBack back;

    public UIShop()
    {
        super();
        setLayer(Layer.LAYER_BUTTON);

        //左右ボタン
        left = new ChoiseBack(0);
        write = new ChoiseBack(1);

        //戻る
        back = new ChoiseBack(2);

    }

    @Override
    public void draw()
    {
        //左右ボタン
        left.draw();
        write.draw();

        //戻るボタン
        back.draw();

    }

    public static void loadTexture()
    {

    }

    @Override
    public void touch(MotionEvent event)
    {
        //getXYと描画画面との誤差を修正
        Vector2 ratio = new Vector2(GameActivity.getBaseWid() / GameActivity.getSurfaceWid(),
                GameActivity.getBaseHei() / GameActivity.getSurfaceHei());

        Vector2 touchPos = new Vector2(event.getX() * ratio.x - GameActivity.getBaseWid() / 2,(-event.getY() * ratio.y + GameActivity.getBaseHei() / 2));

        //Status遷移
        if(touchPos.x < back.getPosition().x + back.getSize().x / 2 && touchPos.x > back.getPosition().x - back.getSize().x / 2 &&
                touchPos.y < back.getPosition().y + back.getSize().y / 2 && touchPos.y > back.getPosition().y - back.getSize().y / 2)
        {
            BaseScene.setnextScene(new StatusScene());
        }

    }
}
