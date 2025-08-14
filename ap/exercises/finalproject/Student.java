package ap.exercises.finalproject;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String studentId;
    private String username;
    private String password;

    public Student(String name, String studentId, String username, String password) {
        this.name = name;
        this.studentId = studentId;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Student Info:\n" +
                "Name: " + name +
                " | Student ID: " + studentId +
                " | Username: " + username;
    }
}
