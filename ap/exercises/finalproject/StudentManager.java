package ap.exercises.finalproject;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private ArrayList<Student> students;
    private  FileHandling<Student> sF;

    public StudentManager() {
        this.students = new ArrayList<>();
        this.sF=new FileHandling("F:/JavaProject/ap/exercises/finalproject/Students.txt");
    }

    public void registerStudent(String name, String studentId, String username, String password) {
        if(!students.isEmpty())
            students.clear();
        students=sF.readFromFile(Student.class);
        if (studentId!=null && isUsernameTaken(username)) {
            System.out.println("This username already exists. Please choose a different username.");
            return;
        }

        Student newStudent = new Student(name, studentId, username, password);
        sF.writeInFile(newStudent);
        System.out.println("Student registration completed successfully.");
    }

    public Student authenticateStudent(String username, String password) {
        if(!students.isEmpty())
            students.clear();
        students=sF.readFromFile(Student.class);
        return students.stream()
                .filter(s -> s.getUsername().equals(username) && s.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public void displayStudents() {
        System.out.println("\n--- List of Registered Students ---");

        if (students.isEmpty()) {
            System.out.println("No students have registered yet.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void editStudentInformation(Student student)
    {
        System.out.println("1.Change Name\n" +
                "2.Change ID\n" +
                "3.Change Username\n" +
                "4.Change Password");

    }

    private boolean isUsernameTaken(String username) {
        if(students!=null)
            return students.stream().anyMatch(s -> s.getUsername().equals(username));
        return false;
    }

    public int getStudentCount() {
        return students.size();
    }
}
