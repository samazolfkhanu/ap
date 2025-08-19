package ap.exercises.finalproject;

// MenuHandler.java
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuHandler {
    private Scanner scanner;
    private LibrarySystem librarySystem;
    private Student currentStudent;
    private Librarian currentLibrarian;

    public MenuHandler(LibrarySystem librarySystem) {
        this.scanner = new Scanner(System.in);
        this.librarySystem = librarySystem;
        this.currentStudent = null;
        this.currentLibrarian=null;
    }

    public void displayMainMenu() {
        while (true) {
            System.out.println("\n=== University Library Management System ===");
            System.out.println("1. Student Registration");
            System.out.println("2. Student Login");
            System.out.println("3.Librarian Login");
            System.out.println("4. View Registered Student Count");
            System.out.println("5.Search Book");
            System.out.println("6.Statistical Figures");
            System.out.println("0. Exit");
            System.out.print("Please enter your choice: ");

            int choice = getIntInput(1, 5);

            switch (choice) {
                case 1:
                    handleStudentRegistration();
                    break;
                case 2:
                    handleStudentLogin();
                    break;
                case 4:
                    displayStudentCount();
                    break;
                case 5:
                    System.out.println("Enter Book Name: ");
                    String n=scanner.nextLine();
                    librarySystem.searchBookByGuest(n);
                case 6:
                    displayStudentCount();
                    displayBookCount();
                case 0:
                    System.out.println("Exiting system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
            System.out.println("___________________________");
        }
    }

    private void displayStudentCount() {
        int studentCount = librarySystem.getStudentCount();
        System.out.println("\nTotal registered students: " + studentCount);
    }
    private void displayBookCount()
    {
        int bookCount= librarySystem.getBookCount();
        System.out.println("Total Books: "+bookCount);
    }

    private void handleStudentRegistration() {
        System.out.println("\n--- New Student Registration ---");

        System.out.print("Student name: ");
        String name = scanner.nextLine();

        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        librarySystem.registerStudent(name, studentId, username, password);
    }

    private void handleStudentLogin() {
        System.out.println("\n--- Student Login ---");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        currentStudent =librarySystem.authenticateStudent(username, password);

        if (currentStudent != null) {
            System.out.println("Login successful! Welcome, " + currentStudent.getName());
            displayLoggedInStudentMenu();
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    public void handleLibrarianLogin()
    {
        System.out.println("\n--- Student Login ---");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        currentLibrarian=librarySystem.authenticateLibrarian(username,password);

        if (currentLibrarian != null) {
            System.out.println("Login successful! Welcome, " + currentLibrarian.getUsername());
            displayLoggedInLibrarianMenu();
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private void displayLoggedInStudentMenu() {
        while (currentStudent != null) {
            try
            {
                System.out.println("\n=== Student Dashboard ===");
                System.out.println("1. View My Information");
                System.out.println("2. Edit My Information");
                System.out.println("3. Borrow a Book");
                System.out.println("4. Return a Book");
                System.out.println("5. View Available Books");
                System.out.println("6.Search Book");
                System.out.println("0. Logout");
                System.out.print("Please enter your choice: ");

                int choice = getIntInput(1, 6);

                switch (choice) {
                    case 1:
                        System.out.println("\n--- My Information ---");
                        System.out.println(currentStudent);
                        break;
                    case 2:
                        librarySystem.editStudentInformation(currentStudent);
                        break;
                    case 3:
                        System.out.println("Enter Book Name , Author and Published Year: ");
                        String name=scanner.nextLine();
                        String author=scanner.nextLine();
                        int publishedYear=scanner.nextInt();
                        scanner.nextLine();
                        librarySystem.borrowBookRequest(currentStudent,name,author,publishedYear);
                        break;
                    case 4:
                        System.out.println("Enter Book Name , Author and Published Year: ");
                        String n=scanner.nextLine();
                        String a=scanner.nextLine();
                        int p=scanner.nextInt();
                        scanner.nextLine();
                        librarySystem.returnBookRequest(currentStudent,n,a,p);
                        break;
                    case 5:
                        librarySystem.displayAvailableBooks();
                        break;
                    case 6:
                        System.out.println("Enter Book Name , Author and Published Year: ");
                        String Name=scanner.nextLine();
                        String Author=scanner.nextLine();
                        int pYear=scanner.nextInt();
                        scanner.nextLine();
                        System.out.println(librarySystem.searchBook(Name,Author,pYear));
                        break;
                    case 0:
                        currentStudent = null;
                        System.out.println("Logged out successfully.");
                        return;
                    default:
                        System.out.println("Invalid option! Please try again.");
                }
            }catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    private void displayLoggedInLibrarianMenu()
    {
        while(currentStudent !=null) {
            try
            {
                System.out.println("\n==Librarian DashBoard==");
                System.out.println("1.View My Information");
                System.out.println("2.Edit My Information");
                System.out.println("3.Add Book");
                System.out.println("4.Edit Book Information");
                System.out.println("5.Confirm Borrow Requests");
                System.out.println("6.Student History");
                System.out.println("7.Ban Student");
                System.out.println("8.Confirm Return Request");
                System.out.println("0.Logout");
                System.out.println("Please Enter Your Choice:");

                int choice = getIntInput(1, 6);

                switch(choice)
                {
                    case 1:
                        System.out.println("\n--- My Information ---");
                        System.out.println(currentLibrarian);
                        break;
                    case 2:
                        System.out.println("Enter New Password: ");
                        String nPassword=scanner.nextLine();
                        librarySystem.editLibrarianInformation(currentLibrarian,nPassword);
                        break;
                    case 3:
                        System.out.println("Enter Book Name: ");
                        String name=scanner.nextLine();
                        System.out.println("Enter Author: ");
                        String author=scanner.nextLine();
                        System.out.println("Enter Published Year: ");
                        int published_year=scanner.nextInt();
                        scanner.nextLine();
                        librarySystem.addBook(name,author,published_year);
                        break;
                    case 4:
                        System.out.println("Enter Book Name , Author and Published Year: ");
                        String n=scanner.nextLine();
                        String a=scanner.nextLine();
                        int p=scanner.nextInt();
                        scanner.nextLine();
                        Book book=librarySystem.searchBook(n,a,p);
                        editBookInfo(book);
                        break;
                    case 5:
                        librarySystem.getBorrowRequestList();
                        System.out.println("Enter Request Id: ");
                        int id=scanner.nextInt();
                        scanner.nextLine();
                        librarySystem.addToLoans(id,currentLibrarian);
                        break;
                    case 6:
                        System.out.println("Enter Student Username:");
                        String username=scanner.nextLine();
                        System.out.println("Enter Student Id:");
                        String Id=scanner.nextLine();
                        librarySystem.studentHistory(username,Id);
                        break;
                    case 7:
                        System.out.println("Enter Student Username:");
                        String Username=scanner.nextLine();
                        System.out.println("Enter Student Id:");
                        String ID=scanner.nextLine();
                        librarySystem.banStudent(Username,ID);
                        break;
                    case 8:
                        librarySystem.getReturnRequest();
                        System.out.println("Enter Request Id: ");
                        int idi=scanner.nextInt();
                        scanner.nextLine();
                        librarySystem.addToHistory(idi,currentLibrarian);
                        break;
                    case 0:
                        currentStudent = null;
                        System.out.println("Logged out successfully.");
                        return;
                }
            }catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
    private void editBookInfo(Book book)
    {
        try
        {
            System.out.println("Enter Your Choice: ");
            System.out.println("1.Edit Name");
            System.out.println("2.Edit Author");
            System.out.println("3.Edit Published_Year");
            System.out.println("0.Back");
            int c=scanner.nextInt();
            switch(c)
            {
                case 1:
                    System.out.println("Enter New Book Name:");
                    String nName=scanner.nextLine();
                    librarySystem.editBookInfo(book,nName,"name");
                    break;
                case 2:
                    System.out.println("Enter New Author Name: ");
                    String nAuthor=scanner.nextLine();
                    librarySystem.editBookInfo(book,nAuthor,"author");
                    break;
                case 3:
                    System.out.println("Enter New Published Year: ");
                    String nPublishedYear=scanner.nextLine();
                    librarySystem.editBookInfo(book,nPublishedYear,"publishedYear");
                    break;
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
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
