import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class StudentInfoFrame extends JFrame {
    private JTextField nameField;
    private JTextField rollNumberField;
    private JTextField classField;

    public StudentInfoFrame() {
        super("Student Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Using absolute positioning
        getContentPane().setBackground(new Color(190, 209, 207));

        // Adding first logo
        ImageIcon logoIcon1 = new ImageIcon("C:/pp_prog/ChrisisAlert.png"); // Adjust path to your logo
        int logoWidth1 = 120;  // Adjust the width as needed
        int logoHeight1 = (int) (logoWidth1 * ((double) logoIcon1.getIconHeight() / logoIcon1.getIconWidth()));
        JLabel logoLabel1 = new JLabel(new ImageIcon(logoIcon1.getImage().getScaledInstance(logoWidth1, logoHeight1, Image.SCALE_SMOOTH)));
        int logo1PosX = 20;
        int logo1PosY = 20;
        logoLabel1.setBounds(logo1PosX, logo1PosY, logoWidth1, logoHeight1);
        add(logoLabel1);

        // Adding second logo
        ImageIcon logoIcon2 = new ImageIcon("C:/pp_prog/uni logo.png"); // Adjust path to your second logo
        int logoWidth2 = 120;  // Adjust the width as needed
        int logoHeight2 = (int) (logoWidth2 * ((double) logoIcon2.getIconHeight() / logoIcon2.getIconWidth()));
        JLabel logoLabel2 = new JLabel(new ImageIcon(logoIcon2.getImage().getScaledInstance(logoWidth2, logoHeight2, Image.SCALE_SMOOTH)));
        int logo2PosX = logo1PosX + logoHeight2 + 35; // Align horizontally with the first logo
        int logo2PosY = logo1PosY + 40; // Place below the first logo with some spacing
        logoLabel2.setBounds(logo2PosX, logo2PosY, logoWidth2, logoHeight2);
        add(logoLabel2);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, logo1PosY + logoHeight1 + 20, 80, 20);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(110, logo1PosY + logoHeight1 + 20, 200, 20);
        add(nameField);

        JLabel rollNumberLabel = new JLabel("Roll Number:");
        rollNumberLabel.setBounds(20, logo1PosY + logoHeight1 + 50, 80, 20);
        add(rollNumberLabel);

        rollNumberField = new JTextField();
        rollNumberField.setBounds(110, logo1PosY + logoHeight1 + 50, 200, 20);
        add(rollNumberField);

        JLabel classLabel = new JLabel("Class:");
        classLabel.setBounds(20, logo1PosY + logoHeight1 + 80, 80, 20);
        add(classLabel);

        classField = new JTextField();
        classField.setBounds(110, logo1PosY + logoHeight1 + 80, 200, 20);
        add(classField);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(150, logo1PosY + logoHeight1 + 120, 100, 30);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String rollNumber = rollNumberField.getText();
                String className = classField.getText();

                dispose(); // Close this frame
                MarksEntryFrame marksFrame = new MarksEntryFrame(name, rollNumber, className);
                marksFrame.setVisible(true);
            }
        });
        add(submitButton);

        setSize(400, logo1PosY + logoHeight1 + 200); // Adjust size as needed
        setLocationRelativeTo(null); // Center the frame
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentInfoFrame frame = new StudentInfoFrame();
            frame.setVisible(true);
        });
    }
}

class MarksEntryFrame extends JFrame {
    private JTextField[][] textFields;
    private JCheckBox[][] checkBoxes;
    private boolean isDarkMode = false;
    private String name;
    private String rollNumber;
    private String className;

    public MarksEntryFrame(String name, String rollNumber, String className) {
        super("Marks Entry Frame");
        this.name = name;
        this.rollNumber = rollNumber;
        this.className = className;
        getContentPane().setBackground(new Color(190, 209, 207));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Using absolute positioning

        // Adding first logo
        ImageIcon logoIcon1 = new ImageIcon("C:/pp_prog/ChrisisAlert.png"); // Adjust path to your logo
        int logoWidth1 = 120;  // Adjust the width as needed
        int logoHeight1 = (int) (logoWidth1 * ((double) logoIcon1.getIconHeight() / logoIcon1.getIconWidth()));
        JLabel logoLabel1 = new JLabel(new ImageIcon(logoIcon1.getImage().getScaledInstance(logoWidth1, logoHeight1, Image.SCALE_SMOOTH)));
        int logo1PosX = 20;
        int logo1PosY = 20;
        logoLabel1.setBounds(logo1PosX, logo1PosY, logoWidth1, logoHeight1);
        add(logoLabel1);

        // Adding second logo
        ImageIcon logoIcon2 = new ImageIcon("C:/pp_prog/uni logo.png"); // Adjust path to your second logo
        int logoWidth2 = 120;  // Adjust the width as needed
        int logoHeight2 = (int) (logoWidth2 * ((double) logoIcon2.getIconHeight() / logoIcon2.getIconWidth()));
        JLabel logoLabel2 = new JLabel(new ImageIcon(logoIcon2.getImage().getScaledInstance(logoWidth2, logoHeight2, Image.SCALE_SMOOTH)));
        int logo2PosX = logo1PosX + logoHeight2 + 35; // Align horizontally with the first logo
        int logo2PosY = logo1PosY + 40; // Place below the first logo with some spacing
        logoLabel2.setBounds(logo2PosX, logo2PosY, logoWidth2, logoHeight2);
        add(logoLabel2);
        // Inside the constructor of MarksEntryFrame
        JLabel nameLabel = new JLabel("Hi! " + name);
        nameLabel.setBounds(20, logo1PosY + logoHeight1 + 20, 200, 20);
        add(nameLabel);

        JLabel classRollLabel = new JLabel("Class: " + className + "     Reg Number: " + rollNumber);
        classRollLabel.setBounds(20, logo1PosY + logoHeight1 + 50, 300, 20);
        add(classRollLabel);

        String[] labels = {"CIA1", "CIA2", "CIA3", "MIDSEM LAB", "END SEM LAB", "ATTENDANCE", "CREDIT","TARGET"};

        textFields = new JTextField[6][labels.length]; // Assuming there are 6 subjects
        checkBoxes = new JCheckBox[6][labels.length]; // Assuming there are 6 subjects

        // Adding labels for each column
        int labelPosY = Math.max(logo1PosY + logoHeight1, logo2PosY + logoHeight2 + 100) + 20;
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setBounds((i + 1) * 120, labelPosY, 100, 20);
            add(label);
        }

        // Adding text fields and checkboxes for each subject and mark type
        int fieldPosY = labelPosY + 30;
        for (int i = 0; i < 6; i++) { // Assuming there are 6 subjects
            JTextField subjectTextField = new JTextField("Subject " + (i + 1)); // Default subject names
            subjectTextField.setBounds(5, fieldPosY + i * 30, 100, 20);
		 subjectTextField.setEditable(false);
            add(subjectTextField);

            for (int j = 0; j < labels.length; j++) {
                textFields[i][j] = new JTextField();
                textFields[i][j].setBounds((j + 1) * 120, fieldPosY + i * 30, 50, 20);
                add(textFields[i][j]);

                // Checkboxes are added only for marks fields (excluding target)
                if (!labels[j].equals("TARGET")&& !labels[j].equals("CREDIT")) {
                    checkBoxes[i][j] = new JCheckBox();
                    checkBoxes[i][j].setBounds((j + 1) * 120 + 55, fieldPosY + i * 30, 20, 20);
                    add(checkBoxes[i][j]);
                }
            }
        }

        // Adding submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(650, fieldPosY + 6 * 30 + 20, 120, 30); // Assuming there are 6 subjects
        submitButton.addActionListener(new ActionListener() {
            @Override
          public void actionPerformed(ActionEvent e) {
    // Create a new frame for displaying submitted values
    JFrame resultFrame = new JFrame("Submitted Values");
    resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    resultFrame.setLayout(new BorderLayout());

    // Create a table model
    DefaultTableModel tableModel = new DefaultTableModel();
    tableModel.addColumn("Subject");
    for (String label : labels) {
        tableModel.addColumn(label);
    }

    for (int i = 0; i < 6; i++) { // Assuming there are 6 subjects
        Object[] rowData = new Object[labels.length + 1];
        rowData[0] = "Subject " + (i + 1); // Subject names

        for (int j = 0; j < labels.length; j++) {
            if (labels[j].equals("TARGET") || labels[j].equals("CREDIT")) {
                rowData[j + 1] = textFields[i][j].getText();
            } else {
                if (checkBoxes[i][j] != null && checkBoxes[i][j].isSelected()) {
                    rowData[j + 1] = textFields[i][j].getText();
                } else {
                    rowData[j + 1] = "";
                }
            }
        }
        tableModel.addRow(rowData);
    }

    // Adding target value
    resultFrame.add(new JScrollPane(new JTable(tableModel)));
    resultFrame.pack();
    resultFrame.setVisible(true);



                // Create a JTable with the model
                JTable table = new JTable(tableModel);

                // Add the table to a scroll pane and add it to the frame
                JScrollPane scrollPane = new JScrollPane(table);
                resultFrame.add(scrollPane, BorderLayout.CENTER);

                // Add buttons for confirmation and editing
                JPanel buttonPanel = new JPanel();
                JButton confirmButton = new JButton("Confirm and Display Results");
                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Perform calculations and display results
                        // For now, let's just close the result frame
                        resultFrame.dispose();
                    }
                });
                buttonPanel.add(confirmButton);

                JButton editButton = new JButton("Edit Values");
                editButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Close the result frame
                        resultFrame.dispose();
                        // Re-enable editing of text fields
                        enableTextFields(true);
                    }
                });
                buttonPanel.add(editButton);

                resultFrame.add(buttonPanel, BorderLayout.SOUTH);

                // Show the result frame
                resultFrame.pack();
                resultFrame.setVisible(true);

                // Disable editing of text fields after submission
                enableTextFields(false);
            }
        });
        add(submitButton);

        // Adding light/dark mode toggle button
        JButton modeSwitchButton = new JButton();
        modeSwitchButton.setBounds(650, fieldPosY + 6 * 30 + 70, 120, 30); // Assuming there are 6 subjects
        modeSwitchButton.setText("Switch Mode");
        modeSwitchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleMode();
            }
        });
        add(modeSwitchButton);

        setSize(950, fieldPosY + 6 * 30 + 120); // Assuming there are 6 subjects
        setLocationRelativeTo(null); // Center the frame
    }

    private void toggleMode() {
        isDarkMode = !isDarkMode;
        if (isDarkMode) {
            getContentPane().setBackground(new Color(10, 69, 62)); // Dark mode background color
        } else {
            getContentPane().setBackground(new Color(190, 209, 207)); // Beige color
        }
    }

    private void enableTextFields(boolean enable) {
        for (int i = 0; i < textFields.length; i++) {
            for (int j = 0; j < textFields[i].length; j++) {
                if (textFields[i][j] != null) {
                    textFields[i][j].setEditable(enable);
                }
            }
        }
    }
}