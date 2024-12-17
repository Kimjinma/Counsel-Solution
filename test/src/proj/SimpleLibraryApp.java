package proj;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SimpleLibraryApp extends JFrame {
    private JTextField searchField;
    private JTable bookTable;
    private JTextArea bookDetailsArea;
    private DefaultTableModel tableModel;

    // 도서 데이터를 리스트로 관리
    private List<Book> bookList;
    private List<BorrowRecord> borrowRecords;
    private Book selectedBook;

    public SimpleLibraryApp() {
        setTitle("Halla Library");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 도서 데이터 초기화
        bookList = new ArrayList<>();
        bookList.add(new Book(1,"자바의 정석", "남궁성", "프로그래밍", "2024-08-05", 5,8));
        bookList.add(new Book(2,"Effective Java", "Joshua Bloch", "프로그래밍", "2024-08-08", 4, 9));
        bookList.add(new Book(3,"리더십", "존 맥스웰", "자기계발", "2024-08-09", 3, 6));

        borrowRecords = new ArrayList<>();

        // 상단 메뉴바
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("메뉴");
        JMenuItem loginItem = new JMenuItem("로그인");
        JMenuItem logoutItem = new JMenuItem("로그아웃");
        menu.add(loginItem);
        menu.add(logoutItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // 검색 및 카테고리 패널
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(20);
        JButton searchButton = new JButton("검색");
        searchPanel.add(new JLabel("도서 검색:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton allBooksButton = new JButton("전체 도서");
        JButton category1Button = new JButton("프로그래밍");
        JButton category2Button = new JButton("자기계발");

        categoryPanel.add(allBooksButton);
        categoryPanel.add(category1Button);
        categoryPanel.add(category2Button);

        topPanel.add(searchPanel, BorderLayout.WEST);
        topPanel.add(categoryPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // 도서 목록 테이블
        String[] columnNames = {"책번호", "제목", "저자", "카테고리", "반납기간", "총 개수", "남은 책 수"};
        tableModel = new DefaultTableModel(columnNames, 0);
        bookTable = new JTable(tableModel);
        loadBooks(null);  // 전체 도서 로드
        JScrollPane tableScrollPane = new JScrollPane(bookTable);
        add(tableScrollPane, BorderLayout.CENTER);

        // 도서 상세 정보 패널
        bookDetailsArea = new JTextArea();
        bookDetailsArea.setEditable(false);
        JScrollPane detailsScrollPane = new JScrollPane(bookDetailsArea);
        detailsScrollPane.setPreferredSize(new Dimension(300, 0));

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(detailsScrollPane, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel(new FlowLayout());
        JButton borrowButton = new JButton("대여");
        JButton returnButton = new JButton("반납");
        actionPanel.add(borrowButton);
        actionPanel.add(returnButton);
        rightPanel.add(actionPanel, BorderLayout.SOUTH);

        add(rightPanel, BorderLayout.EAST);

        // 버튼 리스너 추가
        searchButton.addActionListener(new SearchActionListener());
        bookTable.getSelectionModel().addListSelectionListener(e -> showBookDetails());

        // 카테고리 버튼 리스너 추가
        allBooksButton.addActionListener(e -> loadBooks(null));
        category1Button.addActionListener(e -> loadBooks("프로그래밍"));
        category2Button.addActionListener(e -> loadBooks("자기계발"));

        // 대여 버튼 이벤트 처리
        borrowButton.addActionListener(e -> {
            if (selectedBook != null && !selectedBook.isBorrowed()) {
                borrowBook(selectedBook);
            } else {
                JOptionPane.showMessageDialog(this, "대여할 수 없는 도서입니다.");
            }
        });

        // 반납 버튼 이벤트 처리
        returnButton.addActionListener(e -> {
            if (selectedBook != null && selectedBook.isBorrowed()) {
                returnBook(selectedBook);
            } else {
                JOptionPane.showMessageDialog(this, "반납할 수 없는 도서입니다.");
            }
        });
    }

    // 검색 기능
    private class SearchActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String query = searchField.getText().toLowerCase();
            loadBooks(query);
        }
    }

    // 도서 목록 로딩 (카테고리 또는 검색어 필터링)
    private void loadBooks(String filter) {
        tableModel.setRowCount(0);  // 기존 테이블 데이터 초기화

        for (Book book : bookList) {
            if (filter == null || book.getTitle().toLowerCase().contains(filter) ||
                    book.getAuthor().toLowerCase().contains(filter) || book.getCategory().toLowerCase().contains(filter)) {
                tableModel.addRow(new Object[]{book.getBookId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getReturnDate(), book.getTotalCopies(), book.getAvailableCopies()});
            }
        }
    }

    // 도서 상세 정보 표시
    private void showBookDetails() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow != -1) {
            String title = (String) bookTable.getValueAt(selectedRow, 1);
            selectedBook = getBookByTitle(title);

            if (selectedBook != null) {
                bookDetailsArea.setText("제목: " + selectedBook.getTitle() +
                        "\n저자: " + selectedBook.getAuthor() +
                        "\n카테고리: " + selectedBook.getCategory() +
                        "\n반납기간: " + selectedBook.getReturnDate() +
                        "\n총 개수: " + selectedBook.getTotalCopies() +
                        "\n대여 상태: " + (selectedBook.isBorrowed() ? "대여 중" : "대여 가능"));
            }
        }
    }

    private Book getBookByTitle(String title) {
        for (Book book : bookList) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    private void borrowBook(Book book) {
        String borrower = JOptionPane.showInputDialog(this, "대여자 이름을 입력하세요:");
        if (borrower != null && !borrower.trim().isEmpty()) {
            book.setBorrowed(true);
            String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            borrowRecords.add(new BorrowRecord(book, borrower, currentDate));
            loadBooks(null);
            showBookDetails(); // 대여 상태를 업데이트
            JOptionPane.showMessageDialog(this, book.getTitle() + "가 대여되었습니다.");
        }
    }

    private void returnBook(Book book) {
        book.setBorrowed(false);
        loadBooks(null);
        showBookDetails(); // 반납 상태를 업데이트
        JOptionPane.showMessageDialog(this, book.getTitle() + "가 반납되었습니다.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SimpleLibraryApp().setVisible(true);
        });
    }
}
