package com.example.deb.Scene;

import com.example.deb.BG.BGBattle;
import com.example.deb.BaseClass.BaseScene;
import com.example.deb.Battle.Battle;
import com.example.deb.Battle.UIBattle;

public class BattleScene extends BaseScene
{
    private BGBattle bgBattle;

    private UIBattle uiBattle;

    private Battle battle;

    public BattleScene()
    {
        bgBattle = new BGBattle();
        list.add(bgBattle);

        battle = new Battle();
        list.add(battle);

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
