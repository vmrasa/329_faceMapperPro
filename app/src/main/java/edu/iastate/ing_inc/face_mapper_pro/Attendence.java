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
   private ArrayList<String> attendenceArrList = new ArrayList<String>();


   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_attendence);


       AttendenceList = (ListView) findViewById(R.id.attendence_list);


       //This arraylist should come from parse
       attendenceArrList.add("Bob Joe");
       attendenceArrList.add("Jane Doe");
       attendenceArrList.add("John Smith");
       attendenceArrList.add("Marry Jane");
       attendenceArrList.add("Bill Bones");
       attendenceArrList.add("Harry Doe");
       attendenceArrList.add("John Appleseed");
       attendenceArrList.add("Bri Jones");
       attendenceArrList.add("Terry Jones");
       attendenceArrList.add("Jane Doe");
       attendenceArrList.add("John Smith");
       attendenceArrList.add("Marry Jane");
       //end of Parse List
       generateList(attendenceArrList);
   }


   private void generateList(ArrayList attendence)
   {
       ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, attendence );

       AttendenceList.setAdapter(arrayAdapter);

   }
}


