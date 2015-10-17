package animelabs.myapplication;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Asheesh on 10/17/2015.
 */
public class CustomBroadcastReciever extends ParsePushBroadcastReceiver {
     @Override
    protected void onPushOpen(Context context, Intent intent) {
        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(intent.getStringExtra("com.parse.Data"));
            String title=jsonObject.getString("title").toString().trim();
            Log.d("String",title);
            if(title.equals("Flipkart")) {
                String url = "http://www.flipkart.com/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setData(Uri.parse(url));
                context.startActivity(i);

            }
            else if(title.equals("Snapdeal"))
            {
                String url = "http://www.snapdeal.com/offers/diwali-offers?&MID=42289|web|platinum|1||HourlyDeals|Electronics|HourlySale|ElectronicsHourlyDeals&";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
            else if(title.equals("Amazon"))
            {
                String url = "http://www.amazon.in/b/ref=br_imp_ara-1?_encoding=UTF8&node=3419926031&pf_rd_m=A1VBAL9TL5WCBF&pf_rd_s=desktop-hero-kindle-A&pf_rd_r=0SQPEK41VJS18RJWD495&pf_rd_t=36701&pf_rd_p=726411027&pf_rd_i=desktop";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
