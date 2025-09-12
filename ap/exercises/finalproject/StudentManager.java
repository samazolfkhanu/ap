package ap.exercises.finalproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentManager implements List_Tool<Student> , Account_Handler<Student>,Ban_Control {
    private Map<String,Student> students;
    private  FileHandling<Student> sF;

    public StudentManager() {
        this.students = new HashMap<>();
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
            maxId=students.values().stream()
                    .map(student -> Integer.parseInt(student.getStudentId()))
                    .max(Integer::compare)
                    .orElse(0);
        }
        Student newStudent = new Student(name.trim(),String.valueOf(maxId+1), username.trim(), password.trim());
        sF.writeInFile(newStudent, Student.class);
        System.out.println("Student registration completed successfully.");
    }

    public Student authenticateUser(String username, String password) {
        getList();
        return students.values().stream()
                .filter(s -> s.getUsername().equals(username) && s.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public void getList()
    {
        List<Student> l=new ArrayList<>();
        if(students!=null && !students.isEmpty())
            students.clear();
        l=sF.readFromFile(Student.class);
        if(l!=null) {
            students = l.stream()
                    .collect(Collectors.toMap(Student::getUsername, x -> x));
        }
    }

    public Map<String,Student> returnStudent()
    {
        getList();
        return students;
    }

    private boolean isUsernameTaken(String username) {
        getList();
        if(students!=null)
            return students.entrySet().stream().anyMatch(s -> s.getKey().equals(username));
        return false;
    }
    public void banStudent(String username) {
        getList();
        try{
            for(Map.Entry<String ,Student> m:students.entrySet()) {
                if(m.getKey().equals(username))
                    m.getValue().banStudent();
            }
            System.out.println("Student Banned Successfully!");
        }catch (InvalidEntrance e) {
            System.out.println(e.getMessage());
        }
        updateList(students);
    }

    public void unbanStudent(String username){
        getList();
        try{
            for(Map.Entry<String ,Student> m:students.entrySet()) {
                if(m.getKey().equals(username))
                    m.getValue().unbanStudent();
            }
            System.out.println("Student Unbanned Successfully!");
        }catch(InvalidEntrance e)
        {
            System.out.println(e.getMessage());
        }
        updateList(students);
    }

    public void editName(String name,Student s) throws InvalidEntrance {
        getList();
        if(students!=null)
        {
            for(Map.Entry<String ,Student> m:students.entrySet())
            {
                if(m.getKey().equals(s.getUsername()))
                    m.getValue().setName(name);
            }
        }
        updateList(students);
    }

    public void editPassword(String password,Student s) throws InvalidEntrance {
        getList();
        if(students!=null) {
            for(Map.Entry<String ,Student> m:students.entrySet())
            {
                if(m.getValue().getPassword().equals(s.getPassword()))
                    m.getValue().setName(password);
            }
        }
        updateList(students);
    }

    public void editStudentId(String id,Student s) throws InvalidEntrance {
        getList();
        if(students!=null)
        {
            for(Map.Entry<String ,Student> m:students.entrySet())
            {
                if(m.getValue().getStudentId().equals(s.getStudentId()))
                    m.getValue().setName(id);
            }
        }
        updateList(students);
    }

    public void updateList(Map<String,Student> l)
    {
        sF.clearFile();
        for(Student s:l.values())
        {
            sF.writeInFile(s, Student.class);
        }
    }

    public Student getAUser(String username)
    {
        getList();
        if(students!=null)
        {
            return students.entrySet().stream()
                    .filter(x->x.getKey().equals(username))
                    .findFirst()
                    .get()
                    .getValue();
        }
        return null;
    }
}

