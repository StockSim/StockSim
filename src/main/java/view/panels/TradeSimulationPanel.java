package view.panels;

import utility.ViewManager;
import view.IComponent;
import view.components.ButtonComponent;
import view.view_events.EventType;
import view.view_events.SwitchPanelEvent;
import view.view_events.ViewEvent;

import javax.swing.*;
import java.awt.*;
import java.util.EnumSet;

public class TradeSimulationPanel extends JPanel implements IComponent {

    public TradeSimulationPanel() {
        ViewManager.Instance().registerComponent(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header with Title and Back Button
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Trade Simulation");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerPanel.add(titleLabel, BorderLayout.WEST);

        // Back to Home button
        ButtonComponent backButton = new ButtonComponent("Back to Home");
        backButton.setPreferredSize(new Dimension(120, 30));
        backButton.addActionListener(e ->
                ViewManager.Instance().broadcastEvent(new SwitchPanelEvent("DashboardPanel"))
        );

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        buttonPanel.add(backButton);
        headerPanel.add(buttonPanel, BorderLayout.EAST);

        add(headerPanel);

        // Upper Panel with Market Search and Order Entry
        JPanel upperPanel = new JPanel(new GridBagLayout());
        upperPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;

        JPanel searchAndOrderPanel = new JPanel(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 4.0;
        gbc.insets = new Insets(0, 0, 0, 10);
        MarketSearchPanel marketSearchPanel = new MarketSearchPanel();
        marketSearchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        searchAndOrderPanel.add(marketSearchPanel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0, 10, 0, 0);
        OrderEntryPanel orderEntryPanel = new OrderEntryPanel();
        orderEntryPanel.setPreferredSize(new Dimension(200, 0));
        orderEntryPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        searchAndOrderPanel.add(orderEntryPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        upperPanel.add(searchAndOrderPanel, gbc);

        add(upperPanel);

        // Lower Panel with Asset and Portfolio Details
        JPanel assetAndPortfolioPanel = new JPanel();
        assetAndPortfolioPanel.setLayout(new BoxLayout(assetAndPortfolioPanel, BoxLayout.Y_AXIS));
        assetAndPortfolioPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        assetAndPortfolioPanel.add(new AssetPanel());
        assetAndPortfolioPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        assetAndPortfolioPanel.add(new PortfolioPanel());

        add(assetAndPortfolioPanel);
    }

    @Override
    public void receiveViewEvent(ViewEvent event) {
        if (event instanceof SwitchPanelEvent) {
            System.out.println("TradeSimulationPanel received a SwitchPanelEvent.");
        }
    }

    @Override
    public EnumSet<EventType> getSupportedEventTypes() {
        // TradeSimulationPanel only supports SWITCH_PANEL events
        return EnumSet.of(EventType.SWITCH_PANEL);
    }
}