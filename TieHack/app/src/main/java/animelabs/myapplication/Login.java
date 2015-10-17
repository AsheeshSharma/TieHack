package animelabs.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
public class Login extends Activity {
    EditText e1,e2;
    ImageButton bt1;
    JSONParser jsonParser=new JSONParser();
    private static String url_new_user = "http://tiehbackrevieww.co.nf/signin.php";
    private ProgressDialog pDialog;
    private static final String TAG_SUCCESS = "success";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        e1=(EditText)findViewById(R.id.user);
        e2=(EditText)findViewById(R.id.pwd);
        bt1=(ImageButton)findViewById(R.id.btnLogin);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CreateProduct().execute();
            }
        });

    }
    private class CreateProduct extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String tid=e1.getText().toString();
            String pwd=e2.getText().toString();
            List<NameValuePair> prms=new ArrayList<NameValuePair>();
            prms.add(new BasicNameValuePair("tid",tid));
            prms.add(new BasicNameValuePair("pwd", pwd));
            String json=jsonParser.makeHttpRequest(url_new_user,"POST",prms);
            Log.d("Create Response", json);
            try {
                JSONObject job=new JSONObject(json.substring(json.indexOf("{"), json.lastIndexOf("}") + 1));
                int success=job.getInt(TAG_SUCCESS);
                if(success==1)
                {
                    Intent i=new Intent(Login.this,MainActivity.class);
                    startActivity(i);

                }
                else
                {
                    Log.d("Failed to Sign In", json.toString());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog=new ProgressDialog(Login.this);
            pDialog.setMessage("Signing In...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(pDialog!=null)
                pDialog.dismiss();
        }
    }
}
