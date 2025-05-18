package ap.exercises.midtermproject;

import java.time.LocalDate;
import java.util.*;

public class Menu
{
    InputeScanner s=new InputeScanner();
    private Library lib=new Library();
    private final Manager m=new Manager("Sama","Zolfkhani",1L,"PostDoc");
    private FileHandling<Manager> m1;
    public Menu(Library lib)
    {
        if(lib!=null) {
            this.lib = lib;
            m1=new FileHandling<>();
            m1.writeToFile(m,"F:/JavaProject/ap/exercises/midtermproject/Manager.txt");
        }
        else
            throw new NullPointerException("library field is null!");
    }
    public void run()
    {
        Role c;
        String r;
        do {
            System.out.println("Choose your role:\nManager\nLibrarian\nStudent\nExit");
            c=Role.search(s.getStringOption());

            switch(c)
            {
                case Role.MANAGER:
                    managerMenu();
                    break;
                case Role.LIBRARIAN:
                    librarianMenu();
                    break;
                case Role.STUDENT:
                    studentMenu();
                    break;
                case Role.EXIT:
                    break;
            }
        }while(c!=Role.EXIT);
    }

    public void studentMenu()
    {
        int c1;
        do{
            System.out.println("1.Register\n2.login\n0.back");
            c1=s.getIntOption();
            s.getStringOption();
            switch(c1)
            {
                case 1:
                    System.out.println("Enter your name,family name,discpline and id:");
                    String name=s.getStringOption();
                    String family =s.getStringOption();
                    String dis=s.getStringOption();
                    long id=s.getLongOption();
                    s.getStringOption();
                    LocalDate memberShipDate=LocalDate.now();
                    try
                    {
                        Student stu=new Student(name,family,dis,memberShipDate,id);
                        lib.registerStudent(stu);
                    }
                    catch(InvalidInputException e)
                    {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    boolean fs=false;
                    Map<Long,Student> students=lib.getStudents();
                    System.out.println("Enter your id: ");
                    Long idi=s.getLongOption();
                    s.getStringOption();
                    if(students.containsKey(idi))
                    {
                        fs=true;
                        int c2;
                        do {
                            System.out.println("1.Search Books\n2.borrow a book\n3.Return Book\n4.List Of Books Not Returned\n0.back\n");
                            c2=s.getIntOption();
                            s.getStringOption();
                            switch(c2)
                            {
                                case 1:
                                    System.out.println("enter book name:");
                                    String bn=s.getStringOption();
                                    lib.searchBook(bn);
                                    break;

                                case 2:
                                    System.out.println("enter Book name: ");
                                    String nameB=s.getStringOption();
                                    lib.borrowProccess(nameB,idi);
                                    break;

                                case 3:
                                    System.out.println("enter book name: ");
                                    String bookn=s.getStringOption();
                                    lib.returnProccess(bookn);
                                    break;

                                case 4:
                                    lib.listOfBookNR(idi);
                                    break;


                            }
                        }while(c2!=0);
                        break;
                    }
                    if(!fs)
                        System.out.println("Student not found");
                    break;

            }
        }while(c1!=0);
    }

    public void managerMenu()
    {
        System.out.println("Enter your Id: ");
        Long i=s.getLongOption();
        boolean ifo=false;
        Map<Long,Manager> m2=m1.readFromFile("F:/JavaProject/ap/exercises/midtermproject/Manager.txt");
        for(Manager m3:m2.values())
        {
            if(Objects.equals(m3.getId(), i))
            {
                ifo=true;
                int c1;
                do {
                    System.out.println("1.Add librarian\n2.Borrowed Books\n3.10 top Books\n4.Librarian History\n0.Back");
                    c1=s.getIntOption();
                    s.getStringOption();
                    switch(c1)
                    {
                        case 1:
                            System.out.println("Enter name and family name and id:");
                            String name=s.getStringOption();
                            String familyName=s.getStringOption();
                            long id=s.getLongOption();
                            s.getStringOption();
                            try
                            {
                                lib.registerLibrarian(new Librarian(name,familyName,id));
                            }
                            catch(InvalidInputException e)
                            {
                                System.out.println(e.getMessage());
                            }
                            break;

                        case 2:
                            List<Book> b=lib.getborrowdBook();
                            for(Book book:b)
                            {
                                System.out.println(book);
                            }
                            break;

                        case 3:
                            Book[] books=lib.getTopBooks();
                            try
                            {
                                for(Book book:books)
                                {
                                    System.out.println(book.toString());
                                }
                            }
                            catch(Exception e)
                            {
                                System.out.println("End!");
                            }
                            break;

                        case 4:
                            Map<Long,Librarian> m=lib.getLibrarians();
                            for(Librarian l:m.values()) {
                                System.out.println(">>Librarian Information:\nName: "+ l.getN()+"\nFamily Name: "+ l.getFN()+"\nNumber Of Borrowd Book: "+ l.getnOBB()+"\nNumber of returned book: "+ l.getnORB());
                            }
                            break;

                    }
                }while(c1!=0);
            }
        }
        if(!ifo)
            System.out.println("No Manager with This Id!");

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
                    System.out.println("Enter your Id:");
                    Long id=s.getLongOption();
                    boolean isfound=false;
                    Map<Long,Librarian> libra=lib.getLibrarians();
                    if (libra.containsKey(id) )
                    {
                        isfound=true;
                        do {
                            System.out.println("1.Edit info\n2.Add Book\n3.Show info\n4.Accept Borrowing Book\n5.Accept Returning Book\n0.Back");
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
                                                libra.get(id).setN(name);
                                                lib.addNew(libra);
                                                break;
                                            case 2:
                                                System.out.println("Enter name: ");
                                                String fname=s.getStringOption();
                                                libra.get(id).setFN(fname);
                                                lib.addNew(libra);
                                                break;}
                                        break;

                                    case 2:
                                        System.out.println("Enter name of book,author , number of pages and published year and id: ");
                                        String bn=s.getStringOption();
                                        String an=s.getStringOption();
                                        int np=s.getIntOption();
                                        int py=s.getIntOption();
                                        long idi=s.getLongOption();
                                        s.getStringOption();
                                        lib.addBook(new Book(bn,an,np,py,idi));
                                        break;

                                    case 3:
                                        System.out.println(libra.get(id).toString());
                                        break;

                                    case 4:
                                        Map<Long,Request> re=lib.getRL();
                                        for(Map.Entry<Long ,Request> r:re.entrySet())
                                        {
                                            if(Objects.equals(r.getValue().getIssuedBy().getId(), id))
                                            {
                                                System.out.println(r.getValue().toString());
                                            }
                                        }
                                        System.out.println("Enter Loan id:");
                                        Long i=s.getLongOption();
                                        for(Map.Entry<Long ,Request> r1:re.entrySet())
                                        {
                                            if(Objects.equals(r1.getValue().getId(), i))
                                            {
                                                LocalDate date=LocalDate.now();
                                                Loan l=new Loan(r1.getValue().getBook(),r1.getValue().getStudent(),r1.getValue().getIssuedBy(),date,date.plusDays(30));
                                                lib.addNewBook(l.getB().getId());
                                                lib.addNewStudentit(l.getS().getId());
                                                re.remove(i);
                                                Map<Long,Librarian> m=lib.getLibrarians();
                                                for(Map.Entry<Long,Librarian> m1:m.entrySet())
                                                {
                                                    if(Objects.equals(m1.getValue().getId(), id))
                                                    {
                                                        m1.getValue().increasenOBB();
                                                        lib.addNew(m);
                                                        break;
                                                    }
                                                }
                                                lib.addTOLoans(l);
                                                lib.updateRequest(re);
                                                break;
                                            }
                                        }
                                        break;

                                    case 5:
                                        Map<Long,Request> r=lib.getRequestsb();
                                        for(Map.Entry<Long ,Request> req:r.entrySet())
                                        {
                                            if(Objects.equals(req.getValue().getIssuedBy().getId(), id))
                                            {
                                                System.out.println(req.getValue().toString());
                                            }
                                        }
                                        System.out.println("Enter Request id:");
                                        Long idii=s.getLongOption();
                                        for(Map.Entry<Long ,Request> r1:r.entrySet())
                                        {
                                            if(Objects.equals(r1.getValue().getId(), idii))
                                            {
                                                Map<Long,Librarian> librar=lib.getLibrarians();
                                                for(Map.Entry<Long,Librarian> m1:librar.entrySet())
                                                {
                                                    if(Objects.equals(m1.getValue().getId(), id))
                                                    {
                                                        m1.getValue().increasenORB();
                                                        lib.addNew(librar);
                                                        break;
                                                    }
                                                }
                                                lib.addNewStudentrt(r1.getValue().getStudent().getId());
                                                lib.updateRequestR(r.get(idii));
                                                break;
                                            }
                                        }
                                }
                            }
                            catch(InvalidInputException err)
                            {
                                System.out.println(err.getMessage());
                            }
                        }while(c2!=0);
                    }
                    if(!isfound)
                        System.out.println("Can not find Librarian with this id!");
                    break;
            }
        }while(c1!=0);
    }
}