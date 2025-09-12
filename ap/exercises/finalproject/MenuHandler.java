package ap.exercises.finalproject;

import java.util.Scanner;
public class MenuHandler {
    private Scanner scanner;
    private LibrarySystem librarySystem;
    private Student currentStudent;
    private Librarian currentLibrarian;
    private Manager currentManager;

    public MenuHandler(LibrarySystem librarySystem) {
        this.scanner = new Scanner(System.in);
        this.librarySystem = librarySystem;
        this.currentStudent = null;
        this.currentLibrarian=null;
        this.currentManager=null;
    }

    public void displayMainMenu(){

        while (true) {
            try{
                System.out.println("\n=== University Library Management System ===");
                System.out.println("1.Student Registration");
                System.out.println("2.Student Login");
                System.out.println("3.Librarian Login");
                System.out.println("4.View Registered User Count");
                System.out.println("5.Search Book");
                System.out.println("6.Statistical Figures");
                System.out.println("7.Manager");
                System.out.println("0.Exit");
                System.out.print("Please enter your choice: ");

                int choice = getIntInput(0, 8);

                switch (choice) {
                    case 1:
                        handleStudentRegistration();
                        break;
                    case 2:
                        handleStudentLogin();
                        break;
                    case 3:
                        handleLibrarianLogin();
                        break;
                    case 4:
                        librarySystem.returnRegisteredUsers();
                        break;
                    case 5:
                        System.out.println("Enter Book Name: ");
                        String n = scanner.nextLine().trim();
                        librarySystem.searchBookByGuest(n);
                        break;
                    case 6:
                        librarySystem.returnRegisteredUsers();
                        displayBookCount();
                        break;
                    case 7:
                        handleManager();
                        break;
                    case 0:
                        System.out.println("Exiting system. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid option! Please try again.");
                }
                System.out.println("___________________________");
            }catch(InvalidEntrance e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private void displayBookCount()
    {
        int bookCount= librarySystem.getBookCount();
        System.out.println("Total Books: "+bookCount);
    }

    private void handleManager() {
        try {
            System.out.println("Username: ");
            String un=scanner.nextLine().trim();
            System.out.println("Password:");
            String pw=scanner.nextLine().trim();
            currentManager=librarySystem.authenticateManager(un,pw);
            if(currentManager!=null) {
                System.out.println("Login successful! Welcome, " + currentManager.getUsername());
                managerMenu();
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void handleStudentRegistration() throws InvalidEntrance {
        System.out.println("\n--- New Student Registration ---");
        System.out.print("Student name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();
        librarySystem.registerStudent(name,username, password);
    }

    private void handleStudentLogin() throws InvalidEntrance {
        System.out.println("\n--- Student Login ---");
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();
        currentStudent =librarySystem.authenticateStudent(username, password);
        if (currentStudent != null) {
            System.out.println("Login successful! Welcome, " + currentStudent.getName());
            displayLoggedInStudentMenu();
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    public void handleLibrarianLogin() throws InvalidEntrance {
        System.out.println("\n--- Librarian Login ---");
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();
        currentLibrarian = librarySystem.authenticateLibrarian(username, password);
        if (currentLibrarian != null) {
            System.out.println("Login successful! Welcome, " + currentLibrarian.getUsername());
            displayLoggedInLibrarianMenu();
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private void displayLoggedInStudentMenu() throws InvalidEntrance {
        while (currentStudent != null) {
            try {
                System.out.println("\n=== Student Dashboard ===");
                System.out.println("1. View My Information");
                System.out.println("2. Edit My Information");
                System.out.println("3. Borrow a Book");
                System.out.println("4. Return a Book");
                System.out.println("5. View Available Books");
                System.out.println("6.Search Book");
                System.out.println("0. Logout");
                System.out.print("Please enter your choice: ");
                int choice = getIntInput(0, 6);

                switch (choice) {
                    case 1:
                        System.out.println("\n--- My Information ---");
                        System.out.println(librarySystem.getAStudent(currentStudent.getUsername()));
                        break;
                    case 2:
                        editStudentInfo();
                        break;
                    case 3:
                        System.out.println("Enter Book Name , Author and Published Year: ");
                        String name = scanner.nextLine().trim();
                        String author = scanner.nextLine().trim();
                        int publishedYear = scanner.nextInt();
                        scanner.nextLine();
                        librarySystem.borrowBookRequest(currentStudent, name, author, publishedYear);
                        break;
                    case 4:
                        System.out.println("Enter Book Name , Author and Published Year: ");
                        String n = scanner.nextLine().trim();
                        String a = scanner.nextLine().trim();
                        int p = scanner.nextInt();
                        scanner.nextLine();
                        librarySystem.returnBookRequest(currentStudent, n, a, p);
                        break;
                    case 5:
                        librarySystem.displayAvailableBooks();
                        break;
                    case 6:
                        System.out.println("Enter Book Name , Author and Published Year: ");
                        String Name = scanner.nextLine().trim();
                        String Author = scanner.nextLine().trim();
                        int pYear = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println(librarySystem.searchBook(Name, Author, pYear));
                        break;
                    case 0:
                        currentStudent = null;
                        System.out.println("Logged out successfully.");
                        return;
                    default:
                        System.out.println("Invalid option! Please try again.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void displayLoggedInLibrarianMenu() {
        while (currentLibrarian != null) {
            try{
                System.out.println("\n==Librarian DashBoard==");
                System.out.println("1.View My Information");
                System.out.println("2.Edit My Information");
                System.out.println("3.Add Book");
                System.out.println("4.Edit Book Information");
                System.out.println("5.Confirm Borrow Requests");
                System.out.println("6.Confirm Return Request");
                System.out.println("7.Ban Student");
                System.out.println("8.Unban Student");
                System.out.println("9.Student History");
                System.out.println("0.Logout");
                System.out.println("Please Enter Your Choice:");
                int choice = getIntInput(0, 9);
                switch (choice) {
                    case 1:
                        System.out.println("\n--- My Information ---");
                        System.out.println(librarySystem.getAlibrarian(currentLibrarian.getUsername()));
                        break;
                    case 2:
                        System.out.println("Enter New Password: ");
                        String nPassword = scanner.nextLine().trim();
                        librarySystem.editLibrarianInformation(currentLibrarian, nPassword);
                        break;
                    case 3:
                        System.out.println("Enter Book Name: ");
                        String name = scanner.nextLine().trim();
                        System.out.println("Enter Author: ");
                        String author = scanner.nextLine().trim();
                        System.out.println("Enter ISBN:");
                        String ISBN=scanner.nextLine();
                        System.out.println("Enter Published Year: ");
                        int published_year = scanner.nextInt();
                        scanner.nextLine();
                        librarySystem.addBook(name, author, published_year, currentLibrarian.getUsername(),ISBN);
                        break;
                    case 4:
                        System.out.println("Enter Book Name , Author and Published Year: ");
                        String n = scanner.nextLine().trim();
                        String a = scanner.nextLine().trim();
                        int p = scanner.nextInt();
                        scanner.nextLine();
                        Book book = librarySystem.searchBook(n, a, p);
                        editBookInfo(book);
                        break;
                    case 5:
                        if(librarySystem.getBorrowRequestList())
                        {
                            System.out.println("Enter Request Id: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            librarySystem.addToLoans(id, currentLibrarian);
                        }
                        break;
                    case 6:
                        if(librarySystem.getReturnRequest())
                        {
                            System.out.println("Enter Request Id: ");
                            int idi = scanner.nextInt();
                            scanner.nextLine();
                            librarySystem.addToHistory(idi, currentLibrarian);
                        }
                        break;
                    case 7:
                        System.out.println("Enter Student Username:");
                        String Username = scanner.nextLine().trim();
                        librarySystem.banStudent(Username);
                        break;
                    case 8:
                        System.out.println("Enter Student Username:");
                        String Usern = scanner.nextLine().trim();
                        librarySystem.unbanStudent(Usern);
                        break;
                    case 9:
                        System.out.println("Enter Student Username:");
                        String username = scanner.nextLine().trim();
                        librarySystem.studentHistory(username);
                        break;
                    case 0:
                        currentStudent = null;
                        System.out.println("Logged out successfully.");
                        return;
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    private void editStudentInfo() {
        try {
            System.out.println("1.Edit Name");
            System.out.println("2.Edit ID");
            System.out.println("3.Edit Passsword");
            System.out.println("0.Logout");
            System.out.println("Enter Your Choice:");
            int c = scanner.nextInt();
            scanner.nextLine();
            switch (c) {
                case 1:
                    System.out.println("Enter New Name:");
                    String name = scanner.nextLine().trim();
                    librarySystem.editStudentInfo(currentStudent, name, "name");
                    break;
                case 2:
                    System.out.println("Enter New Id: ");
                    String id = scanner.nextLine().trim();
                    librarySystem.editStudentInfo(currentStudent, id, "id");
                    break;
                case 3:
                    System.out.println("Enter New Password: ");
                    String password = scanner.nextLine().trim();
                    librarySystem.editStudentInfo(currentStudent, password, "password");
                    break;
            }
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void editBookInfo(Book book) {
        try{
            System.out.println("Enter Your Choice: ");
            System.out.println("1.Edit Name");
            System.out.println("2.Edit Author");
            System.out.println("3.Edit Published_Year");
            System.out.println("0.Back");
            int c = scanner.nextInt();
            switch (c) {
                case 1:
                    System.out.println("Enter New Book Name:");
                    String nName = scanner.nextLine().trim();
                    librarySystem.editBookInfo(book, nName, "name");
                    break;
                case 2:
                    System.out.println("Enter New Author Name: ");
                    String nAuthor = scanner.nextLine().trim();
                    librarySystem.editBookInfo(book, nAuthor, "author");
                    break;
                case 3:
                    System.out.println("Enter New Published Year: ");
                    String nPublishedYear = scanner.nextLine();
                    librarySystem.editBookInfo(book, nPublishedYear, "publishedYear");
                    break;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void managerMenu(){
        int c=0;
        while(currentManager!=null) {
           try{
               System.out.println("\n===Manager Dashboard===");
               System.out.println("1.Add Librarian");
               System.out.println("2.Librarian History");
               System.out.println("3.Book History");
               System.out.println("4.Student History");
               System.out.println("5.List Of All Student And Librarian Registered");
               System.out.println("6.Add New Manager");
               System.out.println("0.Log Out");
               c=getIntInput(0,6);
               switch(c) {
                   case 1:
                       System.out.println("Enter Librarian Username: ");
                       String un=scanner.nextLine().trim();
                       System.out.println("Enter Library Id: ");
                       String id=scanner.nextLine().trim();
                       librarySystem.addLibrarian(un,id);
                       break;
                   case 2:
                       System.out.println("Enter Librarian Username:");
                       String username=scanner.nextLine().trim();
                       librarySystem.librarianHistory(username);
                       break;
                   case 3:
                       System.out.println("Enter Book Name: ");
                       String name=scanner.nextLine().trim();
                       System.out.println("Enter Book Author:");
                       String author=scanner.nextLine().trim();
                       librarySystem.bookHistory(name,author);
                       break;
                   case 4:
                       System.out.println("Enter Student Username:");
                       String usern=scanner.nextLine().trim();
                       librarySystem.studentHistory(usern);
                       break;
                   case 5:
                       librarySystem.returnRegisteredUsers();
                       System.out.println("-->List Of 10 Top Late Return:");
                       librarySystem.getTop10LateReturns();
                       break;
                   case 6:
                       System.out.println("Enter Username: ");
                       String u=scanner.nextLine();
                       System.out.println("Enter Password: ");
                       String p=scanner.nextLine();
                       librarySystem.registerManager(u,p);
                       break;
                   case 0:
                       currentManager=null;
                       break;
               }
               System.out.println("___________________________");
           }catch(Exception e)
           {
               System.out.println(e.getMessage());
           }
        }
    }

    private int getIntInput(int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.printf("Please enter a number between %d and %d: ", min, max);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}
