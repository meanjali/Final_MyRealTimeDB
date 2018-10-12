package edu.somaiya.myrealtimedb;

public class Student {
    String studentId;
    String studentName;
    String year;
    public Student(){

    }

    public Student(String studentId, String studentName, String year) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.year = year;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getYear() {
        return year;
    }
}
