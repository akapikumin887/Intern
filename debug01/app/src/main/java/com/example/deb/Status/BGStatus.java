package com.example.deb.Status;


import com.example.deb.Activity.GameActivity;
import com.example.deb.BaseClass.Object;
import com.example.deb.R;
import com.example.deb.System.Texture;

public class BGStatus extends Object
{
    private static Texture texture;


    public static void loadTexture()
    {
        texture = new Texture();
        texture.loadTexture(GameActivity.context, R.drawable.title);
    }
}
