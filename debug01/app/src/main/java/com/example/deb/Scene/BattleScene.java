package com.example.deb.Scene;

import com.example.deb.BG.BGBattle;
import com.example.deb.BaseClass.BaseScene;
import com.example.deb.UI.MessageWindow;

public class BattleScene extends BaseScene
{
    BGBattle bgBattle;
    MessageWindow window;

    public BattleScene()
    {
        bgBattle = new BGBattle();
        list.add(bgBattle);

        //window = new MessageWindow();
    }



}
