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
        this.permission= AccessLevel.ACTIVE;
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

    public void banStudent() throws InvalidEntrance {
        this.permission=AccessLevel.INACTIVE;
    }
    public void unbanStudent() throws InvalidEntrance {
        this.permission=AccessLevel.ACTIVE;
    }

    public void setName(String name) throws InvalidEntrance {
        if(!name.isEmpty())
            this.name=name;
        else
        {
            throw new InvalidEntrance("Invalid Name! <500>");
        }
    }

    public void setPassword(String password) throws InvalidEntrance {
        if(!password.isEmpty())
            this.password=name;
        else
        {
            throw new InvalidEntrance("Invalid Password! <501>");
        }
    }

    public void setStudentId(String id) throws InvalidEntrance {
        if(!id.isEmpty())
            this.studentId=id;
        else
        {
            throw new InvalidEntrance("Invalid ID! <502>");
        }
    }

    @Override
    public String toString() {
        return "Student Info:\n" +
                "Name: " + name +
                " | Student ID: " + studentId +
                " | Username: " + username+
                " | Access Level: "+permission.name();
    }
}
