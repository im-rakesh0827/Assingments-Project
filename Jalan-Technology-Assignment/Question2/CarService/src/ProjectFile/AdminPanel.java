package ProjectFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPanel extends JFrame implements ActionListener {

    JButton addEmp, viewEmp, updateEmp, deleteEmp;
    AdminPanel(){
        setLayout(null);
        ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("icons/home.jpg"));
        Image image2 = image1.getImage().getScaledInstance(1170, 750, Image.SCALE_DEFAULT);
        ImageIcon image3 = new ImageIcon(image2);
        JLabel image = new JLabel(image3);
        image.setBounds(0, 0, 1170, 750);
        add(image);

        JLabel heading = new JLabel("Employee Management System");
        heading.setBounds(650, 20, 450, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        image.add(heading);


        addEmp = new JButton("Add");
        addEmp.setBounds(650, 90, 150, 40);
        addEmp.setFont(new Font("serif", Font.PLAIN, 20));
        addEmp.addActionListener(this::actionPerformed);
        image.add(addEmp);


        viewEmp = new JButton("View");
        viewEmp.setBounds(860, 90, 150, 40);
        viewEmp.setFont(new Font("serif", Font.PLAIN, 20));
        viewEmp.addActionListener(this::actionPerformed);
        image.add(viewEmp);


        updateEmp = new JButton("Update");
        updateEmp.setBounds(650, 150, 150, 40);
        updateEmp.setFont(new Font("serif", Font.PLAIN, 20));
        updateEmp.addActionListener(this::actionPerformed);
        image.add(updateEmp);

        deleteEmp = new JButton("Delete");
        deleteEmp.setBounds(860, 150, 150, 40);
        deleteEmp.setFont(new Font("serif", Font.PLAIN, 20));
        deleteEmp.addActionListener(this::actionPerformed);
        image.add(deleteEmp);


//        setSize(1120, 630);
        setSize(1170, 750);

        setLocation(145, 50);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(addEmp)){
            setVisible(false);
            new RegisterUser();
        }if(e.getSource().equals(viewEmp)){
            setVisible(false);
            new ViewCharges();
        }if(e.getSource().equals(updateEmp)){
            setVisible(false);
            new Update();
        }if(e.getSource().equals(deleteEmp)){
            setVisible(false);
            new RemoveUser();
        }

    }
    public static void main(String[] args) {
        new AdminPanel();
    }


}
