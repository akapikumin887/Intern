package com.codedynamix.pottyari.UI;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.BaseClass.Object;
import com.codedynamix.pottyari.R;
import com.codedynamix.pottyari.System.NewEnter;
import com.codedynamix.pottyari.System.Texture;
import com.codedynamix.pottyari.System.Vector2;

public class Information extends Object
{
    private static Texture[] texture;
    private int touchCnt;

//マクロ定義
    private static final int TEXTURE_MAX = 7;

    public Information()
    {
        super();
        setLayer(Layer.LAYER_MESSAGE);
        setSize(new Vector2(GameActivity.getBaseWid(),GameActivity.getBaseHei()));

        touchCnt = 0;
    }

    public void draw(int num)
    {
        //エラー処理
        for(int i = 0; i < TEXTURE_MAX; i++)
        {
            if(texture[i] == null)
                return;
        }

        switch(num)
        {
            case 0: //Home
                if(!NewEnter.getInformScene(0))
                    return;
                switch (touchCnt)
                {
                    case 0:
                        texture[0].draw(pos,size,rotate,reverse,new Vector2(),new Vector2(1.0f,1.0f),color);
                        break;
                    case 1:
                        texture[1].draw(pos,size,rotate,reverse,new Vector2(),new Vector2(1.0f,1.0f),color);
                        break;
                    case 2:
                        texture[2].draw(pos,size,rotate,reverse,new Vector2(),new Vector2(1.0f,1.0f),color);
                        break;
                    case 3:
                        texture[3].draw(pos,size,rotate,reverse,new Vector2(),new Vector2(1.0f,1.0f),color);
                        break;
                    case 4:
                        NewEnter.setInformScene(0,false);
                        break;
                }
                break;
            case 1: //Status
                if(!NewEnter.getInformScene(1))
                    return;
                switch (touchCnt)
                {
                    case 0:
                        texture[4].draw(pos,size,rotate,reverse,new Vector2(),new Vector2(1.0f,1.0f),color);
                        break;
                    case 1:
                        NewEnter.setInformScene(1,false);
                        break;
                }
                break;
            case 2: //Shop
                if(!NewEnter.getInformScene(2))
                    return;
                switch (touchCnt)
                {
                    case 0:
                        texture[5].draw(pos,size,rotate,reverse,new Vector2(),new Vector2(1.0f,1.0f),color);
                        break;
                    case 1:
                        NewEnter.setInformScene(2,false);
                        break;
                }
                break;
            case 3: //Progress
                if(!NewEnter.getInformScene(3))
                    return;
                switch (touchCnt)
                {
                    case 0:
                        texture[6].draw(pos,size,rotate,reverse,new Vector2(),new Vector2(1.0f,1.0f),color);
                        break;
                    case 1:
                        NewEnter.setInformScene(3,false);
                        break;
                }
                break;
        }
    }

    @Override
    public void draw()
    {
        //エラー処理
        for(int i = 0; i < TEXTURE_MAX; i++)
        {
            if(texture[i] == null)
                return;
        }

        switch (touchCnt)
        {
            case 0:
                texture[0].draw(pos,size,rotate,reverse,new Vector2(),new Vector2(1.0f,1.0f),color);
                break;
            case 1:
                texture[1].draw(pos,size,rotate,reverse,new Vector2(),new Vector2(1.0f,1.0f),color);
                break;
            case 2:
                texture[2].draw(pos,size,rotate,reverse,new Vector2(),new Vector2(1.0f,1.0f),color);
                break;
            case 3:
                texture[3].draw(pos,size,rotate,reverse,new Vector2(),new Vector2(1.0f,1.0f),color);
                break;
            case 4:
                texture[4].draw(pos,size,rotate,reverse,new Vector2(),new Vector2(1.0f,1.0f),color);
                break;
            case 5:
                texture[5].draw(pos,size,rotate,reverse,new Vector2(),new Vector2(1.0f,1.0f),color);
                break;
            case 6:
                texture[6].draw(pos,size,rotate,reverse,new Vector2(),new Vector2(1.0f,1.0f),color);
                break;
        }
    }

    public static void loadTexture()
    {
        texture = new Texture[TEXTURE_MAX];
        for(int i = 0; i < TEXTURE_MAX; i++)
            texture[i] = new Texture();

        texture[0].loadTexture(GameActivity.getCntxt(), R.drawable.informstatus);
        texture[1].loadTexture(GameActivity.getCntxt(), R.drawable.informstepcount);
        texture[2].loadTexture(GameActivity.getCntxt(), R.drawable.informstop);
        texture[3].loadTexture(GameActivity.getCntxt(), R.drawable.informtravel);
        texture[4].loadTexture(GameActivity.getCntxt(), R.drawable.informlvup);
        texture[5].loadTexture(GameActivity.getCntxt(), R.drawable.informshop);
        texture[6].loadTexture(GameActivity.getCntxt(), R.drawable.informtravel2);

    }

    public void addTouch(){touchCnt++;}
    public int getTouchCnt(){return touchCnt;}
}
