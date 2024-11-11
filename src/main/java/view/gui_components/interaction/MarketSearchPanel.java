package view.gui_components.interaction;

import javax.swing.*;
import java.awt.*;

public class MarketSearchPanel extends JPanel {
    private final JTextField searchField;
    private final JButton searchButton;
    private final JLabel titleLabel;
    private final JTable stockTable;

    public MarketSearchPanel() {

        setLayout(new BorderLayout());


        setMinimumSize(new Dimension(400, 300));
        setPreferredSize(new Dimension(600, 400));


        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));


        titleLabel = new JLabel("Market Search");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        headerPanel.add(titleLabel, BorderLayout.WEST);


        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchField = new JTextField(8);
        searchField.setToolTipText("Ticker for the stock");
        searchButton = new JButton("Search");

        searchPanel.add(searchField);
        searchPanel.add(searchButton);


        headerPanel.add(searchPanel, BorderLayout.EAST);


        add(headerPanel, BorderLayout.NORTH);


        JPanel bodyPanel = new JPanel(new BorderLayout());
        stockTable = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(stockTable);

        bodyPanel.add(tableScrollPane, BorderLayout.CENTER);
        add(bodyPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Market Search Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        MarketSearchPanel marketSearchPanel = new MarketSearchPanel();
        frame.add(marketSearchPanel);

        frame.setVisible(true);
    }
}