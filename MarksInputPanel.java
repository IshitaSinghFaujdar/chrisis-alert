import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarksInputPanel extends JFrame {
    
    private JTextField cia1Field, cia2Field, cia3Field, midSemLabField, endSemLabField, attendanceField, creditsField, targetField;
    private JLabel resultLabel;

    public MarksInputPanel() {
        setTitle("Marks Calculator");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(10, 2));
        
        cia1Field = new JTextField(10);
        cia2Field = new JTextField(10);
        cia3Field = new JTextField(10);
        midSemLabField = new JTextField(10);
        endSemLabField = new JTextField(10);
        attendanceField = new JTextField(10);
        creditsField = new JTextField(10);
        targetField = new JTextField(10);
        resultLabel = new JLabel("Result will be displayed here.");

        setLayout(new GridLayout(12, 2));  

        add(new JLabel("CIA1 Marks:"));
        add(cia1Field);

        add(new JLabel("CIA2 Marks:"));
        add(cia2Field);

        add(new JLabel("CIA3 Marks:"));
        add(cia3Field);

        add(new JLabel("Mid Sem Lab Marks:"));
        add(midSemLabField);

        add(new JLabel("End Sem Lab Marks:"));
        add(endSemLabField);

        add(new JLabel("Attendance Marks (out of 5):"));
        add(attendanceField);

        add(new JLabel("Credits:"));
        add(creditsField);

        add(new JLabel("Target Score:"));
        add(targetField);

        add(resultLabel);

        
        JButton submitButton = new JButton("Calculate End Sem Marks");
        submitButton.addActionListener(new SubmitButtonListener());
        add(submitButton);
    }

    
    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                
                double cia1 = Double.parseDouble(cia1Field.getText());
                
                double cia2 = Double.parseDouble(cia2Field.getText());
                double cia3 = Double.parseDouble(cia3Field.getText());
                double midSemLab = Double.parseDouble(midSemLabField.getText());
                double endSemLab = Double.parseDouble(endSemLabField.getText());
                int attendance = Integer.parseInt(attendanceField.getText());
                int credits = Integer.parseInt(creditsField.getText());
                double target = Double.parseDouble(targetField.getText());
                StringBuilder errorMessage = new StringBuilder();

            if (cia1 < 0 || cia1 > 20 || cia2 < 0 || cia2 > 50 || cia3 < 0 || cia3 > 20 ||
                midSemLab < 0 || midSemLab > 50 || endSemLab < 0 || endSemLab > 50) {
                errorMessage.append("❌ One or more values of marks are invalid.\n");
            }

            if (credits < 2 || credits > 4) {
                errorMessage.append("❌ Credit range must be between 2-4.\n");
            }

            if ((target > 50 && credits == 2) || (target < 40 && credits >= 3)) {
                errorMessage.append("❌ Target doesn't match with the credit criteria.\n");
            }
            if (errorMessage.length() > 0) {
                resultLabel.setText("<html>" + errorMessage.toString().replace("\n", "<br>") + "</html>");
                return; // Stops further calculations
            }

                
                double requiredEndSemMarks = Utils.calculateEndSemMarks(cia1, cia2, cia3, midSemLab, endSemLab, attendance, credits, target);
                double finalEndSemMarks = 0;
                if (credits == 4) {
                    
                    if(finalEndSemMarks>100){
                        
                        resultLabel.setText("<html><br>❗You cannot achieve the target.</html>");
                        return;

                    }  
                    finalEndSemMarks = (requiredEndSemMarks * 100) / 30;  
                } else if (credits == 3) {
                    if(finalEndSemMarks>100){
                        
                        resultLabel.setText("<html><br>❗You cannot achieve the target.</html>");
                        return;

                    }  
                    
                    finalEndSemMarks = requiredEndSemMarks*2;  
                } else if (credits == 2) {
                    
                    finalEndSemMarks = (requiredEndSemMarks*2);
                    if(finalEndSemMarks>50){
                        
                        resultLabel.setText("<html><br>❗You cannot achieve the target.</html>");
                        return;

                    }  
                }
                resultLabel.setText("Required End Sem Marks: " + finalEndSemMarks);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input. Please enter valid numbers.");
            }
        }
    }
}
