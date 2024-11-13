package view.panels;

import org.json.JSONObject;
import view.IComponent;
import view.view_events.EventType;
import view.view_events.ViewEvent;

import javax.swing.*;
import java.awt.*;
import java.util.EnumSet;

public class AssetPanel extends JPanel implements IComponent {
    private final JLabel totalAssetsLabel;
    private final JLabel cashLabel;
    private final JLabel stockLabel;

    public AssetPanel() {
        // Sample JSON data representing assets
        JSONObject jsonData = new JSONObject();
        jsonData.put("totalAssets", 5000000.00);
        jsonData.put("cash", 100000.00);
        jsonData.put("stock", 4900000.00);

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Total Assets label setup
        totalAssetsLabel = new JLabel("Total Assets: $" + String.format("%,.2f", jsonData.getDouble("totalAssets")));
        totalAssetsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalAssetsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(totalAssetsLabel, BorderLayout.NORTH);

        // Separator for visual structure
        JSeparator separator = new JSeparator();
        add(separator, BorderLayout.CENTER);

        // Bottom panel for Cash and Stock labels
        JPanel bottomPanel = new JPanel(new BorderLayout());

        // Cash label
        cashLabel = new JLabel("Cash: $" + String.format("%,.2f", jsonData.getDouble("cash")));
        cashLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        cashLabel.setForeground(Color.GRAY);

        // Stock label
        stockLabel = new JLabel("Stock: $" + String.format("%,.2f", jsonData.getDouble("stock")));
        stockLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        stockLabel.setForeground(Color.GRAY);
        stockLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // Adding labels to the bottom panel
        bottomPanel.add(cashLabel, BorderLayout.WEST);
        bottomPanel.add(stockLabel, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(300, 150));
    }

    @Override
    public void receiveViewEvent(ViewEvent event) {
        // Placeholder for future event handling, if needed
    }

    @Override
    public EnumSet<EventType> getSupportedEventTypes() {
        // AssetPanel supports UPDATE_ASSET events
        return EnumSet.of(EventType.UPDATE_ASSET);
    }
}