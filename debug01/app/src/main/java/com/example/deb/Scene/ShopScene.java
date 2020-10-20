package com.example.deb.Scene;

import com.example.deb.BG.BGStShGp;
import com.example.deb.BaseClass.BaseScene;
import com.example.deb.Shop.UIShop;

public class ShopScene extends BaseScene
{
    private BGStShGp bgshop;
    private UIShop uiShop;

    public ShopScene()
    {
        super();
        bgshop = new BGStShGp();
        list.add(bgshop);

        uiShop = new UIShop();
        list.add(uiShop);
    }

    @Override
    public void uninit()
    {

        super.uninit();
    }

    @Override
    public void back()
    {
        BaseScene.setnextScene(new StatusScene());
    }
}
