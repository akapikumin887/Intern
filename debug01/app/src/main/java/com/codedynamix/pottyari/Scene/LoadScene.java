package com.codedynamix.pottyari.Scene;
import android.content.Intent;
import android.opengl.GLSurfaceView;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.BaseClass.BaseScene;
import com.codedynamix.pottyari.Progress.ProgressHero;
import com.codedynamix.pottyari.System.StService;
import com.codedynamix.pottyari.System.load;

import static androidx.core.content.ContextCompat.startForegroundService;

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
        GameActivity.load();


        //サービスが始まっていなければ起動する
        Intent intent = GameActivity.getIntent();
        if(intent == null)
        {
            intent = new Intent(GameActivity.getActivity().getApplication(), StService.class);
            startForegroundService(GameActivity.getCntxt(),intent);
            GameActivity.setIntent(intent);
        }

        //サービスが始まっているかの確認を行いたい
//        Intent inte = new Intent(GameActivity.getActivity().getApplication(), load.class);
//        startForegroundService(GameActivity.getCntxt(),inte);
    }

    @Override
    public void draw()
    {
        //anim.draw();
    }

    @Override
    public void update()
    {
        count++;
        //anim.update();
        if(GameActivity.getIsLoad())
        {
            Intent intent = new Intent(GameActivity.getActivity().getApplication(), load.class);
            GameActivity.getCntxt().stopService(intent);

            BaseScene.setnextScene(new HomeScene());
        }
    }
}
