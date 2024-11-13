package view.components;

import javax.swing.*;
import java.awt.*;

public class InputComponent extends JPanel {
    private final JTextField textField;

    // Constructor with label on top
    public InputComponent(String labelText, int columns) {
        setLayout(new BorderLayout(0, 5)); // Add a vertical gap between label and field
        JLabel label = new JLabel(labelText);
        textField = new JTextField(columns);

        label.setHorizontalAlignment(SwingConstants.LEFT); // Align label to the left
        textField.setPreferredSize(new Dimension(0, textField.getPreferredSize().height));

        add(label, BorderLayout.NORTH);
        add(textField, BorderLayout.CENTER);
    }

    // Constructor for field only (no label)
    public InputComponent(int columns) {
        setLayout(new BorderLayout());
        textField = new JTextField(columns);
        add(textField, BorderLayout.CENTER);
    }

    // Constructor with placeholder text, no label
    public InputComponent(String placeholderText) {
        setLayout(new BorderLayout());
        textField = new JTextField();
        textField.setText(placeholderText);
        textField.setPreferredSize(new Dimension(0, textField.getPreferredSize().height));
        add(textField, BorderLayout.CENTER);
    }

    public String getText() {
        return textField.getText();
    }

    public void setText(String text) {
        textField.setText(text);
    }
}