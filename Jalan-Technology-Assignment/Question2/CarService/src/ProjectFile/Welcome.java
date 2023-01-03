package ProjectFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Welcome extends JFrame implements ActionListener {

    JButton buttonUser, buttonAdmin;
    Welcome(){
        setLayout(null);
        getContentPane().setBackground(Color.LIGHT_GRAY);

        JLabel heading = new JLabel("Welcome To New Era Of Car Servicing");
        heading.setBounds(150, 80, 950, 60);
        heading.setFont(new Font("serif", Font.BOLD, 50));
        add(heading);


        buttonUser = new JButton("Car Owner");
        buttonUser.setBounds(200, 350, 300, 70);
        buttonUser.setFont(new Font("serif", Font.BOLD, 30));
        add(buttonUser);
        buttonUser.addActionListener(this::actionPerformed);


        buttonAdmin = new JButton("Admin");
        buttonAdmin.setBounds(670, 350, 300, 70);
        buttonAdmin.setFont(new Font("serif", Font.BOLD, 30));
        add(buttonAdmin);
        buttonAdmin.addActionListener(this::actionPerformed);


        setSize(1170, 750);
        setLocation(150, 50);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(buttonUser)){
            setVisible(false);
            new LoginUser();

        }else if(e.getSource().equals(buttonAdmin)){
            setVisible(false);
            new LoginAdmin();
        }
    }

    public static void main(String[] args) {
        new Welcome();
    }


}
