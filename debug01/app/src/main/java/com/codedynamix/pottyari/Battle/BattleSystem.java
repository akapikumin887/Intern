package com.codedynamix.pottyari.Battle;

import com.codedynamix.pottyari.Object.EnemyStatus;
import com.codedynamix.pottyari.Object.HeroStatus;

import java.util.Random;

public class BattleSystem
{
    public static int playerAttack()
    {
        Random random = new Random();
        int hAt,eDef;
        hAt = HeroStatus.getAttack();
        eDef = EnemyStatus.getEnemyDef();

        int damage = (hAt * 3 - eDef * 4) + (random.nextInt(7) - 3);

        if(damage > EnemyStatus.getEnemyHp())
            damage = EnemyStatus.getEnemyHp();
        if(damage <= 0)
            damage = 1;
        return damage;
    }

    public static int enemyAttack()
    {
        Random random = new Random();
        int eAt;
        eAt = EnemyStatus.getEnemyAt();

        int damage = eAt / 2 + (random.nextInt(3) - 1);

        //体力がマイナスいかないように調整
        if(HeroStatus.getHp() < damage)
            damage = HeroStatus.getHp();

        return damage;
    }

    public static int playerHeal()
    {
        int heal = HeroStatus.getMaxHp() / 2;
        if(HeroStatus.getHp() + heal > HeroStatus.getMaxHp())
            heal = HeroStatus.getMaxHp() - HeroStatus.getHp();

        //回復するので1つ消費
        HeroStatus.setHealCnt(HeroStatus.getHealCnt() - 1);
        return heal;
    }

    public static int playerResurrection()
    {
        float hp = HeroStatus.getMaxHp() * 0.3f;

        //蘇生するので1つ消費
        HeroStatus.setReviveCnt(HeroStatus.getReviveCnt() - 1);
        return (int)hp;
    }

    public static void playerGrow()
    {
        HeroStatus.setLv(HeroStatus.getLv() + 1);

        if(HeroStatus.getLv() >= 80)
        {
            HeroStatus.setHp((int)((float)HeroStatus.getHp() * 1.03f));
            HeroStatus.setAt((int)((float)HeroStatus.getAt() * 1.02f));

            //99レベルの時にhpを999にしたいという謎のこだわり
            if(HeroStatus.getLv() == 99)
                HeroStatus.setHp((int)((float)HeroStatus.getHp() + 5));
        }
        else if(HeroStatus.getLv() >= 40)
        {
            HeroStatus.setHp((int)((float)HeroStatus.getHp() * 1.02f));
            HeroStatus.setAt((int)((float)HeroStatus.getAt() * 1.02f));
        }
        else if(HeroStatus.getLv() >= 25)
        {
            HeroStatus.setHp((int)((float)HeroStatus.getHp() * 1.04f));
            HeroStatus.setAt((int)((float)HeroStatus.getAt() * 1.05f));
        }
        else if(HeroStatus.getLv() >= 10)
        {
            HeroStatus.setHp((int)((float)HeroStatus.getHp() * 1.07f));
            HeroStatus.setAt((int)((float)HeroStatus.getAt() * 1.1f));
        }
        else
        {
            HeroStatus.setHp((int)((float)HeroStatus.getHp() * 1.1f));
            HeroStatus.setAt((int)((float)HeroStatus.getAt() * 1.2f));
        }

    }

    public static void weaponGrow()
    {
        HeroStatus.setWeaponLv(HeroStatus.getWeaponLv() + 1);
        HeroStatus.setWp((int)((float)HeroStatus.getWp() + 3));
    }

    public static void enemyGrow()
    {
        EnemyStatus.setEnemyDeadCount(EnemyStatus.getEnemyDeadCount() + 1);
    }
}
