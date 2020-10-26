package com.codedynamix.pottyari.System;

import com.codedynamix.pottyari.BG.BGBattle;
import com.codedynamix.pottyari.BG.BGProgress;
import com.codedynamix.pottyari.BG.BGStShGp;
import com.codedynamix.pottyari.BG.BGTitle;
import com.codedynamix.pottyari.Object.Figure;
import com.codedynamix.pottyari.Object.Item;
import com.codedynamix.pottyari.Progress.ProgressHero;
import com.codedynamix.pottyari.UI.ChoiseBack;
import com.codedynamix.pottyari.UI.GameWay;
import com.codedynamix.pottyari.UI.HeroUI;
import com.codedynamix.pottyari.UI.HomeButton;
import com.codedynamix.pottyari.UI.MessageWindow;
import com.codedynamix.pottyari.UI.Status;
import com.codedynamix.pottyari.UI.StatusButton;

public class NowLoading implements Runnable
{

    @Override
    public void run()
    {
        //common
        {
            BGStShGp.loadTexture();
            Item.loadTexture();
            Figure.loadTexture();
            HeroUI.loadTexture();
            ChoiseBack.loadTexture();
            MessageWindow.loadTexture();
        }
        //title
        {
            BGTitle.loadTexture();
            HomeButton.loadTexture();
        }
        //status
        {
            Status.loadTexture();
            StatusButton.loadTexture();
        }
        //game
        {
            BGProgress.loadTexture();
            ProgressHero.loadTexture();
            GameWay.loadTexture();
        }
        //battle
        {
            BGBattle.loadTexture();
        }
    }
}
