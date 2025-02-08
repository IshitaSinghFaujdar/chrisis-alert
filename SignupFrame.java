import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignupFrame extends JFrame {

    JTextField usernameField, emailField, securityAnswerField,customQuestionField;
    JPasswordField passwordField;
    JComboBox<String> securityQuestionBox;
    JButton registerButton, backButton;

    SignupFrame() {
        setLayout(null);
        setTitle("Sign Up");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 50, 100, 30);
        usernameField = new JTextField();
        usernameField.setBounds(200, 50, 200, 30);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 100, 100, 30);
        passwordField = new JPasswordField();
        passwordField.setBounds(200, 100, 200, 30);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 150, 100, 30);
        emailField = new JTextField();
        emailField.setBounds(200, 150, 200, 30);

        JLabel securityQLabel = new JLabel("Security Question:");
        securityQLabel.setBounds(50, 200, 150, 30);
        String[] ques = {"What is your pet’s name?", "What is your favorite book?", "What is your birth city?","Custom Question"};
        securityQuestionBox = new JComboBox<>(ques);
        securityQuestionBox.setBounds(200, 200, 200, 30);
        securityQuestionBox.setSelectedIndex(0);
        securityQuestionBox.repaint(); 
        securityQuestionBox.revalidate();
        add(securityQuestionBox);
        
        customQuestionField = new JTextField();
        customQuestionField.setBounds(200, 240, 200, 30);
        customQuestionField.setVisible(false);
        securityQuestionBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (securityQuestionBox.getSelectedItem().equals("Custom Question")) {
                    customQuestionField.setVisible(true);
                } else {
                    customQuestionField.setVisible(false);
                }
                // Force UI to refresh
                revalidate();
                repaint();
            }
        });
        add(customQuestionField);
        
        


        JLabel securityAnsLabel = new JLabel("Answer:");
        securityAnsLabel.setBounds(50,290,150,30);
        securityAnswerField = new JTextField();
        securityAnswerField.setBounds(200, 290, 200, 30);

        registerButton = new JButton("Register");
        registerButton.setBounds(100, 370, 100, 40);
        registerButton.setBackground(new Color(30, 174, 39));
        registerButton.setForeground(Color.white);

        backButton = new JButton("Back");
        backButton.setBounds(250, 370, 100, 40);
        backButton.setBackground(new Color(6, 25, 84));
        backButton.setForeground(Color.white);

        // Add components
        add(userLabel);
        add(usernameField);
        add(passLabel);
        add(passwordField);
        add(emailLabel);
        add(emailField);
        add(securityQLabel);
        add(securityAnsLabel);
        add(securityAnswerField);
        add(registerButton);
        add(backButton);

        setVisible(true);

        // Register button action
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });

        // Back button action
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close signup window
            }
        });

    }
    private void registerUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String email = emailField.getText();
        String securityQuestion;
        String securityAnswer = securityAnswerField.getText();
    
        if (securityQuestionBox.getSelectedItem().equals("Custom Question")) {
            securityQuestion = customQuestionField.getText();
        } else {
            securityQuestion = securityQuestionBox.getSelectedItem().toString();
        }
    
        String hashedPassword = HashUtil.hashPassword(password);
    
        // Validate fields
        if (username.isEmpty() || password.isEmpty() || email.isEmpty() || securityAnswer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "❌ All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        if (username.contains(" ")) {
            JOptionPane.showMessageDialog(this, "❌ Username cannot contain spaces!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement("SELECT COUNT(*) FROM users WHERE username = ?");
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, password, email, security_question, security_answer) VALUES (?, ?, ?, ?, ?)")) {
    
            if (conn == null) {
                JOptionPane.showMessageDialog(this, "Database connection failed!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // Check if username already exists
            checkStmt.setString(1, username);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(this, "❌ Username already taken! Choose another.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
    
            // Insert the new user
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            stmt.setString(3, email);
            stmt.setString(4, securityQuestion);
            stmt.setString(5, securityAnswer);
    
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "✅ Signup Successful!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Signup failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
    
            // Debugging outputs
            System.out.println("Username: " + username);
            System.out.println("Email: " + email);
            System.out.println("Security Question: " + securityQuestion);
            System.out.println("Security Answer: " + securityAnswer);
            System.out.println("Password (Hashed): " + hashedPassword);
    
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "❌ Error registering user.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }}

   