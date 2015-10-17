package animelabs.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asheesh on 10/17/2015.
 */
public class EnterReview extends Fragment {
    Button ib;
    EditText e1,e2,e3;
    ImageButton ib1;
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    private static String url_new_user = "http://tiehbackrevieww.co.nf/allreviews.php";
    private static final String TAG_SUCCESS = "success";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.enterreview,container,false);
        ib=(Button)view.findViewById(R.id.btnReg);
        e1=(EditText)view.findViewById(R.id.email);
        e2=(EditText)view.findViewById(R.id.user);
        e3=(EditText)view.findViewById(R.id.pwd);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ProdNew().execute();
            }
        });
        return view;
    }

    class ProdNew extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Adding Your Valuable Review");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
//            MainActivity ma=new MainActivity();
//            LatLng latLng=ma.getloca();
//            longitude=latLng.longitude;
//            latitude=latLng.latitude;

            String tid= e1.getText().toString();
            String item= e2.getText().toString();
            String desc=e3.getText().toString();

            //String Username = e3.getText().toString();


            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("item", item));
            params.add(new BasicNameValuePair("desc", desc));
            params.add(new BasicNameValuePair("tid", tid));
            //params.add(new BasicNameValuePair("username", Username));
            // getting JSON Object
            // Note that create product url accepts POST method
            String json = jsonParser.makeHttpRequest(url_new_user,
                    "POST", params);

            // check log cat from response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                JSONObject job=new JSONObject(json.substring(json.indexOf("{"), json.lastIndexOf("}") + 1));
                int success = job.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created a user
                    Intent i = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                    startActivity(i);

                } else {
                    // failed to create user
                    Log.d("failed to create user", json.toString());

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            if(pDialog!=null)
                pDialog.dismiss();
        }

    }
}
