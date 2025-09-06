package ap.exercises.finalproject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class StudentManager {
    private List<Student> students;
    private  FileHandling<Student> sF;

    public StudentManager() {
        this.students = new ArrayList<>();
        this.sF=new FileHandling<>("F:/JavaProject/ap/exercises/finalproject/Students.json");
    }

    public void registerStudent(String name, String username, String password) {
        getStudents();
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

    public Student authenticateStudent(String username, String password) {
        getStudents();
        return students.stream()
                .filter(s -> s.getUsername().equals(username) && s.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public void getStudents()
    {
        if(students!=null && !students.isEmpty())
            students.clear();
        students=sF.readFromFile(Student.class);
    }

    public List<Student> returnStudent()
    {
        getStudents();
        return students;
    }

    private boolean isUsernameTaken(String username) {
        getStudents();
        if(students!=null)
            return students.stream().anyMatch(s -> s.getUsername().equals(username));
        return false;
    }
    public void banStudent(String username,String id) throws InvalidEntrance {
        getStudents();
        for(Student s:students){
            if(s.getUsername().equals(username) && s.getStudentId().equals(id))
                s.banStudent();
        }
        updateStudentFile(students);
    }

    public void unbanStudent(String username,String id) throws InvalidEntrance {
        getStudents();
        for(Student s:students){
            if(s.getUsername().equals(username) && s.getStudentId().equals(id))
                s.unbanStudent();
        }
        updateStudentFile(students);
    }

    public void editName(String name,Student s) throws InvalidEntrance {
        getStudents();
        for(Student student:students)
        {
            if(student.getUsername().equals(s.getUsername()))
                student.setName(name);
        }
        updateStudentFile(students);
    }

    public void editPassword(String password,Student s) throws InvalidEntrance {
        getStudents();
        for(Student student:students)
        {
            if(student.getUsername().equals(s.getUsername()))
                student.setPassword(password);
        }
        updateStudentFile(students);
    }

    public void editStudentId(String id,Student s) throws InvalidEntrance {
        getStudents();
        for(Student student:students)
        {
            if(student.getUsername().equals(s.getUsername()))
                student.setStudentId(id);
        }
        updateStudentFile(students);
    }

    public void updateStudentFile(List<Student> l)
    {
        sF.clearFile();
        for(Student s:l)
        {
            sF.writeInFile(s, Student.class);
        }
    }

    public Student getAStudent(String username)
    {
        getStudents();
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

