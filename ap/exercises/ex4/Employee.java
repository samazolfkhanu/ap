package ap.exercises.ex4;

import java.util.Objects;

public class Employee
{
    private String name;
    private double salary;

    public Employee(String name,double salary)throws InvalidInputException
    {
        if(name!=null && salary>0)
        {
            this.name=name;
            this.salary=salary;
        }
        else
            throw new InvalidInputException("Invalid Input!");
    }

    public void setName(String name)throws InvalidInputException
    {
        if(name!=null)
            this.name=name;
        else
            throw new InvalidInputException("Invalid Input!");
    }
    public String getName()
    {
        return name;
    }

    public void setSalary(double salary)throws InvalidInputException
    {
        if(salary>0)
            this.salary=salary;
        else
            throw new InvalidInputException("Invalid Input!");
    }
    public double getSalary()
    {
        return salary;
    }

    public void raiseSalary(double byPercent)
    {
        salary=(salary*(byPercent)/100)+salary;
    }

    @Override
    public String toString()
    {
        return ">>name: "+name+"\tsalary: "+salary;
    }
}
