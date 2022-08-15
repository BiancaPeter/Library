public class Admin extends User {

    public Admin(String name) {
        super(name);
    }

    public void addBook(Book book, Library library) {
        Book foundBook = library.findBookByISBN(book.getISBNCode());
        if (!"".equals(foundBook.getISBNCode())) {
            foundBook.setTotalNumberOfCopies(book.getTotalNumberOfCopies() + 1);
        } else {
            library.getBookList()[library.getNumberOfBooksOnList()] = book;
            library.setNumberOfBooksOnList(library.getNumberOfBooksOnList() + 1);
        }
    }

    public void deleteBook(String ISBNCode, Library library) throws BookNotFoundException {
        int positionInBookList = library.findPositionInBookListByISBNCodeToEdit(ISBNCode);
        if (positionInBookList == -1) {
            throw new BookNotFoundException("The book with ISBNCode " + ISBNCode + " is not in the list.");
        }
        for (int i = positionInBookList; i < library.getNumberOfBooksOnList() - 1; i++) {
            library.getBookList()[i] = library.getBookList()[i + 1];
        }
        library.getBookList()[library.getNumberOfBooksOnList()] = null;
        library.setNumberOfBooksOnList(library.getNumberOfBooksOnList() - 1);
    }

    public void deleteBook(String ISBNCode, int numberOfCopiesToBeDeleted, Library library) throws BookNotFoundException, NumberOfCopiesToDeleteGreaterThanExistException {
        int positionInBookList = library.findPositionInBookListByISBNCodeToEdit(ISBNCode);
        if (positionInBookList == -1) {
            throw new BookNotFoundException("The book with ISBNCode " + ISBNCode + " is not in the list.");
        }
        if (numberOfCopiesToBeDeleted > library.getBookList()[positionInBookList].getTotalNumberOfCopies()) {
            throw new NumberOfCopiesToDeleteGreaterThanExistException("The number of copies you want to delete is greater than the number of copies existing in the library");
        }
        library.getBookList()[positionInBookList].setTotalNumberOfCopies(library.getBookList()[positionInBookList].getTotalNumberOfCopies() - numberOfCopiesToBeDeleted);
    }


    public void listAllBooks(Library library) {
        for (int i = 0; i < library.getNumberOfBooksOnList(); i++) {
            System.out.println(library.getBookList()[i]);
        }
    }

    public void listBookDetails(String ISBNCode, Library library) throws BookNotFoundException {
        Book foundBook = library.findBookByISBN(ISBNCode);
        if ("".equals(foundBook.getISBNCode())) {
            throw new BookNotFoundException("The book with ISBNCode " + ISBNCode + " is not in the list.");
        }
        System.out.println(foundBook);
    }

    public void viewBorrowedBooks(String name, Library library) {
        User foundUser = library.findUserByName(name);
        Client foundClient = (Client) foundUser;
        System.out.println("Customer " + name + " borrowed the following books:");
        for (int i = 0; i < foundClient.getNumberOfBorrowedBooks(); i++) {
            System.out.println(foundClient.getBorrowedBooksCodes()[i]);
        }
    }
}
