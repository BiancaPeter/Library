import java.util.Arrays;

public class Client extends User {
    private String[] borrowedBooksCodes = new String[10];
    private int numberOfBorrowedBooks = 0;

    public Client(String name) {
        super(name);
    }

    public String[] getBorrowedBooksCodes() {
        return borrowedBooksCodes;
    }

    public int getNumberOfBorrowedBooks() {
        return numberOfBorrowedBooks;
    }

    public void isBookAvailable(String ISBNCode, Library library) {
        Book foundBook = library.findBookByISBN(ISBNCode);
        if (!"".equals(foundBook.getISBNCode())) {
            System.out.println("This book is available");
        } else {
            System.out.println("This book is not available");
        }
    }

    public void viewAvailableBooks(Library library) {
        System.out.println("The books available in the library are: ");
        for (int i = 0; i < library.getNumberOfBooksOnList(); i++) {
            if (library.getBookList()[i].getBorrowedNumberOfCopies() < library.getBookList()[i].getTotalNumberOfCopies()) {
                System.out.println(library.getBookList()[i]);
            }
        }
    }

    public void borrowBook(String ISBNCode, Library library) throws BookNotFoundException, BookNotAvailableException {
        int positionInBookList = library.findPositionInBookListByISBNCodeToEdit(ISBNCode);
        if (positionInBookList == -1) {
            throw new BookNotFoundException("The book with ISBNCode " + ISBNCode + " is not in the list.");
        }
        if (library.getBookList()[positionInBookList].getBorrowedNumberOfCopies() == library.getBookList()[positionInBookList].getTotalNumberOfCopies()) {
            throw new BookNotAvailableException("There are no copies available for borrowing");
        }
        borrowedBooksCodes[numberOfBorrowedBooks] = ISBNCode;
        numberOfBorrowedBooks++;
        library.getBookList()[positionInBookList].setBorrowedNumberOfCopies(library.getBookList()[positionInBookList].getBorrowedNumberOfCopies() + 1);
    }

    public void returnBook(String ISBNCode, Library library) throws BookNotFoundException {
        int positionInBorrowedBooksCodes = findPositionInBorrowedBooks(ISBNCode);
        if (positionInBorrowedBooksCodes == -1) {
            throw new BookNotFoundException("The book with ISBNCode " + ISBNCode + " is not in the list.");
        }
        for (int i = positionInBorrowedBooksCodes; i < numberOfBorrowedBooks - 1; i++) {
            borrowedBooksCodes[i] = borrowedBooksCodes[i + 1];
        }
        borrowedBooksCodes[numberOfBorrowedBooks - 1] = null;
        numberOfBorrowedBooks--;
        int positionInBookList = library.findPositionInBookListByISBNCodeToEdit(ISBNCode);
        library.getBookList()[positionInBookList].setBorrowedNumberOfCopies(library.getBookList()[positionInBookList].getBorrowedNumberOfCopies() - 1);
    }


    private int findPositionInBorrowedBooks(String ISBNCode) {
        for (int i = 0; i < numberOfBorrowedBooks; i++) {
            if (ISBNCode.equals(borrowedBooksCodes[i])) {
                return i;
            }
        }
        return -1;
    }
}

