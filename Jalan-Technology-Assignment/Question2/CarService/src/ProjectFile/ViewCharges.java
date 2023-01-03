package ProjectFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.*;

import net.proteanit.sql.DbUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewCharges extends JFrame implements ActionListener {
    JTable table;
    Choice serviceCode;
    Choice serviceType;
    Choice carType;
    JLabel labelServiceCode, labelServiceType, labelCarType;
    JScrollPane jScrollPane;
    JButton buttonSearch, buttonPrint, buttonUpdate, buttonBack;

    JButton [] smallButtonArray;

    ViewCharges(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        int verticalShift = 40;
        labelServiceCode = new JLabel("Service Code : ");
        labelServiceCode.setBounds(20, verticalShift, 200, 30);
        labelServiceCode.setFont(new Font("serif", Font.BOLD, 20));
        add(labelServiceCode);

        serviceCode = new Choice();
        serviceCode.setBounds(220, verticalShift, 150, 30);
        serviceCode.setFont(new Font("serif", Font.PLAIN, 15));
        add(serviceCode);


        verticalShift+=40;
        labelServiceType = new JLabel("Service Type : ");
        labelServiceType.setBounds(20, verticalShift, 200, 30);
        labelServiceType.setFont(new Font("serif", Font.BOLD, 20));
        add(labelServiceType);

        serviceType = new Choice();
        serviceType.setBounds(220, verticalShift, 150, 30);
        serviceType.setFont(new Font("serif", Font.PLAIN, 15));
        add(serviceType);


        verticalShift+=40;
        labelCarType = new JLabel("Car Type : ");
        labelCarType.setBounds(20, verticalShift, 200, 30);
        labelCarType.setFont(new Font("serif", Font.BOLD, 20));
        add(labelCarType);

        carType = new Choice();
        carType.setBounds(220, verticalShift, 150, 30);
        carType.setFont(new Font("serif", Font.PLAIN, 15));
        add(carType);



//        Buttons Area :
        int buttonHeight = 35;
        int buttonWidth = 150;
        int verticalButtonShift = 270;
        buttonSearch = new JButton("Search");
        buttonSearch.setBounds(20, verticalButtonShift, buttonWidth, buttonHeight);
        buttonSearch.addActionListener(this::actionPerformed);
        add(buttonSearch);

//        Button : Print
        buttonPrint = new JButton("Print");
        buttonPrint.setBounds(200, verticalButtonShift, buttonWidth, buttonHeight);
        buttonPrint.addActionListener(this::actionPerformed);
        add(buttonPrint);



//        Button : Back
        buttonBack = new JButton("Back");
        buttonBack.setBounds(380, verticalButtonShift, buttonWidth, buttonHeight);
        buttonBack.addActionListener(this::actionPerformed);
        add(buttonBack);


//        Applying fonts & styles on all the buttons :
        smallButtonArray = new JButton[] {buttonSearch, buttonPrint, buttonBack};
        applyFontStyleButtonSmall(smallButtonArray);
        try {
            Conn c = new Conn();
            String query = "select * from serviceCharge";
            ResultSet resultSet = c.statement.executeQuery(query);
            serviceCode.add("None");
            serviceCode.add("All");
            while (resultSet.next()){
                serviceCode.add(resultSet.getString("serviceCode"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        table = new JTable();
        jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(0, 350, 1170, 300);
        jScrollPane.setFont(new Font("serif", Font.PLAIN, 28));
        table.setBounds(0, 150, 1170, 80);
        add(jScrollPane);


        setSize(1170, 750);
        setLocation(145, 50);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void applyFontStyleButtonSmall(JButton[] smallButtonArray) {

    }

    public static void main(String[] args) {
        new ViewCharges();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(buttonSearch)){
            try {
                Conn connection = new Conn();
                String query = "";
                ResultSet resultSet=null;
                if(serviceCode.getSelectedItem().equals("None")){
                    JOptionPane.showMessageDialog(
                            this,
                            "Select  EmployeeId First : ",
                            "Try Again!",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }else if(serviceCode.getSelectedItem().equals("All")){
                    query = "select * from serviceCharge";
                    resultSet = connection.statement.executeQuery(query);
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));
                   table.setFont(new Font("serif", Font.PLAIN, 14));
                }else{
                    query = "select * from serviceCharge where serviceCode = '"+ serviceCode.getSelectedItem()+"'";
                    resultSet = connection.statement.executeQuery(query);
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));
                   table.setFont(new Font("serif", Font.PLAIN, 14));
                }
                table.setFont(new Font("serif", Font.PLAIN, 15));

            }catch (Exception E){
                E.printStackTrace();
            }
        }else if(e.getSource().equals(buttonPrint)){
            try {
                table.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }

    }
}

