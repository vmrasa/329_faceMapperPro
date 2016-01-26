package edu.iastate.ing_inc.face_mapper_pro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Login extends AppCompatActivity {
    private EditText et_username;
    private EditText et_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        gatherElements();
    }

    private void gatherElements(){
        et_password = (EditText) findViewById(R.id.password);
        et_username = (EditText) findViewById(R.id.username);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    public void loginClick(View view) {
        String username = et_username.getText().toString();
        String password = et_password.getText().toString();
        if(!(username.equals("") || password.equals("")))
            attemptLogin(username, password);
    }

    private void attemptLogin(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    Intent intent = new Intent(Login.this, NotImplemented.class);
                    startActivityForResult(intent, 0);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "No user exists with that combination of username and password",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void registerClick(View view) {
        Intent intent = new Intent(this, NotImplemented.class);
        startActivity(intent);
    }
}
