package com.example.deb.System;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BG.BGBattle;
import com.example.deb.BG.BGProgress;
import com.example.deb.BG.BGStShGp;
import com.example.deb.BG.BGTitle;
import com.example.deb.Object.Figure;
import com.example.deb.Object.Item;
import com.example.deb.Progress.ProgressHero;
import com.example.deb.UI.ChoiseBack;
import com.example.deb.UI.GameWay;
import com.example.deb.UI.HeroUI;
import com.example.deb.UI.HomeButton;
import com.example.deb.UI.MessageWindow;
import com.example.deb.UI.Status;
import com.example.deb.UI.StatusButton;

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
