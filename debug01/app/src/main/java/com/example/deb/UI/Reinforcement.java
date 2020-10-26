package com.example.deb.UI;

import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.System.Vector2;
import com.example.deb.System.Texture;

public class Reinforcement  extends Object
{
        private static Texture texture;

        private Vector2 texStartPoint;
        private Vector2 texSize;

        public  Reinforcement()
        {
            super();
            setLayer(Object.Layer.LAYER_BUTTON);

            setSize(new Vector2(  400.0f, 400.0f));
            setPosition(new Vector2());
            texStartPoint = new Vector2();
            texSize = new Vector2(1.0f, 1.0f);
        }


        @Override
        public void draw()
        {
            texture.draw(pos,size,rotate,reverse,texStartPoint,texSize,color);
        }

        public static void loadTexture()
        {
            texture = new Texture();
            texture.loadTexture(GameActivity.getCntxt(), R.drawable.reinforcement);
        }
}
