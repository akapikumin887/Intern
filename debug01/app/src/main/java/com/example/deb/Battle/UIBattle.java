package com.example.deb.Battle;

import com.example.deb.BaseClass.Object;
import com.example.deb.UI.Enemy;
import com.example.deb.UI.MessageWindow;

public class UIBattle extends Object
{
    private MessageWindow window;

    private Enemy boss;

    public UIBattle()
    {
        super();
        setLayer(Layer.LAYER_BUTTON);
        window = new MessageWindow(4);

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
        boss.draw();
    }
}
