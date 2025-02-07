import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame{
    

    JLabel user_l,pass_l,message;
    JTextField username;
    JPasswordField password;
    JButton submit, forgot;

    LoginFrame(){
        setLayout(null);
        user_l=new JLabel();
        user_l.setText("UserName: ");
        username=new JTextField();
        pass_l=new JLabel();
        pass_l.setText("Password: ");
        password=new JPasswordField();
        submit=new JButton("Submit");
        submit.setBackground(new Color(30,174,39));
        submit.setForeground(Color.white);
        forgot= new JButton("Forgot Password?");
        forgot.setBackground(new Color(6,25,84));
        forgot.setForeground(Color.white);
        JButton SignUp=new JButton("Sign Up");
        SignUp.setBackground(new Color(6,25,84));
        SignUp.setForeground(Color.white);
    //dimensions
    user_l.setBounds(400,200,200,30);
    username.setBounds(400,255,200,30);
    pass_l.setBounds(400,310,200,30);
    password.setBounds(400,365,200,30);

    submit.setBounds(400,430,200,50);
    forgot.setBounds(385,495,150,30);
    SignUp.setBounds(515,495,100,30);

        
        //panel with text

        JPanel panel=new JPanel();
        panel.setBackground(new Color(6,25,84));
        panel.setBounds(0, 0, 200, 700);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JLabel heading=new JLabel("Developer's Note:");
        JLabel panelText = new JLabel("<html><br><br>Hi There, This was created for educational purposes. I found this useful for calculating how much to study for end semester examinations to maintain a decent grade.<br> If you have any suggestions for improvement, please feel free to reach out @ ishita85236@gmail.com to Ishita Singh Faujdar. <br> Todalooo. All the best for examinations.</html>");
        heading.setForeground(Color.WHITE);
        heading.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panelText.setForeground(Color.WHITE);
        panelText.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(heading);
        panel.add(panelText);
        add(panel,BorderLayout.WEST);
        
        //Adding to frame
        this.add(user_l);
        this.add(username);
        this.add(pass_l);
        this.add(password);
        this.add(submit);
        this.add(forgot);
        this.add(SignUp);
        

        setTitle("Login/SignUp");
        setSize(700,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }


}