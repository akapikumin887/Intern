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

    private Enemy enemy;

    public BattleScene(int num,Enemy.ENEMY_TYPE type)
    {
        bgBattle = new BGBattle();
        list.add(bgBattle);

        uiBattle = new UIBattle();
        list.add(uiBattle);

        //敵
        enemy = new Enemy(num, type);
        list.add(enemy);

        HeroStatus.setIsBattle(true);
    }


    @Override
    public void update()
    {
        super.update();
        if(!HeroStatus.getIsBattle())
            list.remove(enemy);
    }

    @Override
    public void back()
    {
        if(!uiBattle.getIsLog())
            BaseScene.setnextScene(new ProgressScene());
    }

}
