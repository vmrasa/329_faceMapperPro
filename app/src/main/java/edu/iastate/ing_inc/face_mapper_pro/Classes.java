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

       // Get Classes that user teaches
       ParseUser user = ParseUser.getCurrentUser();
       ParseQuery<ParseObject> classes = new ParseQuery<ParseObject>("Class");
       classes.whereContains("teacherID", user.getObjectId());
       classes.findInBackground(new FindCallback<ParseObject>() {
           @Override
           public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (int i=0; i < objects.size(); i++)
                        classesArrList.add(objects.get(i).getString("name"));
                } else {
                    Toast.makeText(getApplicationContext(), "Error on search",
                            Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
           }
       });

       // Add classes that user is a student for without duplicating
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

       //This arraylist should come from parse
//        classesArrList.add("CPRE 185");
//        classesArrList.add("ENG 314");
//        classesArrList.add("ComS 365");
//        classesArrList.add("MATH 215");
//        classesArrList.add("SE 654");
       //end of Parse List
       generateList( classesArrList);
   }


   private void generateList(ArrayList  classes)
   {
       ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classes );

       classesList.setAdapter(arrayAdapter);

   }
}

