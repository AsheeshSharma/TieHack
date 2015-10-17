package animelabs.myapplication;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by Asheesh on 10/17/2015.
 */
public class Frag_Home extends Fragment {
    ImageButton imageButton1,imageButton2,imageButton3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.homee,container,false);
        imageButton1=(ImageButton)view.findViewById(R.id.ib1);
        imageButton2=(ImageButton)view.findViewById(R.id.ib2);
        imageButton3=(ImageButton)view.findViewById(R.id.ib3);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.snapdeal.com/offers/diwali-offers?&MID=42289|web|platinum|1||HourlyDeals|Electronics|HourlySale|ElectronicsHourlyDeals&";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.flipkart.com/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.amazon.in/b/ref=br_imp_ara-1?_encoding=UTF8&node=3419926031&pf_rd_m=A1VBAL9TL5WCBF&pf_rd_s=desktop-hero-kindle-A&pf_rd_r=0SQPEK41VJS18RJWD495&pf_rd_t=36701&pf_rd_p=726411027&pf_rd_i=desktop";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        return  view;
        }
}
