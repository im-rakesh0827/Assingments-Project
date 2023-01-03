package ProjectFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

import static ProjectFile.ApplyFontStyle.applyFontStyleButtonBig;
import static ProjectFile.ApplyFontStyle.applyFontStyleLabelField;
import static ProjectFile.ConfirmChoice.confirmOptionYesNo;

public class RemoveUser extends JFrame  implements ActionListener {
    JLabel labelEmpId, labelName, labelNameFixed, labelEmail, labelEmailFixed, labelPhone, labelPhoneFixed;
    Choice choiceEmpId;
    JButton buttonDelete, buttonBack;

    JLabel [] labelArray;
    JTextField [] fieldArray;
    JButton [] buttonArrayBig;


    RemoveUser(){
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);



        labelEmpId = new JLabel("Employee ID : ");
        labelEmpId.setBounds(50, 50, 150, 30);
        add(labelEmpId);

        choiceEmpId = new Choice();
        choiceEmpId.setBounds(200, 50, 150, 35);
        choiceEmpId.setFont(new Font("serif", Font.PLAIN, 18));
        add(choiceEmpId);

        try {
            Conn connection = new Conn();
            String query = "select * from employees";
            ResultSet resultSet = connection.statement.executeQuery(query);
            while (resultSet.next()){
                choiceEmpId.add(resultSet.getString("employeeId"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        labelName = new JLabel("Name : ");
        labelName.setBounds(50, 100, 150, 30);
        add(labelName);

        labelNameFixed = new JLabel();
        labelNameFixed.setBounds(200, 100, 150, 30);
        add(labelNameFixed);


        labelEmail = new JLabel("Email : ");
        labelEmail.setBounds(50, 150, 150, 30);
        add(labelEmail);

        labelEmailFixed = new JLabel();
        labelEmailFixed.setBounds(200, 150, 150, 30);
        add(labelEmailFixed);


        labelPhone = new JLabel("Phone : ");
        labelPhone.setBounds(50, 200, 150, 30);
        add(labelPhone);

        labelPhoneFixed = new JLabel();
        labelPhoneFixed.setBounds(200, 200, 150, 30);
        add(labelPhoneFixed);


        try {
            Conn connection = new Conn();
            String query = "select * from employees where employeeId='"+choiceEmpId.getSelectedItem()+"' ";
            ResultSet resultSet = connection.statement.executeQuery(query);
            while (resultSet.next()){
                labelNameFixed.setText(resultSet.getString("name"));
                labelEmailFixed.setText(resultSet.getString("email"));
                labelPhoneFixed.setText(resultSet.getString("phone"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        choiceEmpId.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    Conn connection = new Conn();
                    String query = "select * from employees where employeeId='"+choiceEmpId.getSelectedItem()+"' ";
                    ResultSet resultSet = connection.statement.executeQuery(query);
                    while (resultSet.next()){
                        labelNameFixed.setText(resultSet.getString("name"));
                        labelEmailFixed.setText(resultSet.getString("email"));
                        labelPhoneFixed.setText(resultSet.getString("phone"));
                    }

                }catch (Exception E){
                    E.printStackTrace();
                }

            }
        });

//        Adding Image : Background
        ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"));
        Image image2 = image1.getImage().getScaledInstance(1170, 750, Image.SCALE_DEFAULT);
        ImageIcon image3 = new ImageIcon(image2);
        JLabel image = new JLabel(image3);
        image.setBounds(0, 0, 1170, 750);
        add(image);

//        Buttons :
        buttonDelete = new JButton("Delete");
        buttonDelete.setBounds(50, 270, 150, 40);
        buttonDelete.addActionListener(this::actionPerformed);
        image.add(buttonDelete);


        buttonBack = new JButton("Back");
        buttonBack.setBounds(250, 270, 150, 40);
        buttonBack.addActionListener(this::actionPerformed);
        image.add(buttonBack);





        labelArray = new JLabel[]{labelEmpId, labelName, labelNameFixed, labelEmail, labelEmailFixed, labelPhone, labelPhoneFixed};
        fieldArray = new JTextField[]{};
        applyFontStyleLabelField(labelArray, fieldArray);

        buttonArrayBig = new JButton[]{buttonDelete, buttonBack};
        applyFontStyleButtonBig(buttonArrayBig);



        setSize(1170, 750);
        setLocation(140, 50);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(buttonDelete)){
            if(confirmOptionYesNo()){
                try {
                    Conn connection = new Conn();
                    String query = "delete from employees where employeeId='"+choiceEmpId.getSelectedItem()+"' ";
                    connection.statement.executeUpdate(query);
                    JOptionPane.showMessageDialog(
                            this,
                            "Employee Information Successfully Deleted :",
                            "Employee Deleted",
                            JOptionPane.ERROR_MESSAGE
                    );
                    setVisible(false);
                    new RemoveUser();

                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        }else if(e.getSource().equals(buttonBack)){
            if(confirmOptionYesNo()){
                setVisible(false);
                new AdminPanel();
            }
        }

    }

    public static void main(String[] args) {
        new RemoveUser();

    }


}
