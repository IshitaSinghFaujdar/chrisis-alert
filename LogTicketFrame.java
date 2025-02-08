import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LogTicketFrame extends JFrame {
    private String username;
    private JTextArea issueDescription;
    private JButton submitButton, cancelButton;

    public LogTicketFrame(String username) {
        this.username = username;
        setTitle("Log a Ticket");
        setSize(400, 300);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel("Describe your issue:");
        issueDescription = new JTextArea(5, 30);
        JScrollPane scrollPane = new JScrollPane(issueDescription);

        submitButton = new JButton("Submit Ticket");
        cancelButton = new JButton("Cancel");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitTicket();
            }
        });

        cancelButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);

        add(label, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void submitTicket() {
    String issueText = issueDescription.getText().trim();
    if (issueText.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter a description!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = DBConnection.getConnection();

        //user_id based on username
        String userQuery = "SELECT id FROM users WHERE username = ?";
        stmt = conn.prepareStatement(userQuery);
        stmt.setString(1, username);
        rs = stmt.executeQuery();

        if (rs.next()) {
            int userId = rs.getInt("id");

            //  ticket with user_id
            String ticketQuery = "INSERT INTO tickets (user_id, username, issue, status) VALUES (?, ?, ?, 'Open')";
            stmt = conn.prepareStatement(ticketQuery);
            stmt.setInt(1, userId);
            stmt.setString(2, username);
            stmt.setString(3, issueText);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Ticket submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        } else {
            JOptionPane.showMessageDialog(this, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error submitting ticket!", "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

}
