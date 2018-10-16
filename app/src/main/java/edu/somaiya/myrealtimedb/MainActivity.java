package edu.somaiya.myrealtimedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
EditText editTextName;
Spinner spinner;
Button buttonAdd;
DatabaseReference databaseStudents;
ListView lvStudent;
List<Student>studentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseStudents= FirebaseDatabase.getInstance().getReference("students");
        editTextName=(EditText)findViewById(R.id.editTextName);
        spinner=(Spinner)findViewById(R.id.spinner);
        buttonAdd=(Button)findViewById(R.id.buttonAdd);
        lvStudent=(ListView)findViewById(R.id.lvStudent);
studentList=new ArrayList<>();

buttonAdd.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
addStudent();
    }
});
    }


    @Override
    protected void onStart() {
        super.onStart();
        databaseStudents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
        //clear student list
                studentList.clear();
                //iterate for every artist
                for(DataSnapshot studentSnapshot:dataSnapshot.getChildren()){
        Student student=studentSnapshot.getValue(Student.class);
        studentList.add(student);
}
                StudentList adapter=new StudentList(MainActivity.this,studentList);
                lvStudent.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void addStudent(){
        String sName=editTextName.getText().toString().trim();
        String sYear=spinner.getSelectedItem().toString();
if (!TextUtils.isEmpty(sName)){
//store it in database
    //create unique id for student
String id=databaseStudents.push().getKey();
//create new student
    Student student=new Student(id,sName,sYear);
databaseStudents.child(id).setValue(student);
Toast.makeText(this,"Student Added",Toast.LENGTH_SHORT).show();
}
else{
    Toast.makeText(this,"Name cannot be empty",Toast.LENGTH_SHORT).show();
}
    }
}
