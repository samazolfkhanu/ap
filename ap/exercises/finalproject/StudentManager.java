package ap.exercises.finalproject;

import java.util.ArrayList;
import java.util.List;

public class StudentManager implements List_Tool<Student> , Account_Handler<Student>,Ban_Control {
    private List<Student> students;
    private  FileHandling<Student> sF;

    public StudentManager() {
        this.students = new ArrayList<>();
        this.sF=new FileHandling<>("F:/JavaProject/ap/exercises/finalproject/Students.json");
    }

    public void registerStudent(String name, String username, String password) throws InvalidEntrance {
        getList();
        if (isUsernameTaken(username)) {
            System.out.println("This username already exists. Please choose a different username.");
            return;
        }
        int maxId=0;
        if(students!=null)
        {
            maxId=students.stream()
                    .map(x->Integer.parseInt(x.getStudentId()))
                    .max(Integer::compare)
                    .orElse(0);
        }
        Student newStudent = new Student(name.trim(),String.valueOf(maxId+1), username.trim(), password.trim());
        sF.writeInFile(newStudent, Student.class);
        System.out.println("Student registration completed successfully.");
    }

    public Student authenticateUser(String username, String password) {
        getList();
        return students.stream()
                .filter(s -> s.getUsername().equals(username) && s.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public void getList()
    {
        if(students!=null && !students.isEmpty())
            students.clear();
        students=sF.readFromFile(Student.class);
    }

    public List<Student> returnStudent()
    {
        getList();
        return students;
    }

    private boolean isUsernameTaken(String username) {
        getList();
        if(students!=null)
            return students.stream().anyMatch(s -> s.getUsername().equals(username));
        return false;
    }
    public void banStudent(String username) {
        getList();
        try{
            for(Student s:students){
                if(s.getUsername().equals(username) )
                    s.banStudent();
            }
        }catch (InvalidEntrance e) {
            System.out.println(e.getMessage());
        }
        updateList(students);
    }

    public void unbanStudent(String username){
        getList();
        try{
            for(Student s:students){
                if(s.getUsername().equals(username))
                    s.unbanStudent();
            }
        }catch(InvalidEntrance e)
        {
            System.out.println(e.getMessage());
        }
        updateList(students);
    }

    public void editName(String name,Student s) throws InvalidEntrance {
        getList();
        for(Student student:students)
        {
            if(student.getUsername().equals(s.getUsername()))
                student.setName(name);
        }
        updateList(students);
    }

    public void editPassword(String password,Student s) throws InvalidEntrance {
        getList();
        for(Student student:students)
        {
            if(student.getUsername().equals(s.getUsername()))
                student.setPassword(password);
        }
        updateList(students);
    }

    public void editStudentId(String id,Student s) throws InvalidEntrance {
        getList();
        for(Student student:students)
        {
            if(student.getUsername().equals(s.getUsername()))
                student.setStudentId(id);
        }
        updateList(students);
    }

    public void updateList(List<Student> l)
    {
        sF.clearFile();
        for(Student s:l)
        {
            sF.writeInFile(s, Student.class);
        }
    }

    public Student getAUser(String username)
    {
        getList();
        if(students!=null)
        {
            return students.stream()
                    .filter(x->x.getUsername().equals(username))
                    .findFirst()
                    .get();
        }
        return null;
    }
}

