package view.gui_components.display_info;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PortfolioPanel extends JPanel {
    private final JLabel titleLabel;
    private final JTable portfolioTable;

    public PortfolioPanel() {

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 300));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));


        titleLabel = new JLabel("Portfolio");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        headerPanel.add(titleLabel, BorderLayout.WEST);


        add(headerPanel, BorderLayout.NORTH);


        DefaultTableModel model = getDefaultTableModel();

        portfolioTable = new JTable(model);
        portfolioTable.setFillsViewportHeight(true);
        portfolioTable.setRowHeight(30);
        portfolioTable.setFont(new Font("Arial", Font.PLAIN, 14));
        portfolioTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        portfolioTable.getTableHeader().setForeground(Color.GRAY);


        portfolioTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane tableScrollPane = new JScrollPane(portfolioTable);


        tableScrollPane.setPreferredSize(new Dimension(600, 200));


        add(tableScrollPane, BorderLayout.CENTER);
    }

    private static DefaultTableModel getDefaultTableModel() {
        String[] columnNames = {
                "Ticker", "Company Name", "Average Cost", "Quantity",
                "Market Price", "Profit / Share", "Total Profit"
        };

        Object[][] data = {
                {"NVDA", "NVIDIA Corporation", "138.27", "2", "158.03", "19.76", "39.52"},
                {"TSLA", "Tesla, Inc.", "110.23", "12", "101.85", "-8.38", "-100.56"},
                {"INTC", "Intel Corporation", "XX.XX", "X", "XX.XX", "XX.XX", "XX.XX"},
        };


        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return model;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Portfolio Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        PortfolioPanel portfolioPanel = new PortfolioPanel();
        frame.add(portfolioPanel);

        frame.setVisible(true);
    }
}