package com.codedynamix.pottyari.Scene;


import com.codedynamix.pottyari.BaseClass.BaseScene;
import com.codedynamix.pottyari.BG.BGTitle;
import com.codedynamix.pottyari.Title.UITitle;
import com.codedynamix.pottyari.UI.HeroUI;

public class HomeScene extends BaseScene
{
    private BGTitle bgTitle;
    private UITitle uiTitle;
    private HeroUI heroTitle;


    public HomeScene()
    {
//インスタンス初期化
        //背景
        bgTitle = new BGTitle();
        list.add(bgTitle);

        //UI
        uiTitle = new UITitle();
        list.add(uiTitle);

        //勇者
        heroTitle = new HeroUI();
        list.add(heroTitle);
    }
}
