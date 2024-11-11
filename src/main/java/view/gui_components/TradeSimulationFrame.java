package view.gui_components;

import view.gui_components.display_info.AssetPanel;
import view.gui_components.display_info.PortfolioPanel;
import view.gui_components.interaction.MarketSearchPanel;
import view.gui_components.interaction.OrderEntryPanel;

import javax.swing.*;
import java.awt.*;

public class TradeSimulationFrame extends JFrame {

    public TradeSimulationFrame() {
        setTitle("Trade Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        JLabel titleLabel = new JLabel("Trade Simulation");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerPanel.add(titleLabel, BorderLayout.WEST);


        JButton backButton = new JButton("Back to Home");
        backButton.setPreferredSize(new Dimension(120, 30));
        backButton.setFocusPainted(false);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        buttonPanel.add(backButton);
        headerPanel.add(buttonPanel, BorderLayout.EAST);


        mainPanel.add(headerPanel);


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


        JPanel assetAndPortfolioPanel = new JPanel();
        assetAndPortfolioPanel.setLayout(new BoxLayout(assetAndPortfolioPanel, BoxLayout.Y_AXIS));
        assetAndPortfolioPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        assetAndPortfolioPanel.add(new AssetPanel());
        assetAndPortfolioPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        assetAndPortfolioPanel.add(new PortfolioPanel());


        mainPanel.add(upperPanel);


        mainPanel.add(assetAndPortfolioPanel);


        getContentPane().add(mainPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new TradeSimulationFrame();
    }
}