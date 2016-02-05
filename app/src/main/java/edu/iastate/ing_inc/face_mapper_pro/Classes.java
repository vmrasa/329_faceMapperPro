package edu.iastate.ing_inc.face_mapper_pro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class Classes extends AppCompatActivity {

   private ListView classesList;
   private ArrayList<String> classesArrList = new ArrayList<String>();
    private ArrayAdapter<String> arrayAdapter;


   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_classes);


       classesList = (ListView) findViewById(R.id.classes_list);
       classesList.setOnItemClickListener(new ListView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> a, View v, int i, long l) {
               /* //This is where the next intent will be called
               Intent mainIntent = new Intent(Settings.this,
                       Register.class);
               startActivity(mainIntent);
               */
           }
       });

       // Add classes that user is a student for without duplicating
       ParseUser user = ParseUser.getCurrentUser();
       List<String> studentClasses = user.getList("relevantClasses");
       for (int i=0; i<studentClasses.size(); i++) {
           boolean alreadyAdded = false;
           String thisClass = studentClasses.get(i);
           for (int j=0; j<classesArrList.size(); j++) {
               if (thisClass.equals(classesArrList.get(j))) {
                   alreadyAdded = true;
                   break;
               }
           }
           if (!alreadyAdded)
               classesArrList.add(thisClass);
       }

       // Get Classes that user teaches
       ParseQuery<ParseObject> classes = new ParseQuery<ParseObject>("Class");
       classes.whereContains("teacherID", user.getObjectId());
       classes.findInBackground(new FindCallback<ParseObject>() {
           @Override
           public void done(List<ParseObject> objects, ParseException e) {
               if (e == null) {
                   for (int i = 0; i < objects.size(); i++)
                       classesArrList.add(objects.get(i).getString("name"));

                   generateList(classesArrList);
               } else {
                   Toast.makeText(getApplicationContext(), "Error on search",
                           Toast.LENGTH_SHORT).show();
                   e.printStackTrace();
               }
           }
       });
   }

   private void generateList(ArrayList  classes)
   {
       arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classesArrList );

       classesList.setAdapter(arrayAdapter);

       classesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String classCode = classesArrList.get(position);

               Intent intent = new Intent(Classes.this, Attendance.class);
//               intent.putExtra("classCode", classCode);
               startActivity(intent);
           }
       });
   }
}

