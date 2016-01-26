package edu.iastate.ing_inc.face_mapper_pro; /**
 * Created by Evan on 1/25/2016.
 */

import android.app.Application;

import com.parse.Parse;

public class MyApplication extends Application {
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this);
    }
}
