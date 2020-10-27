package com.codedynamix.pottyari.System;

import com.codedynamix.pottyari.BG.BGBattle;
import com.codedynamix.pottyari.BG.BGProgress;
import com.codedynamix.pottyari.BG.BGStShGp;
import com.codedynamix.pottyari.BG.BGTitle;
import com.codedynamix.pottyari.Battle.BattleCommand;
import com.codedynamix.pottyari.Object.Figure;
import com.codedynamix.pottyari.Object.Item;
import com.codedynamix.pottyari.Progress.Coffin;
import com.codedynamix.pottyari.Progress.ProgressHero;
import com.codedynamix.pottyari.UI.BattleText;
import com.codedynamix.pottyari.UI.ChoiseBack;
import com.codedynamix.pottyari.UI.Enemy;
import com.codedynamix.pottyari.UI.GameWay;
import com.codedynamix.pottyari.UI.Game_stepword;
import com.codedynamix.pottyari.UI.HeroUI;
import com.codedynamix.pottyari.UI.HomeButton;
import com.codedynamix.pottyari.UI.HpBar;
import com.codedynamix.pottyari.UI.ItemName;
import com.codedynamix.pottyari.UI.MessageWindow;
import com.codedynamix.pottyari.UI.Reinforcement;
import com.codedynamix.pottyari.UI.ShopText;
import com.codedynamix.pottyari.UI.Status;
import com.codedynamix.pottyari.UI.StatusButton;

public class NowLoading implements Runnable
{
    private static boolean isLoad = false;
    private static boolean isAnim = false;
    @Override
    public void run()
    {

        //animation
        {
            ProgressHero.loadTexture();
            isAnim = true;
        }
        //common
        {
            BGStShGp.loadTexture();
            Item.loadTexture();
            Figure.loadTexture();
            HeroUI.loadTexture();
            ChoiseBack.loadTexture();
            MessageWindow.loadTexture();
            ShopText.loadTexture();
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
        //shop
        {
            ItemName.loadTexture();
            Reinforcement.loadTexture();
        }
        //game
        {
            BGProgress.loadTexture();
            GameWay.loadTexture();
            Game_stepword.loadTexture();
            Coffin.loadTexture();
            BattleText.loadTexture();
        }
        //battle
        {
            BGBattle.loadTexture();
            HpBar.loadTexture();
            BattleCommand.loadTexture();
            Enemy.loadTexture();
        }
        isLoad = true;
    }

    public static boolean getIsLoad() {return isLoad;}
    public static boolean getIsAnim() {return isAnim;}
}
