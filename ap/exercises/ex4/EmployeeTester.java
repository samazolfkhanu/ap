package ap.exercises.ex4;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class EmployeeTester
{
    public static void main(String[] args)
    {
        Scanner s=new Scanner(System.in);
        try
        {
            System.out.println("enter name and salary: ");
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
