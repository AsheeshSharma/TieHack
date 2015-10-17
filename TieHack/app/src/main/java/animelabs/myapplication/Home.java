package animelabs.myapplication;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

/**
 * Created by Asheesh on 10/17/2015.
 */
public class Home extends Activity {
    ImageButton b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        b1=(ImageButton)findViewById(R.id.imageButton);
        b2=(ImageButton)findViewById(R.id.imageButton2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(b1, View.TRANSLATION_X, 600);
                rotateAnimator.setRepeatCount(1);
                rotateAnimator.setRepeatMode(ValueAnimator.REVERSE);
                rotateAnimator.setDuration(600);
                rotateAnimator.start();
                Thread timer = new Thread() {
                    public void run() {
                        try {
                            sleep(400);
                        } catch (InterruptedException e) {
                            e.setStackTrace(getStackTrace());
                        } finally {
                            Intent i = new Intent(Home.this, Login.class);
                            startActivity(i);
                        }

                    }
                };
                timer.start();

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(b2, View.TRANSLATION_X, 100);
                rotateAnimator.setRepeatCount(1);
                rotateAnimator.setRepeatMode(ValueAnimator.REVERSE);
                rotateAnimator.setDuration(600);
                rotateAnimator.start();
                Thread timer = new Thread() {
                    public void run() {
                        try {
                            sleep(400);
                        } catch (InterruptedException e) {
                            e.setStackTrace(getStackTrace());
                        } finally {
                            Intent i = new Intent(Home.this, Register.class);
                            startActivity(i);
                        }

                    }
                };
                timer.start();
            }
        });
    }
}
