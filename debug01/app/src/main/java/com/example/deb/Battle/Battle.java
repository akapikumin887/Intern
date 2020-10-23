package com.example.deb.Battle;

import android.view.MotionEvent;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.System.Vector2;
import com.example.deb.UI.BattleText;
import com.example.deb.UI.MessageWindow;

public class Battle extends Object
{
    private MessageWindow window;
    private BattleCommand attack;
    private BattleCommand heal;

    private BattleText firstText;
    private BattleText secondText;
    private BattleText thirdText;
    private BattleText fourthText;

    public Battle()
    {
        super();
        setLayer(Layer.LAYER_BUTTON);

        window = new MessageWindow(4);
        attack = new BattleCommand(0);
        attack.setPosition(new Vector2(-attack.getSize().x / 2 - 50.0f,window.getPosition().y + window.getSize().y / 2 + attack.getSize().y / 1.5f));
        heal   = new BattleCommand(1);
        heal.setPosition(new Vector2(attack.getSize().x / 2 + 50.0f,window.getPosition().y + window.getSize().y / 2 + attack.getSize().y / 1.5f));


        firstText = new BattleText(0);
        firstText.setPosition(new Vector2(firstText.getPosition().x,(window.getPosition().y + window.getSize().y / 2) - firstText.getSize().y * 1.5f));

        secondText = new BattleText(8);
        secondText.setPosition(new Vector2(secondText.getPosition().x,(window.getPosition().y + window.getSize().y / 2) - (secondText.getSize().y * 1.5f) * 2.0f));

        thirdText = new BattleText(2);
        thirdText.setPosition(new Vector2(thirdText.getPosition().x,(window.getPosition().y + window.getSize().y / 2) - (thirdText.getSize().y * 1.5f) * 3.0f));

        fourthText = new BattleText(5);
        fourthText.setPosition(new Vector2(fourthText.getPosition().x,(window.getPosition().y + window.getSize().y / 2) - (fourthText.getSize().y * 1.5f) * 4.0f));
    }

    @Override
    public void draw()
    {
        window.draw();
        attack.draw();
        heal.draw();

        firstText.draw();
        secondText.draw();
        thirdText.draw();
        fourthText.draw();
    }



    @Override
    public void touch(MotionEvent event)
    {
        //getXYと描画画面との誤差を修正
        Vector2 ratio = new Vector2(GameActivity.getBaseWid() / GameActivity.getSurfaceWid(),
                GameActivity.getBaseHei() / GameActivity.getSurfaceHei());

        Vector2 touchPos = new Vector2(event.getX() * ratio.x - GameActivity.getBaseWid() / 2,(-event.getY() * ratio.y + GameActivity.getBaseHei() / 2));

        if(event.getAction() == MotionEvent.ACTION_DOWN)    //trigger
        {
            //たたかう
            if(touchPos.x < attack.getPosition().x + attack.getSize().x / 2 && touchPos.x > attack.getPosition().x - attack.getSize().x / 2 &&
                    touchPos.y < attack.getPosition().y + attack.getSize().y / 2 && touchPos.y > attack.getPosition().y - attack.getSize().y / 2)
            {
//                stHp.setValue(stHp.getValue() - 3);
//                if(remnantsHp > 0.0f)
//                {
//                    remnantsHp -= 0.1f;
//                    greenBar.setSize(new Vector2(GameActivity.getBaseWid() * remnantsHp, greenBar.getSize().y));
//                    greenBar.setPosition(new Vector2(phoneLeftWidth + greenBar.getSize().x * 0.5f, greenBar.getPosition().y));
//                    greenBar.setTexSize(new Vector2( remnantsHp,0.3333f));
//
//                }
            }

            //回復
            if(touchPos.x < heal.getPosition().x + heal.getSize().x / 2 && touchPos.x > heal.getPosition().x - heal.getSize().x / 2 &&
                    touchPos.y < heal.getPosition().y + heal.getSize().y / 2 && touchPos.y > heal.getPosition().y - heal.getSize().y / 2)
            {
//                stHp.setValue(stHp.getValue() + 3);
//                if(remnantsHp < 1.0f)
//                {
//                    remnantsHp += 0.1f;
//                    greenBar.setSize(new Vector2(GameActivity.getBaseWid() * remnantsHp, greenBar.getSize().y));
//                    greenBar.setPosition(new Vector2(phoneLeftWidth + greenBar.getSize().x * 0.5f, greenBar.getPosition().y));
//                    greenBar.setTexSize(new Vector2( remnantsHp,0.3333f));
//
//                }
            }
        }

    }

}
