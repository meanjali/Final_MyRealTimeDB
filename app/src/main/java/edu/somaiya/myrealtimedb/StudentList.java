package edu.somaiya.myrealtimedb;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

//To display students list;create custom adapter
public class StudentList extends ArrayAdapter<Student> {
    private Activity context;
    private List<Student>studentList;

    public StudentList(Activity context,List<Student> studentList) {
        super(context,R.layout.showdata,studentList);
        this.context=context;
        this.studentList=studentList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.showdata,null,true);
        TextView tvName=(TextView)listViewItem.findViewById(R.id.tvName);
        TextView tvDept=(TextView)listViewItem.findViewById(R.id.tvDept);

        Student student=studentList.get(position);
        tvName.setText(student.getStudentName());
        tvDept.setText(student.getYear());

        return listViewItem;
        }
    }

