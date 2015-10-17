package animelabs.myapplication;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener  {
    static final String KEY_TITLE = "title";
    static final String KEY_PHOTOS = "photo";
    ArrayList<HashMap<String,String>> data;
    ListView listView;
    Toolbar toolbar;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    DrawerLayout Drawer;
    ActionBarDrawerToggle mDrawerToggle;
    String TITLES[] = {"Home","All Reviews","Write Review","All Tweets","Tweet","Profile"};
    int ICONS[] = {R.drawable.hme,R.drawable.rat,R.drawable.wrt,R.drawable.tf,R.drawable.bb,R.drawable.budi};
    String DATA[]={"23","43","54","334","667","23"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=(Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MyAdapter(TITLES,ICONS,DATA);
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnItemTouchListener(
                new RecycleItemClickListener(getApplicationContext(), new RecycleItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Fragment frag = null;
                        if (position == 1) {
                            frag=new Frag_Home();
                            FragmentManager frgManager = getFragmentManager();
                            frgManager.beginTransaction().replace(R.id.content_frame, frag)
                                    .commit();
                            view.setBackgroundResource(R.drawable.listcutom);
                            Drawer.closeDrawer(Gravity.LEFT);
                            mRecyclerView.getChildAt(2).setBackgroundResource(Color.TRANSPARENT);
                            mRecyclerView.getChildAt(3).setBackgroundResource(Color.TRANSPARENT);
                            mRecyclerView.getChildAt(4).setBackgroundResource(Color.TRANSPARENT);
                            mRecyclerView.getChildAt(5).setBackgroundResource(Color.TRANSPARENT);
                        }
                        if (position == 2) {
                            Intent i=new Intent(MainActivity.this,FragReviews.class);
                            startActivity(i);
                            view.setBackgroundResource(R.drawable.listcutom);
                            Drawer.closeDrawer(Gravity.LEFT);
                            mRecyclerView.getChildAt(1).setBackgroundResource(Color.TRANSPARENT);
                            mRecyclerView.getChildAt(3).setBackgroundResource(Color.TRANSPARENT);
                            mRecyclerView.getChildAt(4).setBackgroundResource(Color.TRANSPARENT);
                            mRecyclerView.getChildAt(5).setBackgroundResource(Color.TRANSPARENT);

                        }
                        if (position == 3) {

                            frag=new EnterReview();
                            FragmentManager frgManager = getFragmentManager();
                            frgManager.beginTransaction().replace(R.id.content_frame, frag)
                                    .commit();
                            Drawer.closeDrawer(Gravity.LEFT);
                            view.setBackgroundResource(R.drawable.listcutom);
                            mRecyclerView.getChildAt(1).setBackgroundResource(Color.TRANSPARENT);
                            mRecyclerView.getChildAt(2).setBackgroundResource(Color.TRANSPARENT);
                            mRecyclerView.getChildAt(4).setBackgroundResource(Color.TRANSPARENT);
                            mRecyclerView.getChildAt(5).setBackgroundResource(Color.TRANSPARENT);


                        }
                        if (position == 4) {
                            Intent i=new Intent(MainActivity.this,AllTweets.class);
                            startActivity(i);
                            Drawer.closeDrawer(Gravity.LEFT);
                            view.setBackgroundResource(R.drawable.listcutom);
                            mRecyclerView.getChildAt(1).setBackgroundResource(Color.TRANSPARENT);
                            mRecyclerView.getChildAt(3).setBackgroundResource(Color.TRANSPARENT);
                            mRecyclerView.getChildAt(2).setBackgroundResource(Color.TRANSPARENT);
                            mRecyclerView.getChildAt(5).setBackgroundResource(Color.TRANSPARENT);

                        }
                        if (position == 5) {
                            frag=new Frag_Tweet();
                            FragmentManager frgManager = getFragmentManager();
                            frgManager.beginTransaction().replace(R.id.content_frame, frag)
                                    .commit();
                            Drawer.closeDrawer(Gravity.LEFT);
                            view.setBackgroundResource(R.drawable.listcutom);
                            mRecyclerView.getChildAt(1).setBackgroundResource(Color.TRANSPARENT);
                            mRecyclerView.getChildAt(3).setBackgroundResource(Color.TRANSPARENT);
                            mRecyclerView.getChildAt(4).setBackgroundResource(Color.TRANSPARENT);
                            mRecyclerView.getChildAt(2).setBackgroundResource(Color.TRANSPARENT);

                        }
                    }
                })
        );
        data=new ArrayList<HashMap<String,String>>();
        // Spinner element

        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,toolbar,R.string.openDrawer,R.string.closeDrawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        Drawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        final ImageView leftLowerButton=new ImageView(getApplicationContext());
        leftLowerButton.setImageDrawable(getResources().getDrawable(R.drawable.share));
        final FloatingActionButton leftButton=new FloatingActionButton.Builder(this)
                .setContentView(leftLowerButton)
                .setPosition(FloatingActionButton.POSITION_BOTTOM_LEFT)
                .build();
        final SubActionButton.Builder builderr=new SubActionButton.Builder(this);
        SubActionButton facebook=makeSAB(R.drawable.facebook,getApplicationContext(),builderr);
        SubActionButton twitter=makeSAB(R.drawable.twit,getApplicationContext(),builderr);
        SubActionButton googleplus=makeSAB(R.drawable.googleplus,getApplicationContext(),builderr);
        FloatingActionMenu leftactionmenu=new FloatingActionMenu.Builder(this)
                .addSubActionView(facebook)
                .addSubActionView(twitter)
                .addSubActionView(googleplus)
                .setRadius(200)
                .setStartAngle(-90)
                .setEndAngle(0)
                .attachTo(leftButton)
                .build();
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tweetIntent = new Intent(Intent.ACTION_SEND);
                tweetIntent.putExtra(Intent.EXTRA_TEXT, "I used #Reviews to check and submit Reviews. Its Amazing!");
                tweetIntent.setType("text/plain");

                PackageManager packManager = getPackageManager();
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
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        leftactionmenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu floatingActionMenu) {
                leftLowerButton.setRotation(0);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 180);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(leftLowerButton, pvhR);
                animation.start();
            }

            @Override
            public void onMenuClosed(FloatingActionMenu floatingActionMenu) {
                leftLowerButton.setRotation(180);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 0);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(leftLowerButton, pvhR);
                animation.start();
            }
        });
    }
    private SubActionButton makeSAB (int resId, Context c, SubActionButton.Builder sBuilder) {
        ImageView icon = new ImageView(c);
        icon.setImageResource(resId);
        icon.setPadding(10,10,10,10);
        int mySubActionButtonSize = getResources().getDimensionPixelSize(R.dimen.my_sub_action_button_size);
        int mySubActionButtonContentMargin = getResources().getDimensionPixelSize(R.dimen.my_sub_action_button_content_margin);
        FrameLayout.LayoutParams newContentParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        newContentParams.setMargins(mySubActionButtonContentMargin,
                mySubActionButtonContentMargin,
                mySubActionButtonContentMargin,
                mySubActionButtonContentMargin);
        sBuilder.setLayoutParams(newContentParams);
        FrameLayout.LayoutParams newParams = new FrameLayout.LayoutParams(mySubActionButtonSize, mySubActionButtonSize);
        sBuilder.setLayoutParams(newParams);
        return sBuilder.setContentView(icon).build();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

}
