package com.example.deb.Scene;

import android.view.MotionEvent;

import com.example.deb.BG.BGStShGp;
import com.example.deb.BaseClass.BaseScene;
import com.example.deb.Object.Item;

public class ShopScene extends BaseScene
{
    private BGStShGp bgshop;
    private Item item;

    public ShopScene()
    {
        super();
        bgshop = new BGStShGp();
        list.add(bgshop);

        item = new Item(0.75f);
        list.add(item);

    }

    @Override
    public void uninit()
    {

        super.uninit();
    }

    @Override
    public void touch(MotionEvent event)
    {
        super.touch(event);
    }
}
