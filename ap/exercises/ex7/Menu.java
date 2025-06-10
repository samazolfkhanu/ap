package ap.exercises.ex7;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;
import java.util.*;

public class Menu
{
    Gson g=new GsonBuilder().create();
    private InputeScanner s=new InputeScanner();
    private Library lib;
    private final Manager m=new Manager("Sama","Zolfkhani",1L,"PostDoc");
    private final StorageHandler managerStorage=new JsonHandler(Manager.class);
    public Menu(Library lib) {
        if(lib!=null) {
            this.lib = lib;
            managerStorage.save(m,"F:/JavaProject/ap/exercises/midtermproject/Manager");
        }
        else
            throw new NullPointerException("library field is null!");
    }
    public void run()
    {
        Role c = null;
        String r;
        do {
            try
            {
                System.out.println("Choose your role:\n" +
                        "Manager\n" +
                        "Librarian\n" +
                        "Student\n" +
                        "Exit");
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
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }while(c!=Role.EXIT);
    }

    public void studentMenu()
    {
        int c1 = 0;
        do{
           try
           {
               System.out.println("1.Register\n" +
                       "2.login\n" +
                       "0.back");
               c1=s.getIntOption();
               switch(c1) {
                   case 1:
                       System.out.println("Enter your name,family name,discpline and id:");
                       String name = s.getStringOption();
                       String family = s.getStringOption();
                       String dis = s.getStringOption();
                       long id = s.getLongOption();
                       String memberShipDate = LocalDate.now().toString();
                       try {
                           Student stu = new Student(name, family, dis, memberShipDate, id);
                           lib.registerStudent(stu);
                       } catch (InvalidInputException e) {
                           System.out.println(e.getMessage());
                       }
                       break;

                   case 2:
                       boolean fs = false;
                       Map<Long, Student> students = lib.getStudents();
                       System.out.println(students.isEmpty());
                       for(Map.Entry<Long,Student> m1:students.entrySet())
                       {
                           System.out.println("hi");
                           System.out.println(m1.getValue().toString());
                       }
                       System.out.println("Enter your id: ");
                       Long idi = s.getLongOption();
                       if (students.containsKey(idi)) {
                           fs = true;
                           int c2;
                           do {
                               System.out.println("1.Search Books\n" +
                                       "2.borrow a book\n" +
                                       "3.Return Book\n" +
                                       "4.List Of Books Not Returned\n" +
                                       "5.Show Borrowing History\n" +
                                       "6.Show Student Info\n" +
                                       "7.Overdue Fine\n" +
                                       "0.back\n");
                               c2 = s.getIntOption();
                               switch (c2) {
                                   case 1:
                                       System.out.println("enter book name:");
                                       String bn = s.getStringOption();
                                       lib.searchBook(bn);
                                       break;

                                   case 2:
                                       System.out.println("enter Book name: ");
                                       String nameB = s.getStringOption();
                                       lib.borrowProccess(nameB, idi);
                                       break;

                                   case 3:
                                       System.out.println("enter book name: ");
                                       String bookn = s.getStringOption();
                                       lib.returnProccess(bookn);
                                       break;

                                   case 4:
                                       lib.listOfBookNR(idi);
                                       break;

                                   case 5:
                                       Map<Long, Loan> m = lib.getHistory();
                                       for (Loan l : m.values()) {
                                           if (Objects.equals(l.getS().getId(), idi))
                                               System.out.println(l);
                                       }
                                       break;

                                   case 6:
                                       Map<Long, Student> s = lib.getStudents();
                                       System.out.println(s.get(idi));
                                       break;

                                   case 7:
                                       lib.overdueFee(idi);
                                       break;

                               }
                           } while (c2 != 0);
                           break;
                       }
                       if (!fs)
                           System.out.println("Student not found");
                       break;
               }

            }
           catch(Exception e)
           {
               System.out.println(e.getMessage());
           }
        }while(c1!=0);
    }

    public void managerMenu()
    {
        System.out.println("Enter your Id: ");
        Long i=s.getLongOption();
        boolean ifo=false;
        Map<Long,Manager> m2=managerStorage.load("F:/JavaProject/ap/exercises/midtermproject/Manager");

        for(Manager m3:m2.values())
        {
            if(Objects.equals(m3.getId(), i))
            {
                ifo=true;
                int c1 = 0;
                do {
                    try {
                        System.out.println("1.Add librarian\n" +
                                "2.Overdue Book List\n" +
                                "3.10 top Books\n" +
                                "4.Librarian History\n" +
                                "5.Filter History\n" +
                                "6.Show all History\n" +
                                "0.Back");
                        c1 = s.getIntOption();
                        switch (c1) {
                            case 1:
                                System.out.println("Enter name and family name and id:");
                                String name = s.getStringOption();
                                String familyName = s.getStringOption();
                                long id = s.getLongOption();
                                try {
                                    lib.registerLibrarian(new Librarian(name, familyName, id));
                                } catch (InvalidInputException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;

                            case 2:
                                List<Book> b = lib.getborrowdBook();
                                for (Book book : b) {
                                    System.out.println(book);
                                }
                                break;

                            case 3:
                                System.out.println("10 Top Books in a Year:\n");
                                Book[] books = lib.getTopBooks();
                                try {
                                    for (Book book : books) {
                                        System.out.println(book.toString());
                                    }
                                } catch (Exception e) {
                                    System.out.println("End!");
                                }
                                break;

                            case 4:
                                Map<Long, Librarian> m = lib.getLibrarians();
                                for (Librarian l : m.values()) {
                                    System.out.println(">>Librarian Information:\nName: " + l.getName() + "\nFamily Name: " + l.getFamilyName() + "\nNumber Of Borrowd Book: " + l.getnOBB() + "\nNumber of returned book: " + l.getnORB());
                                }
                                break;

                            case 5:
                                System.out.println("Enter Character: ");
                                String c=s.getStringOption();
                                Map<Long,Loan> h=lib.getHistory();
                                for(Loan h1:h.values())
                                {
                                    if(h1.getB().getBookName().startsWith(c))
                                        System.out.println(h1);
                                }
                                break;

                            case 6:
                                Map<Long,Loan> h1=lib.getHistory();
                                for(Loan h2:h1.values())
                                {
                                    System.out.println(h2);
                                }
                                break;

                        }
                    }catch(Exception e)
                    {
                        System.out.println(e.getMessage());
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
            System.out.println("1.Login\n" +
                    "0.Back");
            c1=s.getIntOption();
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
                            System.out.println("1.Edit info\n" +
                                    "2.Add Book\n" +
                                    "3.Show info\n" +
                                    "4.Accept Borrowing Book\n" +
                                    "5.Accept Returning Book\n" +
                                    "6.History Of Librarian\n" +
                                    "7.History Of Borrowed Books\n" +
                                    "0.Back");
                            c2=s.getIntOption();
                            try
                            {
                                switch(c2)
                                {
                                    case 1:
                                        System.out.println("1.Name\n2.Family Name");
                                        c3=s.getIntOption();
                                        switch(c3)
                                        {
                                            case 1:
                                                System.out.println("Enter name: ");
                                                String name=s.getStringOption();
                                                libra.get(id).setName(name);
                                                lib.addNew(libra);
                                                break;
                                            case 2:
                                                System.out.println("Enter name: ");
                                                String fname=s.getStringOption();
                                                libra.get(id).setFamilyName(fname);
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
                                                System.out.println("\n1.Accept\n2.Reject\n");
                                                int aR=s.getIntOption();
                                                switch(aR)
                                                {
                                                    case 1:
                                                        LocalDate date=LocalDate.now();
                                                        String d=date.toString();
                                                        Loan l=new Loan(r1.getValue().getBook(),r1.getValue().getStudent(),r1.getValue().getIssuedBy(),d,date.plusDays(30).toString());
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

                                                    case 2:
                                                        re.remove(i);
                                                        System.out.println("Request was Rejected");
                                                        lib.updateRequest(re);
                                                        break;
                                                }
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
                                                System.out.println("\n1.Accept\n2.Reject\n");
                                                int aR=s.getIntOption();
                                                switch(aR)
                                                {
                                                    case 1:
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

                                                    case 2:
                                                        lib.updateRequestR(r.get(idii));
                                                        System.out.println("Request was Rejected!");
                                                        break;
                                                }
                                            }
                                        }
                                        break;

                                    case 6:
                                        Map<Long,Loan> m=lib.getHistory();
                                        for(Loan l:m.values())
                                        {
                                            if(Objects.equals(l.getIssuedBy().getId(), id) || Objects.equals(l.getReceivedBy().getId(), id))
                                                System.out.println(l);
                                        }
                                        break;

                                    case 7:
                                        Map<Long,Loan> m1=lib.getHistory();
                                        for(Loan l:m1.values())
                                        {
                                            if(Objects.equals(l.getIssuedBy().getId(), id) || Objects.equals(l.getReceivedBy().getId(), id))
                                                System.out.println(l.getB());
                                        }
                                        break;
                                }
                            }
                            catch(Exception err)
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