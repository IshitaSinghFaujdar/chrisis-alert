import javax.swing.*;

public class MarksCalculatorFrame extends JFrame {
    public MarksCalculatorFrame() {
        setTitle("Marks Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  
        MarksInputPanel panel = new MarksInputPanel();
        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MarksCalculatorFrame());
    }
}