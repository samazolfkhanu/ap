package ap.exercises.finalproject;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String studentId;
    private String username;
    private String password;
    private AccessLevel permission;

    public Student(String name, String studentId, String username, String password) {
        this.name = name;
        this.studentId = studentId;
        this.username = username;
        this.password = password;
        this.permission= AccessLevel.search("active");
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

    public String getPermission()
    {
        return permission.name();
    }

    public void setPermission(String p) throws InvalidEntrance {
        if(p!=null)
            this.permission= AccessLevel.search(p);
        else
            throw new InvalidEntrance("Invalid Permission Input!");
    }
    @Override
    public String toString() {
        return "Student Info:\n" +
                "Name: " + name +
                " | Student ID: " + studentId +
                " | Username: " + username;
    }
}
