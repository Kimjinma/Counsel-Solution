package proj;

public class BorrowRecord {
    private Book book;
    private String borrower;
    private String borrowDate;

    public BorrowRecord(Book book, String borrower, String borrowDate) {
        this.book = book;
        this.borrower = borrower;
        this.borrowDate = borrowDate;
    }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

    public String getBorrower() { return borrower; }
    public void setBorrower(String borrower) { this.borrower = borrower; }

    public String getBorrowDate() { return borrowDate; }
    public void setBorrowDate(String borrowDate) { this.borrowDate = borrowDate; }
}

