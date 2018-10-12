package edu.somaiya.myrealtimedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
EditText editTextName;
Spinner spinner;
Button buttonAdd;
DatabaseReference databaseStudents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseStudents= FirebaseDatabase.getInstance().getReference("students");
        editTextName=(EditText)findViewById(R.id.editTextName);
        spinner=(Spinner)findViewById(R.id.spinner);
        buttonAdd=(Button)findViewById(R.id.buttonAdd);
buttonAdd.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
addStudent();
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
