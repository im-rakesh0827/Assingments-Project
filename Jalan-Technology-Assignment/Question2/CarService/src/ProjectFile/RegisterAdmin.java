package ProjectFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static ProjectFile.ApplyFontStyle.applyFontStyleButtonBig;
import static ProjectFile.ApplyFontStyle.applyFontStyleLabelField;
import static ProjectFile.ConfirmChoice.confirmOptionYesNo;


public class RegisterAdmin extends JFrame implements ActionListener {
    JLabel labelName, labelEmail, labelAadhar, labelPhone, labelAddress,  labelPassword, labelConfirmPassword;
    JTextField tfName, tfEmail,  tfAadhar, tfPhone, tfAddress, tfPassword;
    JButton buttonSubmit, buttonBack, buttonCancel;
    JPasswordField pfConfirmPassword;

    JButton [] buttonArray;
    JLabel [] labelArray;
    JTextField [] textFieldArray;

    RegisterAdmin(){
        setLayout(null);
        getContentPane().setBackground(Color.pink);

//        Heading :
        JLabel heading  = new JLabel("ADMIN REGISTRATION FORM");
        heading.setBounds(240, 25, 550, 30);
        heading.setFont(new Font("serif",Font.BOLD, 30));
        heading.setForeground(Color.BLACK);
        add(heading);


        int horizontalShift = 215;
        int verticalShift = 70;
        int textFieldArea = 550;
//        Name :
        labelName = new JLabel("Name : ");
        labelName.setBounds(40, verticalShift, 100, 30);
        add(labelName);
        tfName = new JTextField();
        tfName.setBounds(horizontalShift, verticalShift, textFieldArea, 30);
        add(tfName);


//        Email :
        verticalShift+=40;
        labelEmail = new JLabel("Email : ");
        labelEmail.setBounds(40, verticalShift, 100, 30);
        add(labelEmail);
        tfEmail = new JTextField();
        tfEmail.setBounds(horizontalShift, verticalShift, textFieldArea, 30);
        add(tfEmail);


//        Aadhaar :
        verticalShift+=40;
        labelAadhar = new JLabel("Aadhar : ");
        labelAadhar.setBounds(40, verticalShift, 100, 30);
        add(labelAadhar);
        tfAadhar = new JTextField();
        tfAadhar.setBounds(horizontalShift, verticalShift, textFieldArea, 30);
        add(tfAadhar);


//        Phone :
        verticalShift+=40;
        labelPhone = new JLabel("Phone : ");
        labelPhone.setBounds(40, verticalShift, 100, 30);
        add(labelPhone);
        tfPhone = new JTextField();
        tfPhone.setBounds(horizontalShift, verticalShift, textFieldArea, 30);
        add(tfPhone);


//        Address :
        verticalShift+=40;
        labelAddress = new JLabel("Address : ");
        labelAddress.setBounds(40, verticalShift, 100, 30);
        add(labelAddress);
        tfAddress = new JTextField();
        tfAddress.setBounds(horizontalShift, verticalShift, textFieldArea, 30);
        add(tfAddress);


//        Password :
        verticalShift+=40;
        labelPassword = new JLabel("Password : ");
        labelPassword.setBounds(40, verticalShift, 100, 30);
        add(labelPassword);
        tfPassword = new JTextField();
        tfPassword.setBounds(horizontalShift, verticalShift, textFieldArea, 30);
        add(tfPassword);


//        Confirm Password :
        verticalShift+=40;
        labelConfirmPassword = new JLabel("Confirm Password : ");
        labelConfirmPassword.setBounds(40, verticalShift, 170, 30);
        add(labelConfirmPassword);
        pfConfirmPassword = new JPasswordField();
        pfConfirmPassword.setBounds(horizontalShift, verticalShift, textFieldArea, 30);
        add(pfConfirmPassword);



        int buttonShiftVertical = 400;
//        Button Area : Submit
        buttonShiftVertical+=10;
        buttonSubmit = new JButton("Submit");
        buttonSubmit.setBounds(217, buttonShiftVertical, 150, 40);
        buttonSubmit.addActionListener(this::actionPerformed);
        add(buttonSubmit);


//        Button : Back
        buttonBack = new JButton("Back");
        buttonBack.setBounds(415, buttonShiftVertical, 150, 40);
        buttonBack.setBackground(Color.black);
        buttonBack.addActionListener(this::actionPerformed);
        add(buttonBack);


//        Buttons : Cancel
        buttonCancel = new JButton("Cancel");
        buttonCancel.setBounds(613, buttonShiftVertical, 150, 40);
        buttonCancel.addActionListener(this::actionPerformed);
        add(buttonCancel);


//        Applying Font and Style on Buttons, Labels and TextField :
        labelArray = new JLabel[]{labelName, labelEmail, labelPhone, labelAadhar, labelAddress, labelPassword, labelConfirmPassword,};
        textFieldArray = new JTextField[]{tfName, tfEmail, tfPhone, tfAadhar, tfAddress, tfPassword};
        applyFontStyleLabelField(labelArray, textFieldArray);

        buttonArray = new JButton[]{buttonSubmit, buttonBack, buttonCancel};
        applyFontStyleButtonBig(buttonArray);


        setSize(850, 500);
        setLocation(300, 180);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(buttonSubmit)){
            try {
                registerEmployee();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }else if(e.getSource().equals(buttonBack)){
            if(confirmOptionYesNo()){
                setVisible(false);
                new LoginAdmin();
            }
        }
        else if(e.getSource().equals(buttonCancel)){
            if(confirmOptionYesNo()){
                JOptionPane.showMessageDialog(
                        this,
                        "Press Ok To Go Home",
                        "Registration Cancelled!",
                        JOptionPane.ERROR_MESSAGE
                );
                setVisible(false);
                new Welcome();
            }

        }
    }
    public void registerEmployee() throws SQLException {
        String name = tfName.getText();
        String email = tfEmail.getText();
        String aadhar = tfAadhar.getText();
        String phone = tfPhone.getText();
        String address = tfAddress.getText();
        String password = tfPassword.getText();
        String confirmPassword = String.valueOf(pfConfirmPassword.getPassword());

        if(name.isEmpty() || email.isEmpty() || aadhar.isEmpty() || phone.isEmpty() || address.isEmpty() ||  password.isEmpty()){
            JOptionPane.showMessageDialog(
                    this,
                    "Enter Details Carefully",
                    "Details Missing !",
                    JOptionPane.ERROR_MESSAGE
            );
        }else{
            if(!password.equals(confirmPassword)){
                JOptionPane.showMessageDialog(
                        this,
                        "Re-Enter Confirm Password...",
                        "Password Mismatch ! ",
                        JOptionPane.ERROR_MESSAGE
                );
            } else{
                admin = addUserToDatabase(name, email, aadhar,  phone, address, password);
                if(admin!=null){
                    if(confirmOptionYesNo()){
                        JOptionPane.showMessageDialog(
                                this,
                                "User Registered Successfully ",
                                "Registration Successful",
                                JOptionPane.ERROR_MESSAGE
                        );
                        setVisible(false);
                    }
                }


            }
        }
    }

    Connection connection;
    Statement statement;
    String query;
    PreparedStatement preparedStatement;
    public Admin admin;

    private Admin addUserToDatabase(String name, String email, String aadhar, String phone, String address, String password) throws SQLException {
        final String DBURL = "jdbc:mysql://localhost:3306/carService";
        final String USERNAME = "root";
        final String PASSWORD = "Apple@0827";
        try {
            connection = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            query = "insert into admins(name, email, aadhar, phone, address, password)"+"values(?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, aadhar);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, password);
            int addedRows = preparedStatement.executeUpdate();
            if(addedRows>0){
                admin = new Admin();
                admin.name = name;
                admin.email = email;
                admin.aadhar = aadhar;
                admin.phone = phone;
                admin.address = address;
                admin.password = password;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    public static void main(String[] args) {
        new RegisterAdmin();
    }

}
