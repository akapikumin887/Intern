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
        //EnemyStatus.setEnemyHp(EnemyStatus.getEnemyHp() - damage);
        return damage;
    }

    public static int enemyAttack()
    {
        Random random = new Random();
        int eAt;
        eAt = EnemyStatus.getEnemyAt();

        int damage = eAt / 2 + (random.nextInt(3) - 1);

        //HeroStatus.setHp(HeroStatus.getHp() - damage);
        return damage;
    }

    public static int playerHeal()
    {
        int heal = HeroStatus.getMaxHp() / 2;
        if(HeroStatus.getHp() + heal > HeroStatus.getMaxHp())
            heal = HeroStatus.getMaxHp() - HeroStatus.getHp();

        //回復するので1つ消費 このままだと2つ使う
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
        HeroStatus.setHp((int)((float)HeroStatus.getHp() * 1.1f));
        HeroStatus.setAt((int)((float)HeroStatus.getAt() * 1.2f));
    }

    public static void weaponGrow()
    {
        HeroStatus.setWeaponLv(HeroStatus.getWeaponLv() + 1);
        HeroStatus.setWp((int)((float)HeroStatus.getWp() * 1.1f));
    }

    public static void enemyGrow()
    {
        EnemyStatus.setEnemyDeadCount(EnemyStatus.getEnemyDeadCount() + 1);
    }
}
