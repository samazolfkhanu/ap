package ap.exercises.ex3;

public class Main_EX3_LM_1_1
{
    public static void main(String[] args)
    {
        try
        {
            Book[] b=new Book[2];
            b[0]=new Book("The Gifts Of Imperfection","Brene Brown",138,2010);
            b[1]=new Book("ABC","DEF",349,2006);

            
            Student[] s=new Student[2];
            s[0]=new Student("abc","fff",675432626,"hfgf");
            s[1]=new Student("hfgf","skjd",736863,"dss");
            for(Book B:b)
            {
                System.out.println(B.toString());
            }
            for(Student S:s)
            {
                System.out.println(S.toString());
            }

        }
        catch (InvalidInputException e)
        {
            System.out.println(e.getMessage());
        }
    }
}

