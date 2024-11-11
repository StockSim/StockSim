package view.gui_components.interaction;

import javax.swing.*;
import java.awt.*;

public class OrderEntryPanel extends JPanel {
    private final JLabel titleLabel;
    private final JTextField tickerField;
    private final JTextField quantityField;
    private final JButton buyButton;
    private final JButton sellButton;

    public OrderEntryPanel() {

        setLayout(new BorderLayout());


        setMinimumSize(new Dimension(150, 300));
        setPreferredSize(new Dimension(150, 400));


        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        titleLabel = new JLabel("Order Entry");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);


        add(titleLabel, BorderLayout.NORTH);


        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1.0;


        gbc.gridy = 0;
        formPanel.add(new JLabel("Ticker"), gbc);

        gbc.gridy = 1;
        tickerField = new JTextField();
        tickerField.setToolTipText("Enter stock ticker");
        formPanel.add(tickerField, gbc);


        gbc.gridy = 2;
        formPanel.add(new JLabel("Quantity"), gbc);

        gbc.gridy = 3;
        quantityField = new JTextField();
        quantityField.setToolTipText("Enter quantity");
        formPanel.add(quantityField, gbc);


        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buyButton = new JButton("Buy");
        buyButton.setPreferredSize(new Dimension(100, 40));
        buyButton.setBackground(Color.GRAY);
        buyButton.setForeground(Color.BLACK);

        sellButton = new JButton("Sell");
        sellButton.setPreferredSize(new Dimension(100, 40));
        sellButton.setBackground(Color.GRAY);
        sellButton.setForeground(Color.BLACK);


        buttonPanel.add(buyButton);
        buttonPanel.add(sellButton);


        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Order Entry Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        OrderEntryPanel orderEntryPanel = new OrderEntryPanel();
        frame.add(orderEntryPanel);

        frame.setVisible(true);
    }
}