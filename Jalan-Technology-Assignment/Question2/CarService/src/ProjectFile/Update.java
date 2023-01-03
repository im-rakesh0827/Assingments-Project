package ProjectFile;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static ProjectFile.ApplyFontStyle.applyFontStyleButtonSmall;
import static ProjectFile.ConfirmChoice.confirmOptionYesNo;


public class Update extends JFrame implements ActionListener {
    JTable table;
    Choice choiceData;
    JLabel labelSearch;
    JScrollPane jScrollPane;
    JButton buttonSearch, buttonPrint, buttonUpdate, buttonBack;

    JButton [] smallButtonArray;
    JLabel [] labelArray;
    JTextField [] textFieldArray;

    Update(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        labelSearch = new JLabel("Search Employee ID : ");
        labelSearch.setBounds(20, 20, 200, 30);
        labelSearch.setFont(new Font("serif", Font.BOLD, 20));
        add(labelSearch);

        choiceData = new Choice();
        choiceData.setBounds(220, 20, 150, 30);
        choiceData.setFont(new Font("serif", Font.PLAIN, 15));
        add(choiceData);


//        Buttons Area :
        int buttonHeight = 35;
        int buttonWidth = 150;
        int verticalButtonShift = 70;
        buttonSearch = new JButton("Search");
        buttonSearch.setBounds(20, verticalButtonShift, buttonWidth, buttonHeight);
        buttonSearch.addActionListener(this::actionPerformed);
        add(buttonSearch);

//        Button : Print
        buttonPrint = new JButton("Print");
        buttonPrint.setBounds(200, verticalButtonShift, buttonWidth, buttonHeight);
        buttonPrint.addActionListener(this::actionPerformed);
        add(buttonPrint);

//        Button : Update
        buttonUpdate = new JButton("Update");
        buttonUpdate.setBounds(380, verticalButtonShift, buttonWidth, buttonHeight);
        buttonUpdate.addActionListener(this::actionPerformed);
        add(buttonUpdate);

//        Button : Back
        buttonBack = new JButton("Back");
        buttonBack.setBounds(380, verticalButtonShift, buttonWidth, buttonHeight);
        buttonBack.addActionListener(this::actionPerformed);
        add(buttonBack);


//        Applying fonts & styles on all the buttons :
        smallButtonArray = new JButton[] {buttonSearch, buttonPrint, buttonUpdate, buttonBack};
        applyFontStyleButtonSmall(smallButtonArray);

        try {
            Conn c = new Conn();
            String query = "select * from employees";
            ResultSet resultSet = c.statement.executeQuery(query);
            choiceData.add("None");
            choiceData.add("All");
            while (resultSet.next()){
                choiceData.add(resultSet.getString("employeeId"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        table = new JTable();
        jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(0, 150, 1170, 400);
        jScrollPane.setFont(new Font("serif", Font.PLAIN, 28));
        table.setBounds(0, 150, 1170, 80);
        add(jScrollPane);


        setSize(1170, 750);
        setLocation(145, 50);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
                if(choiceData.getSelectedItem().equals("None")){
                    JOptionPane.showMessageDialog(
                            this,
                            "Select  EmployeeId First : ",
                            "Try Again!",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }else if(choiceData.getSelectedItem().equals("All")){
                    query = "select * from employees";
                    resultSet = connection.statement.executeQuery(query);
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));
//                   table.setFont(new Font("serif", Font.PLAIN, 14));
                }else{
                    query = "select * from employees where employeeId = '"+choiceData.getSelectedItem()+"'";
                    resultSet = connection.statement.executeQuery(query);
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));
//                   table.setFont(new Font("serif", Font.PLAIN, 14));
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
        }else if(e.getSource().equals(buttonUpdate)){
            if((!choiceData.getSelectedItem().equals("All")) && (!choiceData.getSelectedItem().equals("None"))){
                setVisible(false);
                new UpdateUser(choiceData.getSelectedItem());
            }else{
                JOptionPane.showMessageDialog(
                        this,
                        "OOPS, Select An  EmployeeId...",
                        "Try Again!",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }else if(e.getSource().equals(buttonBack)){
            if(confirmOptionYesNo()) {
                setVisible(false);
                new AdminPanel();
            }

        }

    }
}
