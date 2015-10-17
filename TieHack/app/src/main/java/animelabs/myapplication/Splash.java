package animelabs.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import io.saeid.fabloading.LoadingView;

/**
 * Created by Asheesh on 10/17/2015.
 */
public class Splash extends Activity {
    private LoadingView mLoadingView;
    private LoadingView mLoadViewLong;
    private LoadingView mLoadViewNoRepeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        mLoadingView = (LoadingView) findViewById(R.id.loading_view_repeat);
        boolean isLollipop = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
        int marvel_1 = isLollipop ? R.drawable.icon1 : R.drawable.icon1;
        int marvel_2 = isLollipop ? R.drawable.icon2 : R.drawable.icon2;
        int marvel_3 = isLollipop ? R.drawable.icon3 : R.drawable.icon3;
        int marvel_4 = isLollipop ? R.drawable.icon4 : R.drawable.icon4;
        int marvel_5 = isLollipop ? R.drawable.icon5 : R.drawable.icon5;
        int marvel_6 = isLollipop ? R.drawable.icon6 : R.drawable.icon6;

        mLoadingView.addAnimation(Color.parseColor("#FFD200"), marvel_1,
                LoadingView.FROM_LEFT);
        mLoadingView.addAnimation(Color.parseColor("#2F5DA9"), marvel_2,
                LoadingView.FROM_TOP);
        mLoadingView.addAnimation(Color.parseColor("#FF4218"), marvel_3,
                LoadingView.FROM_RIGHT);
        mLoadingView.addAnimation(Color.parseColor("#C7E7FB"), marvel_4,
                LoadingView.FROM_BOTTOM);
        mLoadingView.addAnimation(Color.parseColor("#C7E7FB"), marvel_5,
                LoadingView.FROM_TOP);
        mLoadingView.addAnimation(Color.parseColor("#C7E7FB"), marvel_6,
                LoadingView.FROM_RIGHT);

        mLoadingView.addListener(new LoadingView.LoadingListener() {
            @Override
            public void onAnimationStart(int currentItemPosition) {

            }

            @Override
            public void onAnimationRepeat(int nextItemPosition) {

            }

            @Override
            public void onAnimationEnd(int nextItemPosition) {

            }
        });
        mLoadingView.setRepeat(8);
        mLoadingView.startAnimation();
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(3800);
                } catch (InterruptedException e) {
                    e.setStackTrace(getStackTrace());
                } finally {
                    Intent i = new Intent(Splash.this, Home.class);
                    startActivity(i);
                    finish();
                }

            }
        };
        timer.start();

    }

}
