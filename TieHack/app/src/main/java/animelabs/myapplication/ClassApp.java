package animelabs.myapplication;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by Asheesh on 10/17/2015.
 */
public class ClassApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "vVuwDZTdbaqkUyLTJ35idJwYgGnXxoNLri6wavSa", "ChIii7eJLM2xhMbFnxkiuOv70TjnJPjeaS4216PK");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
