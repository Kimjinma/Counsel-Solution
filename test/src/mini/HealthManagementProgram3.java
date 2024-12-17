package mini;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HealthManagementProgram3 extends JFrame {
    private JLabel photo1Label;
    private JLabel photo2Label;
    private JTextField dateField;
    private JTextField countField;
    private JTextField timeField;
    private JTable exerciseTable;
    private DefaultListModel<String> exerciseListModel;
    private String selectedExercise;
    private static String username;

    // 개인정보 필드
    private JTextField nameField;
    private JTextField ageField;
    private JTextField heightField;
    private JTextField weightField;
    private JTextField bmiField;

    // MySQL DB 연결 정보
    private static final String DB_URL = "jdbc:mysql://localhost:3306/health_management";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "0000";

    public HealthManagementProgram3(String username) {
        this.username = username;
        setTitle("건강 관리 프로그램");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // JTabbedPane 생성
        JTabbedPane tabbedPane = new JTabbedPane();

        // 운동 탭
        JPanel exercisePanel = createExercisePanel();
        tabbedPane.addTab("운동", exercisePanel);

        // 운동 정보 탭
        JPanel exerciseInfoPanel = createExerciseInfoPanel();
        tabbedPane.addTab("운동 정보", exerciseInfoPanel);

        // 식단 탭
        JPanel dietPanel = createDietPanel();
        tabbedPane.addTab("식단", dietPanel);

        // 개인정보 탭
        JPanel personalInfoPanel = createPersonalInfoPanel();
        tabbedPane.addTab("개인 정보", personalInfoPanel);

        // 문의사항 탭
        JPanel inquiryPanel = createInquiryPanel();
        tabbedPane.addTab("문의사항", inquiryPanel);

        // 개인정보 탭 선택 시 데이터를 불러오도록 ChangeListener 추가
        tabbedPane.addChangeListener(e -> {
            JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
            int selectedIndex = sourceTabbedPane.getSelectedIndex();
            if (selectedIndex == 3) { // "개인 정보" 탭을 선택
                fetchPersonalInfo();
            }
        });

        add(tabbedPane);
        setVisible(true);
    }

    // 식단 탭 패널 생성 메소드
    public JPanel createDietPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // 중앙 패널: 날짜, 식사 종류, 음식 이름, 칼로리 입력
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 2));

        centerPanel.add(new JLabel("날짜를 입력하세요 ex)YYYY-MM-DD:"));
        JTextField dietDateField = new JTextField(new SimpleDateFormat("yyyy-MM-dd").format(new Date())); // 현재 날짜로 기본값 설정
        centerPanel.add(dietDateField);

        centerPanel.add(new JLabel("식사 종류를 입력하세요 (아침/점심/저녁/간식):"));
        JTextField mealTypeField = new JTextField();
        centerPanel.add(mealTypeField);

        centerPanel.add(new JLabel("음식 이름을 입력하세요:"));
        JTextField foodNameField = new JTextField();
        centerPanel.add(foodNameField);

        centerPanel.add(new JLabel("칼로리를 입력하세요 (kcal):"));
        JTextField calorieField = new JTextField();
        centerPanel.add(calorieField);

        panel.add(centerPanel, BorderLayout.CENTER);

        // 하단 버튼
        JButton saveButton = new JButton("저장");
        saveButton.addActionListener(e -> {
            String dietDate = dietDateField.getText();
            String mealType = mealTypeField.getText();
            String foodName = foodNameField.getText();
            String calories = calorieField.getText();

            if (dietDate.isEmpty() || mealType.isEmpty() || foodName.isEmpty() || calories.isEmpty()) {
                JOptionPane.showMessageDialog(this, "모든 필드를 입력하세요.");
                return;
            }

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement(
                         "INSERT INTO diet (diet_date, meal_type, food_name, calories, username)"
                                 + " VALUES (?, ?, ?, ?, ?)")) {
                stmt.setDate(1, java.sql.Date.valueOf(dietDate));
                stmt.setString(2, mealType);
                stmt.setString(3, foodName);
                stmt.setInt(4, Integer.parseInt(calories));
                stmt.setString(5, username);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "식단 정보가 저장되었습니다.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "데이터 저장 중 오류가 발생했습니다.");
            }
        });
        panel.add(saveButton, BorderLayout.SOUTH);

        return panel;
    }

    // 운동 탭 패널 생성 메소드
    public JPanel createExercisePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // 왼쪽 리스트
        exerciseListModel = new DefaultListModel<>();
        exerciseListModel.addElement("팔굽혀펴기");
        exerciseListModel.addElement("윗몸일으키기");
        exerciseListModel.addElement("스쿼트");
        JList<String> exerciseList = new JList<>(exerciseListModel);
        exerciseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        exerciseList.addListSelectionListener(e -> selectedExercise = exerciseList.getSelectedValue());
        exerciseList.addListSelectionListener(e -> updatePhotos(exerciseList.getSelectedValue()));
        panel.add(new JScrollPane(exerciseList), BorderLayout.WEST);

        // 중앙 패널: 사진 및 날짜/횟수/시간 입력
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 2));

        photo1Label = new JLabel();
        photo2Label = new JLabel();
        photo1Label.setHorizontalAlignment(JLabel.CENTER);
        photo2Label.setHorizontalAlignment(JLabel.CENTER);
        photo1Label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        photo2Label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        centerPanel.add(photo1Label);
        centerPanel.add(photo2Label);

        centerPanel.add(new JLabel("오늘 날짜를 입력하세요 ex)YYYY-MM-DD:"));
        dateField = new JTextField(new SimpleDateFormat("yyyy-MM-dd").format(new Date())); // 현재 날짜로 기본값 설정
        centerPanel.add(dateField);

        centerPanel.add(new JLabel("오늘 운동한 횟수를 입력하세요:"));
        countField = new JTextField();
        centerPanel.add(countField);

        centerPanel.add(new JLabel("운동 시간을 입력하세요 (분 단위):"));
        timeField = new JTextField();
        centerPanel.add(timeField);

        panel.add(centerPanel, BorderLayout.CENTER);

        // 하단 버튼
        JButton button = new JButton("저장");
        button.addActionListener(e -> saveExerciseData());
        panel.add(button, BorderLayout.SOUTH);

        return panel;
    }

    // 운동 사진 업데이트 메소드
    public void updatePhotos(String exercise) {
        if (exercise != null) {
            switch (exercise) {
                case "팔굽혀펴기":
                    photo1Label.setIcon(new ImageIcon("push1.png"));
                    photo2Label.setIcon(new ImageIcon("push2.png"));
                    break;
                case "윗몸일으키기":
                    photo1Label.setIcon(new ImageIcon("situp1.png"));
                    photo2Label.setIcon(new ImageIcon("situp2.png"));
                    break;
                case "스쿼트":
                    photo1Label.setIcon(new ImageIcon("squat1.png"));
                    photo2Label.setIcon(new ImageIcon("squat2.png"));
                    break;
                default:
                    photo1Label.setIcon(null);
                    photo2Label.setIcon(null);
            }
        } else {
            photo1Label.setIcon(null);
            photo2Label.setIcon(null);
        }
    }

    // 운동 데이터 저장 메소드
    public void saveExerciseData() {
        String date = dateField.getText();
        String count = countField.getText();
        String time = timeField.getText();

        if (selectedExercise == null || date.isEmpty() || count.isEmpty() || time.isEmpty()) {
            JOptionPane.showMessageDialog(this, "모든 필드를 입력하세요.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO exercises2 (exercise_name, exercise_date, exercise_count, exercise_time, username)"
                             + " VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, selectedExercise);
            stmt.setDate(2, java.sql.Date.valueOf(String.valueOf(date)));
            stmt.setInt(3, Integer.parseInt(count));
            stmt.setInt(4, Integer.parseInt(time));
            stmt.setString(5, username);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "운동 데이터가 저장되었습니다.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "데이터 저장 중 오류가 발생했습니다.");
        }
    }

    // 운동 정보 탭 패널 생성 메소드
    public JPanel createExerciseInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        String[] columnNames = {"운동", "날짜", "횟수", "운동 시간(분)"};
        Object[][] data = {};

        exerciseTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(exerciseTable);

        JButton fetchButton = new JButton("불러오기");
        fetchButton.addActionListener(e -> fetchExerciseData());

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(fetchButton, BorderLayout.SOUTH);

        return panel;
    }

    // 운동 데이터 불러오기 메소드
    public void fetchExerciseData() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM exercises2 WHERE username = ? ORDER BY id DESC")) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            Object[][] data = new Object[30][4]; // 최대 30개의 데이터를 담을 수 있도록 배열 크기 설정
            int rowIndex = 0;
            while (rs.next()) {
                data[rowIndex][0] = rs.getString("exercise_name");
                data[rowIndex][1] = rs.getDate("exercise_date");
                data[rowIndex][2] = rs.getInt("exercise_count");
                data[rowIndex][3] = rs.getInt("exercise_time"); // 운동 시간 열 추가
                rowIndex++;
            }

            String[] columnNames = {"운동", "날짜", "횟수", "운동 시간(분)"};
            exerciseTable.setModel(new DefaultTableModel(data, columnNames));
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "데이터 불러오기 중 오류가 발생했습니다.");
        }
    }

    // 개인정보 탭 패널 생성 메소드
    public JPanel createPersonalInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // 좌측 패널
        JPanel leftPanel = new JPanel(new GridLayout(5, 1));
        JLabel nameLabel = new JLabel("이름:");
        JLabel ageLabel = new JLabel("나이:");
        JLabel heightLabel = new JLabel("키:");
        JLabel weightLabel = new JLabel("몸무게:");
        JLabel bmiLabel = new JLabel("BMI:");

        leftPanel.add(nameLabel);
        leftPanel.add(ageLabel);
        leftPanel.add(heightLabel);
        leftPanel.add(weightLabel);
        leftPanel.add(bmiLabel);

        // 우측 패널
        JPanel rightPanel = new JPanel(new GridLayout(5, 1));
        nameField = new JTextField(20);
        ageField = new JTextField(20);
        heightField = new JTextField(20);
        weightField = new JTextField(20);
        bmiField = new JTextField(20);

        rightPanel.add(nameField);
        rightPanel.add(ageField);
        rightPanel.add(heightField);
        rightPanel.add(weightField);
        rightPanel.add(bmiField);

        nameField.setEditable(false);
        ageField.setEditable(false);
        heightField.setEditable(false);
        weightField.setEditable(false);
        bmiField.setEditable(false);

        // 패널 구성
        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(rightPanel, BorderLayout.CENTER);

        return panel;
    }

    // DB에서 개인정보 불러오는 메소드
    public void fetchPersonalInfo() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM members WHERE username = ?")) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nameField.setText(rs.getString("name"));
                ageField.setText(rs.getString("age"));
                heightField.setText(rs.getString("height"));
                weightField.setText(rs.getString("weight"));
                bmiField.setText(rs.getString("bmi"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // 문의사항 탭 패널 생성 메소드
    public JPanel createInquiryPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTextArea inquiryArea = new JTextArea();
        panel.add(new JScrollPane(inquiryArea), BorderLayout.CENTER);

        JButton saveButton = new JButton("저장");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inquiryText = inquiryArea.getText();
                if (inquiryText.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "문의사항을 입력하세요.");
                } else {
                    JOptionPane.showMessageDialog(panel, "문의사항이 저장되었습니다.");
                    // DB 저장 로직을 여기에 추가할 수 있습니다.
                }
            }
        });

        panel.add(saveButton, BorderLayout.SOUTH);

        return panel;
    }

    public static void main(String[] args) {
        new HealthManagementProgram3(username);
    }
}
