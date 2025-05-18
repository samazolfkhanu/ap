package ap.exercises.ex4;

import java.util.Scanner;

public class Main_EX4_E3_12
{
    public static void main(String[] args)
    {

        Scanner s=new Scanner(System.in);
        try
        {
            System.out.println("enter name / salary: ");
            String name=s.nextLine();
            double salary=s.nextDouble();
            Employee e=new Employee(name,salary);
            System.out.println("Name: "+e.getName()+"\tSalary: "+e.getSalary());
            e.raiseSalary(20);
            System.out.println("Updated Information:\n>>Name: "+e.getName()+"\tSalary: "+e.getSalary());

        }

        catch(InvalidInputException e)
        {
            System.out.println(e.getMessage());
        }

    }
}
