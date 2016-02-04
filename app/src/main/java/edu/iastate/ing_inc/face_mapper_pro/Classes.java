package edu.iastate.ing_inc.face_mapper_pro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;

public class Attendence extends AppCompatActivity {

   private ListView AttendenceList;
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


       //This arraylist should come from parse
        classesArrList.add("CPRE 185");
        classesArrList.add("ENG 314");
        classesArrList.add("ComS 365");
        classesArrList.add("MATH 215");
        classesArrList.add("SE 654");
       //end of Parse List
       generateList( classesArrList);
   }


   private void generateList(ArrayList  classesList)
   {
       ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classes );

       classesList.setAdapter(arrayAdapter);

   }
}

