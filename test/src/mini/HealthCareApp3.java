package mini;


import javax.swing.*;


import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class HealthCareApp3 extends JFrame {

    // 로그인 필드
    public JTextField loginUsernameField;
    private JPasswordField loginPasswordField;

    // 회원가입 필드
    private JTextField registerUsernameField, registerNameField, registerAgeField, registerHeightField, registerWeightField;
    private JPasswordField registerPasswordField;
    private JLabel statusLabel; // 상태 레이블 추가

    private static final String DB_URL = "jdbc:mysql://localhost:3306/health_management";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "0000";

    public HealthCareApp3() {
        setTitle("회원 관리 시스템");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        JTabbedPane tabbedPane = new JTabbedPane();

        // 로그인 패널 추가
        JPanel loginPanel = createLoginPanel();
        tabbedPane.addTab("로그인", loginPanel);

        // 회원가입 패널 추가
        JPanel registerPanel = createRegisterPanel();
        tabbedPane.addTab("회원가입", registerPanel);

        add(tabbedPane);
        setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        loginPanel.add(new JLabel("아이디:"), gbc);
        loginUsernameField = new JTextField(20);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        loginPanel.add(loginUsernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        loginPanel.add(new JLabel("비밀번호:"), gbc);
        loginPasswordField = new JPasswordField(20);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        loginPanel.add(loginPasswordField, gbc);

        JButton loginButton = new JButton("로그인");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });
        loginPanel.add(loginButton, gbc);

        return loginPanel;
    }

    private JPanel createRegisterPanel() {
        JPanel registerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        registerPanel.add(new JLabel("아이디:"), gbc);
        registerUsernameField = new JTextField(20);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        registerPanel.add(registerUsernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        registerPanel.add(new JLabel("비밀번호:"), gbc);
        registerPasswordField = new JPasswordField(20);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        registerPanel.add(registerPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        registerPanel.add(new JLabel("이름:"), gbc);
        registerNameField = new JTextField(20);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        registerPanel.add(registerNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        registerPanel.add(new JLabel("나이:"), gbc);
        registerAgeField = new JTextField(20);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        registerPanel.add(registerAgeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        registerPanel.add(new JLabel("키(cm):"), gbc);
        registerHeightField = new JTextField(20);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        registerPanel.add(registerHeightField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.LINE_END;
        registerPanel.add(new JLabel("몸무게(kg):"), gbc);
        registerWeightField = new JTextField(20);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        registerPanel.add(registerWeightField, gbc);

        // 회원가입 버튼
        JButton registerButton = new JButton("등록");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        registerPanel.add(registerButton, gbc);

        // 상태 레이블
        statusLabel = new JLabel(""); // 초기화 추가
        gbc.gridy = 7;
        registerPanel.add(statusLabel, gbc);

        return registerPanel;
    }

    // 회원가입 메서드
    private void registerUser() {
        String username = registerUsernameField.getText();
        String password = new String(registerPasswordField.getPassword());
        String name = registerNameField.getText();
        String ageText = registerAgeField.getText();
        String heightText = registerHeightField.getText();
        String weightText = registerWeightField.getText();

        if (username.isEmpty() || password.isEmpty() || name.isEmpty() || ageText.isEmpty() || heightText.isEmpty() || weightText.isEmpty()) {
            statusLabel.setText("모든 필드를 입력해 주세요.");
            return;
        }

        int age;
        float height,weight;

        try {
            age = Integer.parseInt(ageText);
            height = Float.parseFloat(heightText);
            weight = Float.parseFloat(weightText);
        } catch (NumberFormatException e) {
            statusLabel.setText("나이, 키, 몸무게는 숫자로 입력해 주세요.");
            return;
        }


        // 데이터베이스 연결 및 사용자 정보 저장
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO members (username, password, name, age, height, weight, bmi) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, name);
            statement.setInt(4, age);
            statement.setFloat(5, height);
            statement.setFloat(6, weight);
            statement.setFloat(7, (float) (weight/((height*0.01)*(height*0.01))));
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                statusLabel.setText("회원가입 성공");
            } else {
                statusLabel.setText("회원가입 실패");
            }
        } catch (SQLException e) {
            statusLabel.setText("회원가입에 실패했습니다.");
        }
    }

    public void loginUser() {
        String username = loginUsernameField.getText();
        String password = new String(loginPasswordField.getPassword());

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String sql = "SELECT * FROM members WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                new HealthManagementProgram3(username);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "로그인 실패: 아이디 또는 비밀번호가 잘못되었습니다.");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "로그인 실패: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HealthCareApp3());
    }
}