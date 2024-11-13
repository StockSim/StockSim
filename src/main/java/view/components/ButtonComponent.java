package view.components;

import javax.swing.*;
import java.awt.*;

public class ButtonComponent extends JButton {

    public ButtonComponent(String text) {
        super(text);  // Set the button text

        // Set simple colors
        setBackground(Color.WHITE);   // Set background to white
        setForeground(Color.BLACK);   // Set text color to black
    }
}