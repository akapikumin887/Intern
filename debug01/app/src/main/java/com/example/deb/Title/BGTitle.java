package com.example.deb.Title;

import com.example.deb.BaseClass.Object;
import com.example.deb.System.TextureInfo;

public class BGTitle extends Object
{
    public BGTitle(TextureInfo info)
    {
        //マルチスレッドでぶん投げ隊

        position = new Vector2();
        texInfo = info;
    }

}
