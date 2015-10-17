package animelabs.myapplication;

import android.app.Fragment;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Asheesh on 10/17/2015.
 */
public class FragReviews extends ListActivity {
    private ProgressDialog pDialog;
    private static final String TAG_ITEM = "item";
    private static final String TAG_DESC = "desc";
    private static final String TAG_TID = "tid";

    String source,destination;
    TextView tv;
    ArrayList<HashMap<String, String>> matchFixtureList = new ArrayList<HashMap<String, String>>();
    int results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reviewss);
        ListView listView=getListView();
        new LoadAllMessages().execute();
        tv=(TextView)findViewById(R.id.textView11);


    }
    class LoadAllMessages extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(FragReviews.this);
            pDialog.setMessage("Loading... Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All messages from url
         * */
        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();

            String result = null;
            InputStream is = null;
            StringBuilder sb=null;


            //http post to get all shared messages
            try{

                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://tiehbackrevieww.co.nf/alldata.php");
                httppost.setEntity(new UrlEncodedFormEntity(params));
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                is=entity.getContent();
            }catch(Exception e){
                Log.e("Share", "Error in http connection" + e.toString());
            }

            //convert response to string
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
                sb = new StringBuilder();
                sb.append(reader.readLine() + "\n");
                String line="0";

                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }

                is.close();
                result=sb.toString();
                Log.d("adas",result);
            }catch(Exception e){
                Log.e("Share", "Error converting result "+ e.toString());
            }

            try {
                JSONObject jso=new JSONObject(result.substring(result.indexOf("{"), result.lastIndexOf("}") + 1));
                JSONArray matchFixture= jso.getJSONArray("products");
                //  log  JSON response
                Log.d("Share", "All messages: " + matchFixture.toString());
                Log.d("json aray", "user point array");
                int len = matchFixture.length();
                Log.d("len", "get array length");
                for (int i = 0; i < matchFixture.length(); i++) {
                    JSONObject c = matchFixture.getJSONObject(i);
                    String item = c.getString(TAG_ITEM);

                    String desc = c.getString(TAG_DESC);

                    String tid = c.getString(TAG_TID);
   //  hashmap for single match
                    HashMap<String, String> matchFixturec = new HashMap<String, String>();
                    // adding each child node to HashMap key => value
                    matchFixturec.put(TAG_ITEM, item);
                    matchFixturec.put(TAG_DESC, desc);
                    matchFixturec.put(TAG_TID, tid);
                    matchFixtureList.add(matchFixturec);
                }

            } catch (JSONException e1) {
                Log.e("Share", "Error in JSON Array" + e1.toString());
                e1.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            if(pDialog!=null)
            {
                pDialog.dismiss();}
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
                    tv.setText("Results Found");
                    ListAdapter adapter = new SimpleAdapter(
                           FragReviews.this, matchFixtureList,
                            R.layout.template, new String[] { TAG_ITEM,
                            TAG_DESC,TAG_TID},
                            new int[] { R.id.textView2, R.id.textView6,R.id.textView});
                    // updating listview
                    setListAdapter(adapter);
                }
            });

        }

    }

}