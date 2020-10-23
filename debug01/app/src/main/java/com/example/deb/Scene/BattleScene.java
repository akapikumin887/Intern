package com.example.deb.Scene;

import com.example.deb.BG.BGBattle;
import com.example.deb.BaseClass.BaseScene;
import com.example.deb.Battle.UIBattle;
import com.example.deb.Object.HeroStatus;

public class BattleScene extends BaseScene
{
    private BGBattle bgBattle;

    private UIBattle uiBattle;

    public BattleScene()
    {
        bgBattle = new BGBattle();
        list.add(bgBattle);

        uiBattle = new UIBattle();
        list.add(uiBattle);

        HeroStatus.setIsBattle(true);
    }

    @Override
    public void update()
    {
        //if(EnemyStatus)
        super.update();
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
