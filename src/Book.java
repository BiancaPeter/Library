public class Book {
    private String title;
    private String author;
    private String ISBNCode;
    private int totalNumberOfCopies;
    private int borrowedNumberOfCopies;

    public Book(String title, String author, String ISBNCode, int totalNumberOfCopies, int borrowedNumberOfCopies) {
        this.title = title;
        this.author = author;
        this.ISBNCode = ISBNCode;
        this.totalNumberOfCopies = totalNumberOfCopies;
        this.borrowedNumberOfCopies = borrowedNumberOfCopies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBNCode() {
        return ISBNCode;
    }

    public void setISBNCode(String ISBNCode) {
        this.ISBNCode = ISBNCode;
    }

    public int getTotalNumberOfCopies() {
        return totalNumberOfCopies;
    }

    public void setTotalNumberOfCopies(int totalNumberOfCopies) {
        this.totalNumberOfCopies = totalNumberOfCopies;
    }

    public int getBorrowedNumberOfCopies() {
        return borrowedNumberOfCopies;
    }

    public void setBorrowedNumberOfCopies(int borrowedNumberOfCopies) {
        this.borrowedNumberOfCopies = borrowedNumberOfCopies;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", ISBNCode='" + ISBNCode + '\'' +
                ", totalNumberOfCopies=" + totalNumberOfCopies +
                ", borrowedNumberOfCopies=" + borrowedNumberOfCopies +
                '}';
    }
}
