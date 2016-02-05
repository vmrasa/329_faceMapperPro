package edu.iastate.ing_inc.face_mapper_pro;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;

public class Attendance extends AppCompatActivity {

   private ListView AttendanceList;
   private ArrayList<String> attendanceArrList = new ArrayList<String>();


   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_attendance);
       getSupportActionBar().setTitle(getIntent().getStringExtra("classCode"));

       AttendanceList = (ListView) findViewById(R.id.attendence_list);


       //This arraylist should come from parse
       attendanceArrList.add("Bob Joe");
       attendanceArrList.add("Jane Doe");
       attendanceArrList.add("John Smith");
       attendanceArrList.add("Marry Jane");
       attendanceArrList.add("Bill Bones");
       attendanceArrList.add("Harry Doe");
       attendanceArrList.add("John Appleseed");
       attendanceArrList.add("Bri Jones");
       attendanceArrList.add("Terry Jones");
       attendanceArrList.add("Jane Doe");
       attendanceArrList.add("John Smith");
       attendanceArrList.add("Marry Jane");
       //end of Parse List
       generateList(attendanceArrList);
   }


   private void generateList(ArrayList attendance)
   {
       ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, attendance );

       AttendanceList.setAdapter(arrayAdapter);

   }
}


