package com.example.deb.Shop;

import android.view.MotionEvent;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.BaseScene;
import com.example.deb.BaseClass.Object;
import com.example.deb.Object.Item;
import com.example.deb.Scene.StatusScene;
import com.example.deb.System.Vector2;
import com.example.deb.UI.ChoiseBack;

public class UIShop extends Object
{
    private ChoiseBack left;
    private ChoiseBack right;
    private ChoiseBack back;

    private Item heal;
    private Item resurrection;

    private int itemSelect;

    public UIShop()
    {
        super();
        setLayer(Layer.LAYER_BUTTON);

        //左右ボタン
        left = new ChoiseBack(0);
        right = new ChoiseBack(1);

        //戻る
        back = new ChoiseBack(2);

        //アイテム
        heal = new Item(0.5f);
        resurrection = new Item(0.75f);

        itemSelect = 0;
    }

    @Override
    public void draw()
    {
        //左右ボタン
        if(itemSelect != 0)
            left.draw();
        if(itemSelect != Item.getItemMax() - 1)
            right.draw();

        //戻るボタン
        back.draw();

        //アイテムボタン
        switch(itemSelect)
        {
            case 0:
                heal.draw();
                break;
            case 1:
                resurrection.draw();
                break;
        }
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

        //左ボタン
        if(touchPos.x < left.getPosition().x + left.getSize().x / 2 && touchPos.x > left.getPosition().x - left.getSize().x / 2 &&
                touchPos.y < left.getPosition().y + left.getSize().y / 2 && touchPos.y > left.getPosition().y - left.getSize().y / 2)
        {
            if(itemSelect != 0)
                itemSelect--;
        }

        //左ボタン
        if(touchPos.x < right.getPosition().x + right.getSize().x / 2 && touchPos.x > right.getPosition().x - right.getSize().x / 2 &&
                touchPos.y < right.getPosition().y + right.getSize().y / 2 && touchPos.y > right.getPosition().y - right.getSize().y / 2)
        {
            if(itemSelect != Item.getItemMax() - 1)
                itemSelect++;
        }

    }
}
