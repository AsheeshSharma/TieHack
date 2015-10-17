package animelabs.myapplication;

import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import java.util.List;

/**
 * Created by Asheesh on 10/17/2015.
 */
public class Frag_Tweet extends Fragment {
    ImageButton imageButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view=inflater.inflate(R.layout.tweet,container,false);
            imageButton=(ImageButton)view.findViewById(R.id.imageButton3);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startAnimationSplash();
                    Thread thread=new Thread()
                    {
                        public void run()
                        {
                            try{
                                sleep(1100);
                            }catch(InterruptedException e)
                            {
                                e.setStackTrace(getStackTrace());
                            }finally{

                                Intent tweetIntent = new Intent(Intent.ACTION_SEND);
                                tweetIntent.putExtra(Intent.EXTRA_TEXT, "Reviewing Just Got Easier! Follow @#review to know more. Stay Tuned!");
                                tweetIntent.setType("text/plain");

                                PackageManager packManager = getActivity().getPackageManager();
                                List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(tweetIntent,  PackageManager.MATCH_DEFAULT_ONLY);

                                boolean resolved = false;
                                for(ResolveInfo resolveInfo: resolvedInfoList){
                                    if(resolveInfo.activityInfo.packageName.startsWith("com.twitter.android")){
                                        tweetIntent.setClassName(
                                                resolveInfo.activityInfo.packageName,
                                                resolveInfo.activityInfo.name );
                                        resolved = true;
                                        break;
                                    }
                                }
                                if(resolved){

                                }else{
                                    tweetIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/"));
                                }
                                startActivity(tweetIntent);
                            }
                        }
                    };
                    thread.start();
                }
            });
            return  view;
    }
    private void startAnimationSplash() {
        Animation animation= AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.customanimation);
        animation.reset();
        animation.setFillAfter(true);
        imageButton.startAnimation(animation);
    }
}
