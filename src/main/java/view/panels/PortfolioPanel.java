package view.panels;

import view.IComponent;
import view.view_events.EventType;
import view.view_events.ViewEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.EnumSet;

public class PortfolioPanel extends JPanel implements IComponent {
    private final JLabel titleLabel;
    private final JTable portfolioTable;

    public PortfolioPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 300));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Header Panel with Title
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        titleLabel = new JLabel("Portfolio");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        headerPanel.add(titleLabel, BorderLayout.WEST);

        add(headerPanel, BorderLayout.NORTH);

        // Portfolio Table Setup
        portfolioTable = createPortfolioTable();
        JScrollPane tableScrollPane = new JScrollPane(portfolioTable);
        tableScrollPane.setPreferredSize(new Dimension(600, 200));
        add(tableScrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Portfolio Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        PortfolioPanel portfolioPanel = new PortfolioPanel();
        frame.add(portfolioPanel);

        frame.setVisible(true);
    }

    private JTable createPortfolioTable() {
        DefaultTableModel model = getDefaultTableModel();
        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setForeground(Color.GRAY);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        return table;
    }

    private DefaultTableModel getDefaultTableModel() {
        String[] columnNames = {
                "Ticker", "Company Name", "Average Cost", "Quantity",
                "Market Price", "Profit / Share", "Total Profit"
        };

        Object[][] data = {
                {"NVDA", "NVIDIA Corporation", "138.27", "2", "158.03", "19.76", "40.00"},
                {"TSLA", "Tesla, Inc.", "110.23", "12", "101.85", "-8.38", "-100.56"},
                {"INTC", "Intel Corporation", "XX.XX", "X", "XX.XX", "XX.XX", "XX.XX"},
        };

        return new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    @Override
    public void receiveViewEvent(ViewEvent event) {
        // Future handling of UpdatePortfolioEvent
    }

    @Override
    public EnumSet<EventType> getSupportedEventTypes() {
        // PortfolioPanel only supports UPDATE_ASSET events
        return EnumSet.of(EventType.UPDATE_ASSET);
    }
}