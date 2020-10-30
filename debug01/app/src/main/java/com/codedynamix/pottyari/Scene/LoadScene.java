package com.codedynamix.pottyari.Scene;
import android.content.Intent;
import android.opengl.GLSurfaceView;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.BaseClass.BaseScene;
import com.codedynamix.pottyari.System.StService;

import static androidx.core.content.ContextCompat.startForegroundService;

public class LoadScene extends BaseScene
{
//    private ProgressHero anim;
//    private boolean isFinith;
//    private int count;

    public LoadScene(GLSurfaceView surface)
    {
        //isFinith = false;
        //count = 0;
        GameActivity.load();


        //サービスが始まっていなければ起動する
        Intent intent = GameActivity.getIntent();
        if(intent == null)
        {
            intent = new Intent(GameActivity.getActivity().getApplication(), StService.class);
            startForegroundService(GameActivity.getCntxt(),intent);
            GameActivity.setIntent(intent);
        }
    }

    @Override
    public void draw()
    {
        //anim.draw();
    }

    @Override
    public void update()
    {
        //count++;
        //anim.update();
        if(GameActivity.getIsLoad())
            BaseScene.setnextScene(new HomeScene());
    }
}
