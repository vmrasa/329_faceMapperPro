package edu.iastate.ing_inc.face_mapper_pro;

//import static org.bytedeco.javacpp.opencv_face.*;
//import static org.bytedeco.javacpp.opencv_core.*;
//import static org.bytedeco.javacpp.opencv_imgcodecs.*;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;

    private ImageView imageView;

    int photoButtonPressed;

    Bitmap photo1;
    Bitmap photo2;
    Bitmap photo3;

    private EditText etUsername;
    private EditText etPassword1;
    private EditText etPassword2;

    private CheckBox cbCS309;
    private CheckBox cbCS319;
    private CheckBox cbSE329;
    private CheckBox cbCS330;
    private CheckBox cprE339;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        gatherElements();

        this.imageView = (ImageView) this.findViewById(R.id.imageView1);

        Button photoButton1 = (Button) this.findViewById(R.id.pictureButton1);

        photoButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoButtonPressed = 1;
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        Button photoButton2 = (Button) this.findViewById(R.id.pictureButton2);

        photoButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoButtonPressed = 2;
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        Button photoButton3 = (Button) this.findViewById(R.id.pictureButton3);

        photoButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoButtonPressed = 3;
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
    }

    private void gatherElements() {
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword1 = (EditText) findViewById(R.id.et_password);
        etPassword2 = (EditText) findViewById(R.id.et_confirm_password);

        cbCS309 = (CheckBox) findViewById(R.id.cb_cs309);
        cbCS319 = (CheckBox) findViewById(R.id.cb_cs319);
        cbSE329 = (CheckBox) findViewById(R.id.cb_se329);
        cbCS330 = (CheckBox) findViewById(R.id.cb_cs330);
        cprE339 = (CheckBox) findViewById(R.id.cb_cpre339);
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

        /** OpenCV Logic
         *  Couldn't get 100% working
         * 
         * FaceRecognizer faceRecognizer = createFisherFaceRecognizer();
         * 
         * bitmap[] images = {photo1, photo2, photo3};
         * 
         * faceRecognizer.train(images);
         */
        
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    signUpForClasses();
                    Intent intent = new Intent(RegisterActivity.this, Classes.class);
                    startActivityForResult(intent, 0);
                    finish();
                } else if (e.getCode() == ParseException.USERNAME_TAKEN) {
                    Toast.makeText(getApplicationContext(), "Username Taken", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } else {
                    Toast.makeText(getApplicationContext(), "Register Error", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    private void signUpForClasses() {
        if (cbCS309.isChecked())
            addStudentToClass("COMS309");

        if (cbCS319.isChecked())
            addStudentToClass("COMS319");

        if (cbSE329.isChecked())
            addStudentToClass("SE329");

        if (cbCS330.isChecked())
            addStudentToClass("COMS330");

        if (cprE339.isChecked())
            addStudentToClass("CPRE339");
    }

    private String[] getClasses() {
        ArrayList<String> classes = new ArrayList<String>();
        if (cbCS309.isChecked())
            classes.add("cs309");

        if (cbCS319.isChecked())
            classes.add("cs319");

        if (cbSE329.isChecked())
            classes.add("se329");

        if (cbCS330.isChecked())
            classes.add("cs330");

        if (cprE339.isChecked())
            classes.add("cprE339");

        String[] retArray = new String[classes.size()];
        retArray = classes.toArray(retArray);
        return retArray;
    }

    private void addStudentToClass(final String classCode) {
        ParseQuery<ParseObject> course = new ParseQuery<ParseObject>("Class");
        course.whereContains("name", classCode);
        course.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e == null) {
                    List<String> studentIDs = object.getList("studentIdList");
                    studentIDs.add(ParseUser.getCurrentUser().getObjectId());
                    object.put("studentIdList", studentIDs);
                    object.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e != null) {
                                Toast.makeText(getApplicationContext(),
                                        "Not added to class " + classCode,
                                        Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            if (photoButtonPressed == 1) {
                photo1 = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(photo1);

                Button photoButton1 = (Button) this.findViewById(R.id.pictureButton1);
                photoButton1.setText("Got it!");
            }
            else if (photoButtonPressed == 2) {
                photo2 = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(photo2);

                Button photoButton2 = (Button) this.findViewById(R.id.pictureButton2);
                photoButton2.setText("Got it!");
            }
            else {
                photo3 = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(photo3);

                Button photoButton3 = (Button) this.findViewById(R.id.pictureButton3);
                photoButton3.setText("Got it!");
            }

            photoButtonPressed = 0;
        }
    }
}
