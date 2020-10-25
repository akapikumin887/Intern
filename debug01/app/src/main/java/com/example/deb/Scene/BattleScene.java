package com.example.deb.Scene;

import com.example.deb.BG.BGBattle;
import com.example.deb.BaseClass.BaseScene;
import com.example.deb.Battle.UIBattle;
import com.example.deb.Object.HeroStatus;
import com.example.deb.UI.Enemy;

public class BattleScene extends BaseScene
{
    private BGBattle bgBattle;

    private UIBattle uiBattle;

    private Enemy boss;

    public BattleScene(int num)
    {
        bgBattle = new BGBattle();
        list.add(bgBattle);

        uiBattle = new UIBattle();
        list.add(uiBattle);

        //敵
        boss = new Enemy(num);
        list.add(boss);

        HeroStatus.setIsBattle(true);
    }

    @Override
    public void update()
    {
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
