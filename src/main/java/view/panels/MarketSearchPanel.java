package view.panels;

import view.IComponent;
import view.components.ButtonComponent;
import view.components.InputComponent;
import view.view_events.EventType;
import view.view_events.ViewEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.EnumSet;

public class MarketSearchPanel extends JPanel implements IComponent {
    private final InputComponent searchField;
    private final ButtonComponent searchButton;
    private final JLabel titleLabel;
    private final JTable stockTable;

    public MarketSearchPanel() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(400, 300));
        setPreferredSize(new Dimension(600, 400));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Header with Title and Search Field/Button
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        titleLabel = new JLabel("Market Search");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        headerPanel.add(titleLabel, BorderLayout.WEST);

        // Search Field and Button in a FlowLayout
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchField = new InputComponent(10);
        searchButton = new ButtonComponent("Search");

        // Add listener to search button (placeholder for functionality)
        searchButton.addActionListener(e -> {
            // Logic for searching stocks would go here
            System.out.println("Search button clicked for ticker: " + searchField.getText());
        });

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        headerPanel.add(searchPanel, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        // Stock Table Section
        JPanel bodyPanel = new JPanel(new BorderLayout());
        stockTable = new JTable(getDefaultTableModel());
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

    // Table model setup for demonstration purposes
    private DefaultTableModel getDefaultTableModel() {
        String[] columnNames = {"Ticker", "Company Name", "Price", "Change"};
        Object[][] data = {
                {"AAPL", "Apple Inc.", "150.00", "+1.23"},
                {"GOOG", "Alphabet Inc.", "2800.00", "-15.23"},
                {"TSLA", "Tesla Inc.", "900.00", "+12.34"}
        };
        return new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // Make table cells non-editable
            }
        };
    }

    @Override
    public void receiveViewEvent(ViewEvent event) {
        // Set up for future event handling
    }

    @Override
    public EnumSet<EventType> getSupportedEventTypes() {
        // Currently supports no event types
        return EnumSet.noneOf(EventType.class);
    }
}