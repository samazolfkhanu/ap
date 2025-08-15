package ap.exercises.finalproject;

// MenuHandler.java
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MenuHandler {
    private Scanner scanner;
    private LibrarySystem librarySystem;
    private User currentUser;

    public MenuHandler(LibrarySystem librarySystem) {
        this.scanner = new Scanner(System.in);
        this.librarySystem = librarySystem;
        this.currentUser = null;
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

        Student currentUser =librarySystem.authenticateStudent(username, password);

        if (currentUser != null) {
            System.out.println("Login successful! Welcome, " + currentUser.getName());
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

        Librarian currentUser=librarySystem.authenticateLibrarian(username,password);

        if (currentUser != null) {
            System.out.println("Login successful! Welcome, " + currentUser.getUsername());
            displayLoggedInStudentMenu();
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private void displayLoggedInStudentMenu() {
        while (currentUser != null) {
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
                        System.out.println(currentUser);
                        break;
                    case 2:
                        librarySystem.editStudentInformation((Student) currentUser);
                        break;
                    case 3:
                        System.out.println("Enter Book Name , Author and Published Year: ");
                        String name=scanner.nextLine();
                        String author=scanner.nextLine();
                        int publishedYear=scanner.nextInt();
                        scanner.nextLine();
                        librarySystem.borrowBook((Student) currentUser,name,author,publishedYear);
                        break;
                    case 4:
                        librarySystem.returnBook((Student) currentUser);
                        break;
                    case 5:
                        librarySystem.displayAvailableBooks();
                        break;
                    case 6:
                        System.out.println("Enter Book Name , Author and Published Year: ");
                        String n=scanner.nextLine();
                        String a=scanner.nextLine();
                        int p=scanner.nextInt();
                        scanner.nextLine();
                        librarySystem.searchBook(n,a,p);
                    case 0:
                        currentUser = null;
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
        System.out.println("\n==Librarian DashBoard==");
        System.out.println("1.View My Information");
        System.out.println("2.Edit My Information");
        System.out.println("3.Add Book");
        System.out.println("0.Logout");
        System.out.println("Please Enter Your Choice:");

        int choice = getIntInput(1, 6);

        switch(choice)
        {
            case 1:
                System.out.println("\n--- My Information ---");
                System.out.println(currentUser);
                break;
            case 2:

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
