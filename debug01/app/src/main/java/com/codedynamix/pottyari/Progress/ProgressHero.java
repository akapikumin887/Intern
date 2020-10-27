package com.codedynamix.pottyari.Progress;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.BaseClass.Object;
import com.codedynamix.pottyari.R;
import com.codedynamix.pottyari.System.Texture;
import com.codedynamix.pottyari.System.Vector2;

public class ProgressHero extends Object
{
    private static Texture texture;
    private int animCounter;
    private Vector2 texAnim;

//マクロ定義
    private final Vector2 TEX_SIZE = new Vector2(0.25f,1.0f);
    private final int ANIM_CNT = 15;

    public ProgressHero()
    {
        super();
        setLayer(Layer.LAYER_CHARE);
        setSize(new Vector2(200.0f,200.0f));

        animCounter = 0;
        texAnim = new Vector2();
    }

    @Override
    public void update()
    {
        animCounter++;
        if(animCounter >= ANIM_CNT)
        {
            texAnim.x += 0.25f;
            if(texAnim.x >= 1.0f)
                texAnim.x = 0.0f;
            animCounter = 0;
        }
        super.update();
    }

    @Override
    public void draw()
    {
        if(texture != null)
            if(texture.texId != 0)
                texture.draw(pos,size,rotate,reverse, texAnim,TEX_SIZE,color);
    }

    public static void loadTexture()
    {
        texture = new Texture();
        texture.loadTexture(GameActivity.getCntxt(), R.drawable.player_animation);
    }


}
