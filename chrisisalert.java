import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MarksFrame extends JFrame {
    private JTextField[][] textFields;
    private JCheckBox[][] checkBoxes;

    public MarksFrame() {
        super("Marks Entry Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Using absolute positioning

        // Adding first logo
        ImageIcon logoIcon1 = new ImageIcon("C:/pp_prog/chrisislogo.png"); // Adjust path to your logo
        int logoWidth1 = 150;  // Adjust the width as needed
        int logoHeight1 = (int) (logoWidth1 * ((double) logoIcon1.getIconHeight() / logoIcon1.getIconWidth()));
        JLabel logoLabel1 = new JLabel(new ImageIcon(logoIcon1.getImage().getScaledInstance(logoWidth1, logoHeight1, Image.SCALE_SMOOTH)));
        int logo1PosX = 20;
        int logo1PosY = 20;
        logoLabel1.setBounds(logo1PosX, logo1PosY, logoWidth1, logoHeight1);
        add(logoLabel1);

        // Adding second logo
        ImageIcon logoIcon2 = new ImageIcon("C:/pp_prog/uni logo.png"); // Adjust path to your second logo
        int logoWidth2 = 150;  // Adjust the width as needed
        int logoHeight2 = (int) (logoWidth2 * ((double) logoIcon2.getIconHeight() / logoIcon2.getIconWidth()));
        JLabel logoLabel2 = new JLabel(new ImageIcon(logoIcon2.getImage().getScaledInstance(logoWidth2, logoHeight2, Image.SCALE_SMOOTH)));
        int logo2PosX = logo1PosX + logoWidth1 + 20;
        int logo2PosY = 20;
        logoLabel2.setBounds(logo2PosX, logo2PosY, logoWidth2, logoHeight2);
        add(logoLabel2);

        String[] subjects = {"Subj1", "Subj2", "Subj3", "Subj4", "Subj5", "Subj6"};
        String[] labels = {"CIA1", "CIA2", "CIA3", "MIDSEM LAB", "END SEM LAB", "TARGET"};

        textFields = new JTextField[subjects.length][labels.length];
        checkBoxes = new JCheckBox[subjects.length][labels.length];

        // Adding labels for each column
        int labelPosY = Math.max(logo1PosY + logoHeight1, logo2PosY + logoHeight2) + 20;
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setBounds((i + 1) * 120, labelPosY, 100, 20);
            add(label);
        }

        // Adding text fields and checkboxes for each subject and mark type
        int fieldPosY = labelPosY + 30;
        for (int i = 0; i < subjects.length; i++) {
            JLabel subjectLabel = new JLabel(subjects[i]);
            subjectLabel.setBounds(5, fieldPosY + i * 30, 100, 20);
            add(subjectLabel);

            for (int j = 0; j < labels.length; j++) {
                textFields[i][j] = new JTextField();
                textFields[i][j].setBounds((j + 1) * 120, fieldPosY + i * 30, 50, 20);
                add(textFields[i][j]);

                // Checkboxes are added only for marks fields (excluding target)
                if (!labels[j].equals("TARGET")) {
                    checkBoxes[i][j] = new JCheckBox();
                    checkBoxes[i][j].setBounds((j + 1) * 120 + 55, fieldPosY + i * 30, 20, 20);
                    add(checkBoxes[i][j]);
                }
            }
        }

        // Adding submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(650, fieldPosY + subjects.length * 30 + 20, 80, 30); // Moved down after the entry of 6th subject
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitButton.setText("Submitted");
                submitButton.setBackground(Color.GREEN);

                // Adding submission message
                JLabel submissionMessageLabel = new JLabel("Marks submitted. Results will be displayed shortly.");
                submissionMessageLabel.setBounds(20, fieldPosY + subjects.length * 30 + 60, 400, 20);
                add(submissionMessageLabel);
            }
        });
        add(submitButton);

        setSize(950, fieldPosY + subjects.length * 30 + 120); // Adjusting frame height to accommodate logo and spacing
        setLocationRelativeTo(null); // Center the frame
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MarksFrame frame = new MarksFrame();
            frame.setVisible(true);
        });
    }
}
