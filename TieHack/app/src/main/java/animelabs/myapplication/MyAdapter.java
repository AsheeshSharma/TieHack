package animelabs.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Asheesh on 10/17/2015.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

private static final int TYPE_HEADER = 0;

private static final int TYPE_ITEM = 1;

private String mNavTitles[];
private int mIcons[];
private String mdata[];

public static class ViewHolder extends RecyclerView.ViewHolder {
    int Holderid;

    TextView textView,textView2;
    ImageView imageView;


    public ViewHolder(View itemView,int ViewType) {
        super(itemView);


        if(ViewType == TYPE_ITEM) {
            textView = (TextView) itemView.findViewById(R.id.rowText);
            imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
            textView2=(TextView)itemView.findViewById(R.id.textView5);
            Holderid = 1;
        }
        else{
            Holderid = 0;
        }
    }


}



    MyAdapter(String Titles[],int Icons[],String Data[]){

        mNavTitles = Titles;
        mIcons = Icons;
        mdata=Data;

    }




    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);

            ViewHolder vhItem = new ViewHolder(v,viewType);

            return vhItem;



        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header,parent,false);

            ViewHolder vhHeader = new ViewHolder(v,viewType);

            return vhHeader;


        }
        return null;

    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        if(holder.Holderid ==1) {
            holder.textView.setText(mNavTitles[position - 1]);
            holder.imageView.setImageResource(mIcons[position -1]);
            holder.textView2.setText(mdata[position-1]);
        }
        else{


        }
    }

    @Override
    public int getItemCount() {
        return mNavTitles.length+1;

    }



    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

}