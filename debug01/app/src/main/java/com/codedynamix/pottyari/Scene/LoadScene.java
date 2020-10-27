package com.codedynamix.pottyari.Scene;

import android.opengl.GLSurfaceView;

import com.codedynamix.pottyari.BaseClass.BaseScene;
import com.codedynamix.pottyari.Progress.ProgressHero;
import com.codedynamix.pottyari.System.NowLoading;

public class LoadScene extends BaseScene
{
    private ProgressHero anim;
    private boolean isFinith;
    private int count;

    //別スレッド処理にはなっているように見えるが、順番が存在していてなんかおかしい
    public LoadScene(GLSurfaceView surface)
    {
        isFinith = false;
        count = 0;
        //別スレッドでテクスチャロードをやっておき、その間にアニメーションを垂れ流して置く
        NowLoading nld = new NowLoading();
        surface.queueEvent(nld);

        anim = new ProgressHero();
        list.add(anim);
    }

    @Override
    public void draw()
    {
        anim.draw();
    }

    @Override
    public void update()
    {
        count++;
        anim.update();
        if(NowLoading.getIsLoad())
            BaseScene.setnextScene(new HomeScene());
    }
}
