package app;

import javax.swing.*;

/**
 * The main class of the application
 */
public class Main {
    /**
     * The main method of the application
     *
     * @param args: the arguments passed to the application
     */
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder.addTradeSimulation().build();

        appBuilder.showTradeSimulation();

        application.pack();
        application.setVisible(true);
    }
}