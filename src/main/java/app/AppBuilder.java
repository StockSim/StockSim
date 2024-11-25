package app;

import data_access.InMemoryUserDataAccessObject;
import data_access.StockDataAccessObject;
import interface_adapter.execute_buy.ExecuteBuyController;
import interface_adapter.execute_buy.ExecuteBuyPresenter;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.view_history.ViewHistoryController;
import interface_adapter.view_history.ViewHistoryPresenter;
import use_case.execute_buy.ExecuteBuyDataAccessInterface;
import use_case.execute_buy.ExecuteBuyInputBoundary;
import use_case.execute_buy.ExecuteBuyInteractor;
import use_case.execute_buy.ExecuteBuyOutputBoundary;
import use_case.login.LoginDataAccessInterface;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.view_history.ViewHistoryDataAccessInterface;
import use_case.view_history.ViewHistoryInputBoundary;
import use_case.view_history.ViewHistoryInteractor;
import use_case.view_history.ViewHistoryOutputBoundary;
import utility.MarketTracker;
import utility.ServiceManager;
import view.ViewManager;
import view.components.DialogComponent;
import view.panels.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * A builder class for the application.
 * Manages application frame creation, service registration, and view management.
 */
public class AppBuilder {
    // Default dimensions and title for the application window
    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = 900;
    private static final String DEFAULT_TITLE = "StockSim";

    // Components for the application
    private final JPanel cardPanel;
    private final CardLayout cardLayout;
    private final Map<String, JPanel> panels;
    private String initialPanel = "LogInPanel";

    // Custom dimensions and title for the application window
    private int width = DEFAULT_WIDTH;
    private int height = DEFAULT_HEIGHT;
    private String title = DEFAULT_TITLE;

    /**
     * Constructor for the AppBuilder class
     */
    public AppBuilder() {
        this.cardPanel = new JPanel();
        this.cardLayout = new CardLayout();
        this.panels = new HashMap<>();
        this.cardPanel.setLayout(cardLayout);
    }

    /**
     * Sets custom title for the application window
     */
    public AppBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Sets custom dimensions for the application window
     */
    public AppBuilder withDimensions(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Sets the initial panel to be displayed
     */
    public AppBuilder withInitialPanel(String panelName) {
        this.initialPanel = panelName;
        return this;
    }

    /**
     * Add all panels
     */
    public AppBuilder addAllPanels() {
        addPanel("LogInPanel", new LogInPanel());
        addPanel("SignUpPanel", new SignUpPanel());
        addPanel("DashboardPanel", new DashboardPanel());
        addPanel("TradeSimulationPanel", new TradeSimulationPanel());
        addPanel("TransactionHistoryPanel", new TransactionHistoryPanel());
        return this;
    }

    /**
     * Helper method to add a panel to both the card layout and panels map
     */
    private void addPanel(String name, JPanel panel) {
        panels.put(name, panel);
        cardPanel.add(panel, name);
    }

    /**
     * Adds the dialog component
     */
    public AppBuilder addDialogComponent() {
        ServiceManager.Instance().registerService(DialogComponent.class, new DialogComponent());
        return this;
    }

    /**
     * Initializes all required services
     */
    private void initializeServices() {
        // 1. Initialize DAOs first
        new InMemoryUserDataAccessObject();
        MarketTracker.Instance().initialize(new StockDataAccessObject());

        // 2. Initialize Presenters
        new LoginPresenter();
        new LogoutPresenter();
        new ExecuteBuyPresenter();
        new ViewHistoryPresenter();

        // 3. Initialize Interactors
        new LoginInteractor(ServiceManager.Instance().getService(LoginDataAccessInterface.class), ServiceManager.Instance().getService(LoginOutputBoundary.class));
        new LogoutInteractor(ServiceManager.Instance().getService(LogoutOutputBoundary.class));
        new ExecuteBuyInteractor(ServiceManager.Instance().getService(ExecuteBuyDataAccessInterface.class), ServiceManager.Instance().getService(ExecuteBuyOutputBoundary.class));
        new ViewHistoryInteractor(ServiceManager.Instance().getService(ViewHistoryDataAccessInterface.class), ServiceManager.Instance().getService(ViewHistoryOutputBoundary.class));

        // 4. Initialize Controllers
        new LoginController(ServiceManager.Instance().getService(LoginInputBoundary.class));
        new LogoutController(ServiceManager.Instance().getService(LogoutInputBoundary.class));
        new ExecuteBuyController(ServiceManager.Instance().getService(ExecuteBuyInputBoundary.class));
        new ViewHistoryController(ServiceManager.Instance().getService(ViewHistoryInputBoundary.class));
    }

    /**
     * Builds and returns the configured application frame
     */
    public JFrame build() {
        // Initialize services
        initializeServices();

        // Configure view manager
        ViewManager.Instance().setCardLayout(cardLayout, cardPanel);

        // Create and configure the main frame
        JFrame application = new JFrame(title);
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setSize(width, height);
        application.add(cardPanel);

        // Show initial panel
        cardLayout.show(cardPanel, initialPanel);

        return application;
    }
}
