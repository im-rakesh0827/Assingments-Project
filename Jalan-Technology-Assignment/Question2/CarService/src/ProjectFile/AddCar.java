package ProjectFile;

import javax.swing.*;
import java.awt.*;

public class AddCar extends JFrame {
    AddCar(){

        setLayout(null);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        JLabel labelHeading = new JLabel("Car Service Station");
        labelHeading.setBounds(350, 60, 450, 40);
        labelHeading.setFont(new Font("Serif", Font.BOLD, 50));
        add(labelHeading);

        int verticalShift = 150;
        JLabel labelServiceCode = new JLabel("Service Code : ");
        labelServiceCode.setBounds(150, verticalShift, 200, 30);
        labelServiceCode.setFont(new Font("Serif", Font.BOLD, 25));
        add(labelServiceCode);

        verticalShift+=40;
        JLabel labelService = new JLabel("Service : ");
        labelService.setBounds(150, verticalShift, 450, 40);
        labelService.setFont(new Font("Serif", Font.BOLD, 25));
        add(labelService);

        verticalShift+=40;
        JLabel labelHatchback = new JLabel(" : ");
        labelService.setBounds(150, verticalShift, 450, 40);
        labelService.setFont(new Font("Serif", Font.BOLD, 25));
        add(labelService);


        verticalShift+=40;
        JLabel labelSedan = new JLabel("Service Code : ");
        labelService.setBounds(150, verticalShift, 450, 40);
        labelService.setFont(new Font("Serif", Font.BOLD, 25));
        add(labelService);


        verticalShift+=40;
        JLabel labelSuv = new JLabel("Service Code : ");
        labelService.setBounds(150, verticalShift, 450, 40);
        labelService.setFont(new Font("Serif", Font.BOLD, 25));
        add(labelService);




        setSize(1170, 750);
        setLocation(150, 70);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);


    }

    public static void main(String[] args) {
        new AddCar();
    }
}
