import java.util.Scanner;

public class MainLibrary {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        Book[] bookList = new Book[10];
        User[] userList = new User[5];
        Library library = new Library(bookList, userList);
        selectedTypeOfUser(library, console);
        selectAdminOrClientMenu(library, console);
    }

    public static void selectedTypeOfUser(Library library, Scanner console) {
        String typeOfUser;
        do {
            System.out.println("Enter what type of user you are: admin/client/exit");
            typeOfUser = console.next();
            performSelectedActionUserType(library, typeOfUser, console);
        } while ("admin".equals(typeOfUser) || "client".equals(typeOfUser));
    }

    public static void performSelectedActionUserType(Library library, String typeOfUser, Scanner console) {
        switch (typeOfUser) {
            case "admin":
                System.out.println("Enter the name of admin");
                String nameOfUser = console.next();
                Admin admin = new Admin(nameOfUser);
                try {
                    library.addUser(admin);
                } catch (ExistAUserWithThisNameException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "client":
                System.out.println("Enter the name of client");
                nameOfUser = console.next();
                Client client = new Client(nameOfUser);
                try {
                    library.addUser(client);
                } catch (ExistAUserWithThisNameException e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:
                System.out.println("----------------");
        }
    }

    public static void selectAdminOrClientMenu(Library library, Scanner console){
        String continueOption;
        do {
            System.out.println("Do you want to print admin menu, to print client menu or exit application? admin/client/exit");
            continueOption = console.next();
            if ("exit".equals(continueOption)) {
                System.exit(0);
            } else if ("admin".equals(continueOption)){
                Admin admin=(Admin)library.getUserList()[0];
                accessedAdminMenuInLoop(admin,library,console);
            } else if ("client".equals(continueOption)){
                Client client=(Client) library.getUserList()[1];
                accessedClientMenuInLoop(client,library,console);
            }
        } while (!"admin".equals(continueOption) || !"client".equals(continueOption));
    }
    public static void accessedAdminMenuInLoop(Admin admin, Library library, Scanner console) {
        int option;
        do {
            printAdminMenu();
            option = console.nextInt();
            performSelectedActionForAdmin(admin, library, option, console);
        } while (option != 7);
    }

    public static void accessedClientMenuInLoop(Client client, Library library, Scanner console) {
        int option;
        do {
            printClientMenu();
            option = console.nextInt();
            performSelectedActionForClient(client, library, option, console);
        } while (option != 5);
    }

    public static void performSelectedActionForAdmin(Admin admin, Library library, int option, Scanner console) {
        switch (option) {
            case 1:
                System.out.println("Add a new book to the library");
                System.out.println("Enter the name of the book");
                String title = console.next();
                System.out.println("Enter the author of the book");
                String author = console.next();
                System.out.println("Enter the ISBNCode of the book");
                String ISBNCode = console.next();
                System.out.println("Enter total number of copies of the book");
                int totalNumberOfCopies = console.nextInt();
                System.out.println("Enter borrowed number of copies of the book");
                int borrowedNumberOfCopies = console.nextInt();
                Book book = new Book(title, author, ISBNCode, totalNumberOfCopies, borrowedNumberOfCopies);
                admin.addBook(book, library);
                break;
            case 2:
                System.out.println("Enter the ISBN code of the book you want to delete");
                ISBNCode = console.next();
                try {
                    admin.deleteBook(ISBNCode, library);
                } catch (BookNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 3:
                System.out.println("Enter the ISBN code of the book you want to delete");
                ISBNCode = console.next();
                System.out.println("Enter the number of copies of the book you want to delete");
                int numberOfCopiesToBeDeleted = console.nextInt();
                try {
                    admin.deleteBook(ISBNCode, numberOfCopiesToBeDeleted, library);
                } catch (BookNotFoundException e) {
                    System.out.println(e.getMessage());
                } catch (NumberOfCopiesToDeleteGreaterThanExistException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 4:
                admin.listAllBooks(library);
                break;
            case 5:
                System.out.println("Enter the ISBN code of the book you want to show all details");
                ISBNCode = console.next();
                try {
                    admin.listBookDetails(ISBNCode, library);
                } catch (BookNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 6:
                System.out.println("Enter the name of the client for whom you want to see the list of borrowed books");
                String clientName = console.next();
                admin.viewBorrowedBooks(clientName, library);
                break;
            case 7:
                System.out.println("You have exited the admin menu");
                break;
            default:
                System.out.println("The entered option is invalid, try again!");
        }
    }

    public static void performSelectedActionForClient(Client client, Library library, int option, Scanner console) {
        switch (option) {
            case 1:
                client.viewAvailableBooks(library);
                break;
            case 2:
                System.out.println("Enter the ISBN code of the book you want to check if it is available in the library");
                String ISBNCode = console.next();
                client.isBookAvailable(ISBNCode, library);
                break;
            case 3:
                System.out.println("Enter the ISBN code of the book you want to borrow");
                ISBNCode = console.next();
                try {
                    client.borrowBook(ISBNCode, library);
                } catch (BookNotFoundException e) {
                    System.out.println(e.getMessage());
                } catch (BookNotAvailableException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 4:
                System.out.println("Enter the ISBN code of the book you want to return");
                ISBNCode = console.next();
                try {
                    client.returnBook(ISBNCode, library);
                } catch (BookNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 5:
                System.out.println("You have exited the client menu");
                break;
            default:
                System.out.println("The entered option is invalid, try again!");
        }
    }

    public static void printAdminMenu() {
        System.out.println("ADMIN MENU:");
        System.out.println("1. Add a new book to the library");
        System.out.println("2. Delete a book entirely from the library");
        System.out.println("3. Delete a copy of a book from the library");
        System.out.println("4. Show the details of all the books in the library");
        System.out.println("5. Search for a book in the library by the ISBN code and show all the details");
        System.out.println("6. Shows all the books borrowed by a certain customer");
        System.out.println("7. Exit the admin menu");
        System.out.println("_________________________________________");
        System.out.println("Choose the action with the number: ");
    }

    public static void printClientMenu() {
        System.out.println("CLIENT MENU:");
        System.out.println("1. Shows a list of all the books available for borrowing");
        System.out.println("2. Look for a book in the library to see if it is available");
        System.out.println("3. Borrow a book");
        System.out.println("4. Return a book");
        System.out.println("5. Exit the client menu");
        System.out.println("_________________________________________");
        System.out.println("Choose the action with the number: ");
    }

}
