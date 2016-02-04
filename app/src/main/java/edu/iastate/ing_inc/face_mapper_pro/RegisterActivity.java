package edu.iastate.ing_inc.face_mapper_pro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.ArrayList;
import java.util.Arrays;

public class RegisterActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword1;
    private EditText etPassword2;

    private CheckBox cbCS309;
    private CheckBox cbCS311;
    private CheckBox cbCS329;
    private CheckBox cbCS330;
    private CheckBox cbCS339;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        gatherElements();
    }

    private void gatherElements() {
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword1 = (EditText) findViewById(R.id.et_password);
        etPassword2 = (EditText) findViewById(R.id.et_confirm_password);

        cbCS309 = (CheckBox) findViewById(R.id.cb_cs309);
        cbCS311 = (CheckBox) findViewById(R.id.cb_cs311);
        cbCS329 = (CheckBox) findViewById(R.id.cb_cs329);
        cbCS330 = (CheckBox) findViewById(R.id.cb_cs330);
        cbCS339 = (CheckBox) findViewById(R.id.cb_cs339);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onRegisterClick(View view) {
        String username = etUsername.getText().toString();
        String password1 = etPassword1.getText().toString();
        String password2 = etPassword2.getText().toString();

        if(!username.equals(""))
            if(!password1.equals(""))
                if(password2.equals(password1))
                    registerUser(username, password1);
                else // password2 doesn't match password 1
                    Toast.makeText(getApplicationContext(), "Passwords do not match",
                            Toast.LENGTH_SHORT).show();
            else // password 1 empty
                Toast.makeText(getApplicationContext(), "You must enter a password",
                        Toast.LENGTH_SHORT).show();
        else // username empty
            Toast.makeText(getApplicationContext(), "You must enter a username",
                    Toast.LENGTH_SHORT).show();

    }

    private void registerUser(String username, String password) {
        ParseUser user = new ParseUser();
        user.put("relevantClasses", Arrays.asList(getClasses()));
        user.setPassword(password);
        user.setUsername(username);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Intent intent = new Intent(RegisterActivity.this, NotImplemented.class);
                    startActivityForResult(intent, 0);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Register Error", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    private String[] getClasses() {
        ArrayList<String> classes = new ArrayList<String>();
        if (cbCS309.isChecked())
            classes.add("cs309");

        if (cbCS311.isChecked())
            classes.add("cs311");

        if (cbCS329.isChecked())
            classes.add("cs329");

        if (cbCS330.isChecked())
            classes.add("cs330");

        if (cbCS339.isChecked())
            classes.add("cs339");

        String[] retArray = new String[classes.size()];
        retArray = classes.toArray(retArray);
        return retArray;
    }

    public void onTakePictureCick(View view) {
        // TODO implement camera
    }
}
