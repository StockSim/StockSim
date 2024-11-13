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

public class DashboardPanel extends JPanel implements IComponent {
    private final JLabel welcomeLabel;
    private final ButtonComponent tradeButton;
    private final ButtonComponent logoutButton;

    public DashboardPanel(String username, double cash, double position) {
        ViewManager.Instance().registerComponent(this);

        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(300, 400));
        setPreferredSize(new Dimension(500, 400));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Welcome Label
        welcomeLabel = new JLabel("<html>Welcome back, " + username + ".<br>You have $" + cash + " in cash and $" + position + " in position.</html>");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setVerticalAlignment(SwingConstants.TOP);
        welcomeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(welcomeLabel, BorderLayout.NORTH);

        // Trading Management Panel
        JPanel tradingManagementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        tradingManagementPanel.setBorder(BorderFactory.createTitledBorder("Trading management"));

        // Trade Button with ActionListener for switching panels
        tradeButton = new ButtonComponent("Trade");
        tradeButton.addActionListener(e -> ViewManager.Instance().broadcastEvent(new SwitchPanelEvent("TradeSimulationPanel")));
        tradingManagementPanel.add(tradeButton);

        // Account Management Panel
        JPanel accountManagementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        accountManagementPanel.setBorder(BorderFactory.createTitledBorder("Account management"));

        // Logout Button with ActionListener for switching to LogInPanel
        logoutButton = new ButtonComponent("Log out");
        logoutButton.addActionListener(e -> ViewManager.Instance().broadcastEvent(new SwitchPanelEvent("LogInPanel")));
        accountManagementPanel.add(logoutButton);

        // Combine Panels into a Management Panel
        JPanel managementPanel = new JPanel();
        managementPanel.setLayout(new BoxLayout(managementPanel, BoxLayout.Y_AXIS));
        managementPanel.add(tradingManagementPanel);
        managementPanel.add(Box.createVerticalStrut(10));
        managementPanel.add(accountManagementPanel);

        add(managementPanel, BorderLayout.CENTER);
    }

    @Override
    public void receiveViewEvent(ViewEvent event) {
        if (event instanceof SwitchPanelEvent) {
            System.out.println("DashboardPanel received a SwitchPanelEvent.");
        }
    }

    @Override
    public EnumSet<EventType> getSupportedEventTypes() {
        // DashboardPanel only supports SWITCH_PANEL events
        return EnumSet.of(EventType.SWITCH_PANEL);
    }
}