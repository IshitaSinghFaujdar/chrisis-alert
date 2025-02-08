import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ResetPassword extends JFrame {

    JTextField usernameField, securityAnswerField;
    JPasswordField newPasswordField, confirmPasswordField;
    JLabel securityQuestionLabel;
    JButton verifyButton, resetButton, backButton;

    private Connection conn; // Maintain a single connection instance

    ResetPassword() {
        setLayout(null);
        setTitle("Reset Password");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 50, 100, 30);
        usernameField = new JTextField();
        usernameField.setBounds(200, 50, 200, 30);

        JButton fetchQuestionButton = new JButton("Get Security Question");
        fetchQuestionButton.setBounds(150, 90, 200, 30);
        
        securityQuestionLabel = new JLabel("");
        securityQuestionLabel.setBounds(50, 130, 400, 30);

        JLabel securityAnsLabel = new JLabel("Answer:");
        securityAnsLabel.setBounds(50, 170, 100, 30);
        securityAnswerField = new JTextField();
        securityAnswerField.setBounds(200, 170, 200, 30);

        verifyButton = new JButton("Verify");
        verifyButton.setBounds(150, 210, 100, 30);
        
        JLabel newPassLabel = new JLabel("New Password:");
        newPassLabel.setBounds(50, 260, 120, 30);
        newPasswordField = new JPasswordField();
        newPasswordField.setBounds(200, 260, 200, 30);
        newPasswordField.setEnabled(false); // Disabled initially

        JLabel confirmPassLabel = new JLabel("Confirm Password:");
        confirmPassLabel.setBounds(50, 300, 150, 30);
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(200, 300, 200, 30);
        confirmPasswordField.setEnabled(false);

        resetButton = new JButton("Reset Password");
        resetButton.setBounds(100, 350, 150, 40);
        resetButton.setBackground(new Color(30, 174, 39));
        resetButton.setForeground(Color.white);
        resetButton.setEnabled(false);

        backButton = new JButton("Back");
        backButton.setBounds(270, 350, 100, 40);
        backButton.setBackground(new Color(6, 25, 84));
        backButton.setForeground(Color.white);

        // Add components
        add(userLabel);
        add(usernameField);
        add(fetchQuestionButton);
        add(securityQuestionLabel);
        add(securityAnsLabel);
        add(securityAnswerField);
        add(verifyButton);
        add(newPassLabel);
        add(newPasswordField);
        add(confirmPassLabel);
        add(confirmPasswordField);
        add(resetButton);
        add(backButton);

        setVisible(true);

        // Fetch security question
        fetchQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchSecurityQuestion();
            }
        });

        // Verify security answer
        verifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifyAnswer();
            }
        });

        // Reset password action
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetPassword();
            }
        });

        // Back button action
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeConnection(); // Ensure connection is closed
                dispose();
            }
        });
    }

    private void fetchSecurityQuestion() {
        String username = usernameField.getText();
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter username first!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            conn = DBConnection.getConnection(); // Establish connection only once
            String query = "SELECT security_question FROM users WHERE username = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        securityQuestionLabel.setText("Security Question: " + rs.getString("security_question"));
                    } else {
                        JOptionPane.showMessageDialog(this, "Username not found!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving security question.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verifyAnswer() {
        if (conn == null) {
            conn = DBConnection.getConnection(); // Reconnect if needed
        }

        String username = usernameField.getText();
        String answer = securityAnswerField.getText();

        if (answer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter security answer!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String query = "SELECT security_answer FROM users WHERE username = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String correctAnswer = rs.getString("security_answer");
                    if (correctAnswer.equalsIgnoreCase(answer)) {
                        JOptionPane.showMessageDialog(this, "Security answer verified. Set new password.");
                        newPasswordField.setEnabled(true);
                        confirmPasswordField.setEnabled(true);
                        resetButton.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Incorrect answer!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error verifying answer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetPassword() {
        if (conn == null) {
            conn = DBConnection.getConnection(); // Reconnect if needed
        }

        String username = usernameField.getText();
        String newPassword = new String(newPasswordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter new password!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String query = "UPDATE users SET password = ? WHERE username = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newPassword); // Ideally, hash the password before storing
            stmt.setString(2, username);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Password reset successfully!");
                closeConnection(); // Close connection after use
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error resetting password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ResetPassword();
    }
}
