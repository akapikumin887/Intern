package com.codedynamix.pottyari.Scene;

import com.codedynamix.pottyari.BG.BGStShGp;
import com.codedynamix.pottyari.BaseClass.BaseScene;
import com.codedynamix.pottyari.Shop.UIShop;

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
