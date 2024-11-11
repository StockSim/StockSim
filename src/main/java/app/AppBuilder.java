package app;

import view.gui_components.TradeSimulationFrame;

import javax.swing.*;
import java.awt.*;

/**
 * A builder class for the application
 */
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Add the trade simulation to the application
     *
     * @return the builder
     */
    public AppBuilder addTradeSimulation() {
        TradeSimulationFrame tradeSimulationFrame = new TradeSimulationFrame();

        JPanel tradePanel = (JPanel) tradeSimulationFrame.getContentPane().getComponent(0);
        cardPanel.add(tradePanel, "TradeSimulation");

        return this;
    }

    /**
     * Build the application
     *
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Application");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setSize(1000, 800);
        application.add(cardPanel);
        return application;
    }

    /**
     * Show the trade simulation
     */
    public void showTradeSimulation() {
        cardLayout.show(cardPanel, "TradeSimulation");
    }
}