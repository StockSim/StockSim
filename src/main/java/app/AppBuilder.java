package app;

import data_access.InMemoryStockDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import interface_adapter.execute_buy.ExecuteBuyController;
import interface_adapter.execute_buy.ExecuteBuyPresenter;
import use_case.execute_buy.ExecuteBuyInputBoundary;
import use_case.execute_buy.ExecuteBuyInteractor;
import utility.ServiceManager;
import utility.ViewManager;
import view.components.DialogComponent;
import view.panels.DashboardPanel;
import view.panels.LogInPanel;
import view.panels.SignUpPanel;
import view.panels.TradeSimulationPanel;

import javax.swing.*;
import java.awt.*;

// TODO: better ways of import all the services and panels
// TODO: maybe create a config file for the building process

/**
 * A builder class for the application.
 * This class now works as a setup utility to add panels to ViewManager, build the main application frame, and manage the ServiceLocator.
 */
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    // Internal ServiceLocator for managing controllers, interactors, DAOs, and presenters
    private final ServiceManager serviceManager = new ServiceManager();

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Add the login and sign-up panels to the application
     *
     * @return the builder
     */
    public AppBuilder addAuthenticationPanels() {
        LogInPanel logInPanel = new LogInPanel();
        SignUpPanel signUpPanel = new SignUpPanel();

        // Add panels to the card layout
        cardPanel.add(logInPanel, "LogInPanel");
        cardPanel.add(signUpPanel, "SignUpPanel");

        return this;
    }

    /**
     * Add the dashboard panel to the application
     *
     * @return the builder
     */
    public AppBuilder addDashboardPanel(String username, double cash, double position) {
        DashboardPanel dashboardPanel = new DashboardPanel(username, cash, position);

        // Add the dashboard panel to the card layout
        cardPanel.add(dashboardPanel, "DashboardPanel");

        return this;
    }

    /**
     * Add the trade simulation panel to the application
     *
     * @return the builder
     */
    public AppBuilder addTradeSimulationPanel() {
        TradeSimulationPanel tradeSimulationPanel = new TradeSimulationPanel();

        // Add the trade simulation panel to the card layout
        cardPanel.add(tradeSimulationPanel, "TradeSimulationPanel");

        return this;
    }

    public AppBuilder addDialogComponent() {
        DialogComponent dialogComponent = new DialogComponent();
        return this;
    }

    /**
     * Build the application frame, initialize controllers, interactors, DAOs, and presenters, and register them in ServiceLocator.
     *
     * @return the application frame
     */
    public JFrame build() {
        JFrame application = new JFrame("Application");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setSize(1000, 800);
        application.add(cardPanel);

        //Step 0: Initialize and register InMemoryStockDataAccessObject (DAO)
        InMemoryStockDataAccessObject stockDataAccessObject = new InMemoryStockDataAccessObject();
        ServiceManager.registerService(InMemoryStockDataAccessObject.class, stockDataAccessObject);

        // Step 1: Initialize and register InMemoryUserDataAccessObject (DAO)
        InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
        ServiceManager.registerService(InMemoryUserDataAccessObject.class, userDataAccessObject);

        // Step 2: Initialize and register ExecuteBuyPresenter (OutputBoundary)
        ExecuteBuyPresenter executeBuyPresenter = new ExecuteBuyPresenter();
        ServiceManager.registerService(ExecuteBuyPresenter.class, executeBuyPresenter);

        // Step 3: Initialize ExecuteBuyInteractor with DAO and Presenter
        ExecuteBuyInputBoundary executeBuyInteractor = new ExecuteBuyInteractor(userDataAccessObject, executeBuyPresenter);

        // Step 4: Initialize ExecuteBuyController with ExecuteBuyInteractor and register
        ExecuteBuyController executeBuyController = new ExecuteBuyController(executeBuyInteractor);
        ServiceManager.registerService(ExecuteBuyController.class, executeBuyController);

        // Set ViewManager to control panel switching with cardLayout and cardPanel
        ViewManager.Instance().setCardLayout(cardLayout, cardPanel);

        // Show the LogInPanel initially
        cardLayout.show(cardPanel, "LogInPanel");

        return application;
    }
}