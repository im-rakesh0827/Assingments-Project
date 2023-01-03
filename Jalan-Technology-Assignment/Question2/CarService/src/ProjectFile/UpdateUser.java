package ProjectFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import static ProjectFile.ApplyFontStyle.applyFontStyleButtonBig;
import static ProjectFile.ApplyFontStyle.applyFontStyleLabelField;
import static ProjectFile.ConfirmChoice.confirmOptionYesNo;


public class UpdateUser extends JFrame implements ActionListener {
    JLabel labelNameFixed, labelEmail, labelAadharFixed, labelPhone, labelVehicle, labelAddress,  labelPassword, labelConfirmPassword;
    JTextField tfName, tfEmail,  tfAadhar, tfPhone, tfAddress, tfVehicle, tfPassword;
    JButton buttonSubmit, buttonBack, buttonCancel;
    JPasswordField pfConfirmPassword;

    JButton [] buttonArray;
    JLabel [] labelArray;
    JTextField [] textFieldArray;

    UpdateUser(String employeeId){
        setLayout(null);
        getContentPane().setBackground(Color.getHSBColor(120,23,150));


        int horizontalShift = 215;
        int verticalShift = 60;
        int buttonShiftVertical = 440;
        int textFieldArea = 550;
//        Heading :
        JLabel heading  = new JLabel("UPDATE INFORMATION");
        heading.setBounds(260, 15, 500, 30);
        heading.setFont(new Font("serif",Font.BOLD, 40));
        heading.setForeground(Color.BLACK);
        add(heading);

//        Name :
        labelNameFixed = new JLabel("Name : ");
        labelNameFixed.setBounds(40, verticalShift, 100, 30);
        add(labelNameFixed);
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
        labelAadharFixed = new JLabel("Aadhar : ");
        labelAadharFixed.setBounds(40, verticalShift, 100, 30);
        add(labelAadharFixed);
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



//        Vehicle
        verticalShift+=40;
        labelVehicle = new JLabel("Vehicle No : ");
        labelVehicle.setBounds(40, verticalShift, 100, 30);
        add(labelVehicle);
        tfVehicle = new JTextField();
        tfVehicle.setBounds(horizontalShift, verticalShift, textFieldArea, 30);
        add(tfVehicle);


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



//        Button Area : Submit
        buttonShiftVertical+=160;
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
        labelArray = new JLabel[]{labelNameFixed, labelEmail, labelPhone, labelAadharFixed, labelVehicle, labelAddress, labelPassword, labelConfirmPassword,};
        textFieldArray = new JTextField[]{tfName, tfEmail, tfPhone, tfVehicle, tfAadhar, tfAddress, tfPassword};
        applyFontStyleLabelField(labelArray, textFieldArray);

        buttonArray = new JButton[]{buttonSubmit, buttonBack, buttonCancel};
        applyFontStyleButtonBig(buttonArray);

        try {
            Conn connection = new Conn();
            String query = "select * from users where vehicleNo = '"+tfVehicle+"'";
            ResultSet resultSet  = connection.statement.executeQuery(query);
            while(resultSet.next()){
                labelNameFixed.setText(resultSet.getString("name"));
                tfEmail.setText(resultSet.getString("email"));
                labelAadharFixed.setText(resultSet.getString("aadhar"));
                tfPhone.setText(resultSet.getString("phone"));
                tfAddress.setText(resultSet.getString("address"));
                tfPassword.setText(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        setSize(850, 700);
        setLocation(300, 80);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonSubmit){
            String email = tfEmail.getText();
            String phone = tfPhone.getText();
            String address = tfAddress.getText();
            String password = tfPassword.getText();
            String confirmPassword = String.valueOf(pfConfirmPassword.getPassword());
            String [] dataArray = {email, phone, address, password, confirmPassword};
            try {
                if( email.isEmpty() || phone.isEmpty() || address.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
                    JOptionPane.showMessageDialog(
                            this,
                            "Enter Your Details Carefully...",
                            "Details Missing ! ",
                            JOptionPane.ERROR_MESSAGE
                    );

                }else if(!password.equals(confirmPassword)){
                    JOptionPane.showMessageDialog(
                            this,
                            "Re-Enter Confirm Password...",
                            "Password Mismatch ! ",
                            JOptionPane.ERROR_MESSAGE
                    );

                }else{
                    if(confirmOptionYesNo()){
                        Conn connection = new Conn();
                        String query = "update users set email='"+email+"', phone='"+phone+"', address='"+address+"', password='"+password+"' where employeeId='"+tfVehicle+"'  ";
                        connection.statement.executeUpdate(query);
                        JOptionPane.showMessageDialog(
                                this,
                                "Details Updated Successfully",
                                "Congratulation",
                                JOptionPane.ERROR_MESSAGE
                        );
                        setVisible(false);
                        new AdminPanel();
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }else if(e.getSource()==buttonBack){
            if(confirmOptionYesNo()){
                setVisible(false);
                new ViewCharges();
            }
        }else if(e.getSource()==buttonCancel){
            if(confirmOptionYesNo()){
                setVisible(false);
                new Welcome();
            }
        }

    }

    public static void main(String[] args) {
        new UpdateUser(null);
    }
}
