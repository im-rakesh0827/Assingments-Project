package ProjectFile;

import javax.swing.*;
import java.awt.*;

public class ApplyFontStyle extends JFrame{
//    Applying Font & Styles on JLabel and JTextField :
//    public static void applyFontStyle(JLabel label, JTextField field){
//        label.setFont(new Font("serif", Font.BOLD, 18));
//        field.setFont(new Font("serif", Font.PLAIN, 18));
//    }
//
//    public static void applyFontButtonBig(JButton button){
//        button.setFont(new Font("serif", Font.BOLD, 20));
//    }



//    Applying Font & Styles on array of labels & fields :
    public static void applyFontStyleLabelField(JLabel [] labelArray, JTextField [] fieldArray){
        for(JLabel label:labelArray){
            label.setFont(new Font("serif", Font.BOLD, 18));
        }
        for(JTextField field:fieldArray){
            field.setFont(new Font("serif", Font.PLAIN, 18));
        }
    }


//    Applying Fonts & Styles on array of buttons : Big Buttons
    public static void applyFontStyleButtonBig(JButton [] buttonArray){
        for(JButton button:buttonArray){
            button.setFont(new Font("Serif", Font.BOLD, 20));
        }
    }

//    Applying Fonts & Styles on array of buttons : Small Buttons
    public static void applyFontStyleButtonSmall(JButton [] buttonArray){
        for(JButton button:buttonArray){
            button.setFont(new Font("serif", Font.BOLD, 16));
        }
    }

}
