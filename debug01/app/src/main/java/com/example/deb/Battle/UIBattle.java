package com.example.deb.Battle;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.System.Vector2;
import com.example.deb.UI.Enemy;
import com.example.deb.UI.HpBar;
import com.example.deb.UI.MessageWindow;

public class UIBattle extends Object
{
    private MessageWindow window;
    BattleCommand attack;
    BattleCommand heal;

    private HpBar redBar;
    private HpBar greenBar;

    private Enemy boss;

    private float phoneLeftWidth;

    public UIBattle()
    {
        super();
        setLayer(Layer.LAYER_BUTTON);
        phoneLeftWidth = -GameActivity.getBaseWid() / 2;

        window = new MessageWindow(4);
        attack = new BattleCommand(0);
        attack.setPosition(new Vector2(-attack.getSize().x / 2 - 50.0f,window.getPosition().y + window.getSize().y / 2 + attack.getSize().y / 1.5f));
        heal   = new BattleCommand(1);
        heal.setPosition(new Vector2(attack.getSize().x / 2 + 50.0f,window.getPosition().y + window.getSize().y / 2 + attack.getSize().y / 1.5f));

        float num = 0.05f;

        redBar = new HpBar(1);
        greenBar = new HpBar(0);
        greenBar.setSize(new Vector2(GameActivity.getBaseWid() * num, greenBar.getSize().y));
        greenBar.setPosition(new Vector2(phoneLeftWidth + greenBar.getSize().x * 0.5f, greenBar.getPosition().y));
        greenBar.setTexSize(new Vector2( num,0.3333f));

        boss = new Enemy(2);
    }

    @Override
    public void update()
    {
        boss.update();
    }

    @Override
    public void draw()
    {
        window.draw();
        attack.draw();
        heal.draw();

        boss.draw();

        redBar.draw();
        greenBar.draw();
    }
}
