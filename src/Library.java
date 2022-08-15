public class Library {
    private Book[] bookList;
    private int numberOfBooksInList = 0;
    private User[] userList;
    private int numberOfUsersInList = 0;

    public Library(Book[] bookList, User[] userList) {
        this.bookList = bookList;
        this.userList = userList;
    }

    public Book[] getBookList() {
        return bookList;
    }

    public void setBookList(Book[] bookList) {
        this.bookList = bookList;
    }

    public int getNumberOfBooksOnList() {
        return numberOfBooksInList;
    }

    public void setNumberOfBooksOnList(int numberOfBooksOnList) {
        this.numberOfBooksInList = numberOfBooksOnList;
    }

    public User[] getUserList() {
        return userList;
    }

    public void setUserList(User[] userList) {
        this.userList = userList;
    }

    public int getNumberOfUsersInList() {
        return numberOfUsersInList;
    }

    public void setNumberOfUsersInList(int numberOfUsersInList) {
        this.numberOfUsersInList = numberOfUsersInList;
    }

    public Book findBookByISBN(String ISBNCode) {
        Book foundBook = new Book("", "", "", 0, 0);
        for (int i = 0; i < this.numberOfBooksInList; i++) {
            if (ISBNCode.equals(this.bookList[i].getISBNCode())) {
                foundBook = this.bookList[i];
            }
        }
        return foundBook;
    }

    public int findPositionInBookListByISBNCodeToEdit(String ISBNCode) {
        for (int i = 0; i < this.numberOfBooksInList; i++) {
            if (ISBNCode.equals(this.bookList[i].getISBNCode())) {
                return i;
            }
        }
        return -1;
    }

    public void addUser(User user) throws ExistAUserWithThisNameException {
        User foundUser = findUserByName(user.getName());
        if (!"".equals(foundUser.getName())) {
            throw new ExistAUserWithThisNameException("Is already a user with this name");
        }
        userList[numberOfUsersInList] = user;
        numberOfUsersInList++;
    }

    public User findUserByName(String name) {
        User foundUser = new User("");
        for (int i = 0; i < numberOfUsersInList; i++) {
            if (name.equals(this.userList[i].getName())) {
                foundUser = this.userList[i];
            }
        }
        return foundUser;
    }
}
