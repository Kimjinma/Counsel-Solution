package proj;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private String category;
    private String returnDate;
    private int totalCopies;
    private int availableCopies;
    private boolean borrowed;

    // 기본 생성자
    public Book() {}

    // 매개변수가 있는 생성자
    public Book(int bookId, String title, String author, String category, String date, int totalCopies, int availableCopies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.returnDate = date;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
        this.borrowed = false;
    }

    // Getter 및 Setter 메서드
    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getReturnDate() { return returnDate; }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }

    public int getTotalCopies() { return totalCopies; }
    public void setTotalCopies(int totalCopies) { this.totalCopies = totalCopies; }

    public int getAvailableCopies() { return availableCopies; }
    public void setAvailableCopies(int availableCopies) { this.availableCopies = availableCopies; }

    public boolean isBorrowed() { return borrowed; }
    public void setBorrowed(boolean borrowed) { this.borrowed = borrowed; }
}
