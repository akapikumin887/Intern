package com.example.deb.Scene;

import com.example.deb.BG.BGBattle;
import com.example.deb.BaseClass.BaseScene;
import com.example.deb.Battle.UIBattle;

public class BattleScene extends BaseScene
{
    BGBattle bgBattle;

    UIBattle uiBattle;

    public BattleScene()
    {
        bgBattle = new BGBattle();
        list.add(bgBattle);

        uiBattle = new UIBattle();
        list.add(uiBattle);
    }

    @Override
    public void uninit()
    {

        super.uninit();
    }

    @Override
    public void back()
    {
        BaseScene.setnextScene(new ProgressScene());
    }

}
