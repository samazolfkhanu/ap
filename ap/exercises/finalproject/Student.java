package ap.exercises.finalproject;

public class Student extends User{
    private String name;
    private String studentId;
    private AccessLevel permission;

    public Student(String name, String studentId, String username, String password) throws InvalidEntrance {
        super(username,password);
        if(name!=null && studentId!=null)
        {
            this.name = name;
            this.studentId = studentId;
            this.permission= AccessLevel.ACTIVE;
        }
        else
            throw new InvalidEntrance("Invalis Input!");
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getUsername() {
        return super.getUsername();
    }

    public String getPassword() {
        return super.getPassword();
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
            super.editPassword(password);
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
                "\tName: " + name +
                " | Student ID: " + studentId +
                " | Username: " + super.getUsername()+
                " | Access Level: "+permission.name();
    }
}
