import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardFrame extends JFrame{
    private String username;
    public DashboardFrame(String username){
        this.username = username;
        setTitle("User Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Hi, Welcome " + username + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(welcomeLabel, BorderLayout.NORTH);

        JButton logTicketButton= new JButton("Log a Ticket");
        logTicketButton.setFont(new Font("Ariel",Font.PLAIN, 16));
        logTicketButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){ 
            new LogTicketFrame(username).setVisible(true);
            }
        });

        JButton calculateMarks=new JButton("Calculate end sem Marks");
        calculateMarks.setFont(new Font("Arial", Font.PLAIN, 16));
        calculateMarks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    MarksInputPanel inputPanel = new MarksInputPanel();
                    inputPanel.setVisible(true);
            });}
        });
        JPanel panel = new JPanel();
        panel.add(logTicketButton);
        panel.add(calculateMarks);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

}
