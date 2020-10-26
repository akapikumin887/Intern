package com.codedynamix.pottyari.Scene;

import com.codedynamix.pottyari.BG.BGBattle;
import com.codedynamix.pottyari.BaseClass.BaseScene;
import com.codedynamix.pottyari.Battle.UIBattle;
import com.codedynamix.pottyari.Object.HeroStatus;
import com.codedynamix.pottyari.UI.Enemy;

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
        if(!uiBattle.getIsLog())
            BaseScene.setnextScene(new ProgressScene());
    }

}
