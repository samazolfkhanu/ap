package ap.exercises;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Menu
{
    InputeScanner s=new InputeScanner();
    private Library lib=new Library();
    private final Manager m=new Manager("Sama","Zolfkhani","PostDoc");

    public Menu(Library lib)
    {
        if(lib!=null)
            this.lib=lib;
        else
            throw new NullPointerException("library field is null!");
    }
    public void run()
    {
        int c;
        do {
            System.out.println("Choose your role:\n1.Manager\n2.Librarian\n3.Student\n0.exit");
            c=s.getIntOption();
            s.getStringOption();
            switch(c)
            {
                case 1:
                    managerMenu();
                    break;
                case 2:
                    librarianMenu();
                    break;
                case 3:
                    studentMenu();
                    break;
                case 0:
            }
        }while(c!=0);
    }

    public void studentMenu()
    {
        List<Student> stud=lib.getStudents();
        for(Student student:stud)
        {
            System.out.println(student);
        }
        int c1;
        do{
            System.out.println("1.Register\n2.login\n0.back");
            c1=s.getIntOption();
            s.getStringOption();
            switch(c1)
            {
                case 1:
                    System.out.println("Enter your name,family name,discpline:");
                    String name=s.getStringOption();
                    String family =s.getStringOption();
                    String dis=s.getStringOption();
                    s.getStringOption();
                    LocalDate memberShipDate=LocalDate.now();
                    try
                    {
                        Student stu=new Student(name,family,dis,memberShipDate);
                        lib.registerStudent(stu);
                    }
                    catch(InvalidInputException e)
                    {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    boolean fs=false;
                    List<Student> students=lib.getStudents();
                    System.out.println("Enter your id: ");
                    Long idi=s.getLongOption();
                    s.getStringOption();
                    for(Student s2:students)
                    {
                        if(s2.getid()==idi)
                        {
                            int c2;
                            s.getStringOption();
                            do {
                                System.out.println("1.search Books\n0.back\n");
                                c2=s.getIntOption();
                                s.getStringOption();
                                switch(c2)
                                {
                                    case 1:
                                        boolean fb=false;
                                        System.out.println("enter book name:");
                                        String bn=s.getStringOption();
                                        List<Book> b=lib.getBooks();
                                        for(Book b1:b)
                                        {
                                            if(b1.getBookName().equals(bn))
                                            {
                                                fb=true;
                                                System.out.println("Book found!");
                                                System.out.println(b1);

                                                break;
                                            }
                                        }
                                        if(!fb)
                                            System.out.println("Book not found!");
                                        break;
                                }
                            }while(c2!=0);
                            break;
                        }

                    }
                    if(!fs)
                        System.out.println("Student not found");
                    break;

            }
        }while(c1!=0);
    }

    public void managerMenu()
    {
        int c1;
        do {
            System.out.println("1.Add librarian\n0.Back");
            c1=s.getIntOption();
            s.getStringOption();
            switch(c1)
            {
                case 1:
                    System.out.println("Enter name and family name:");
                    String name=s.getStringOption();
                    String familyName=s.getStringOption();
                    try
                    {
                        lib.registerLibrarian(new Librarian(name,familyName));
                    }
                    catch(InvalidInputException e)
                    {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }while(c1!=0);

    }

    public void librarianMenu()
    {
        int c1,c2,c3;
        do {
            System.out.println("1.Login\n0.Back");
            c1=s.getIntOption();
            s.getStringOption();
            switch(c1)
            {
                case 1:
                    Librarian librarian;
                    System.out.println("Enter your ID:");
                    Long id=s.getLongOption();
                    boolean isfound=false;
                    List<Librarian> libra=lib.getLibrarians();
                    for(Librarian l1:libra)
                    {
                        if(Objects.equals(l1.getID(), id))
                        {
                            isfound=true;
                            do {
                                System.out.println("1.Edit info\n2.Add Book\n3.Show info\n0.Back");
                                c2=s.getIntOption();
                                s.getStringOption();
                                try
                                {
                                    switch(c2)
                                    {
                                        case 1:
                                            System.out.println("1.Name\n2.Family Name");
                                            c3=s.getIntOption();
                                            s.getStringOption();
                                            switch(c3)
                                            {
                                                case 1:
                                                    System.out.println("Enter name: ");
                                                    String name=s.getStringOption();
                                                    libra.removeIf(l3 -> l3.getID() == l1.getID());
                                                    l1.setN(name);
                                                    libra.add(l1);
                                                    lib.addNew(libra);
                                                    break;
                                                case 2:
                                                    System.out.println("Enter name: ");
                                                    String fname=s.getStringOption();
                                                    libra.removeIf(l4->l4.getID()==l1.getID());
                                                    l1.setFN(fname);
                                                    libra.add(l1);
                                                    lib.addNew(libra);
                                                    break;}
                                            break;

                                        case 2:
                                            System.out.println("Enter name of book,author , number of pages and published year: ");
                                            String bn=s.getStringOption();
                                            String an=s.getStringOption();
                                            int np=s.getIntOption();
                                            int py=s.getIntOption();
                                            s.getStringOption();
                                            lib.addBook(new Book(bn,an,np,py));
                                            break;

                                        case 3:
                                            System.out.println(l1);
                                    }
                                }
                                catch(InvalidInputException err)
                                {
                                    System.out.println(err.getMessage());
                                }
                            }while(c2!=0);
                        }
                    }
                    if(!isfound)
                        System.out.println("Can not find Librarian with this id!");
                    break;
            }
        }while(c1!=0);
    }
}