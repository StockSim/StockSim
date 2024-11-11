package view.gui_components.display_info;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;

public class AssetPanel extends JPanel {
    private final JLabel totalAssetsLabel;
    private final JLabel cashLabel;
    private final JLabel stockLabel;

    public AssetPanel() {
        JSONObject jsonData = new JSONObject();
        jsonData.put("totalAssets", 5000000.00);
        jsonData.put("cash", 100000.00);
        jsonData.put("stock", 4900000.00);

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        totalAssetsLabel = new JLabel("Total Assets: $" + String.format("%,.2f", jsonData.getDouble("totalAssets")));
        totalAssetsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalAssetsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(totalAssetsLabel, BorderLayout.NORTH);

        JSeparator separator = new JSeparator();
        add(separator, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        cashLabel = new JLabel("Cash: $" + String.format("%,.2f", jsonData.getDouble("cash")));
        cashLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        cashLabel.setForeground(Color.GRAY);

        stockLabel = new JLabel("Stock: $" + String.format("%,.2f", jsonData.getDouble("stock")));
        stockLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        stockLabel.setForeground(Color.GRAY);
        stockLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        bottomPanel.add(cashLabel, BorderLayout.WEST);
        bottomPanel.add(stockLabel, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        setPreferredSize(null);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Asset Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        AssetPanel assetPanel = new AssetPanel();
        frame.add(assetPanel);

        frame.pack();
        frame.setVisible(true);
    }
}