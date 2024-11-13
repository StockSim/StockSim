package view.panels;

import interface_adapter.execute_buy.ExecuteBuyController;
import utility.ServiceManager;
import utility.ViewManager;
import view.IComponent;
import view.components.ButtonComponent;
import view.components.InputComponent;
import view.view_events.DialogEvent;
import view.view_events.EventType;
import view.view_events.ViewEvent;

import javax.swing.*;
import java.awt.*;
import java.util.EnumSet;

public class OrderEntryPanel extends JPanel implements IComponent {
    private final JLabel titleLabel;
    private final InputComponent tickerField;
    private final InputComponent quantityField;
    private final JButton buyButton;
    private final JButton sellButton;

    public OrderEntryPanel() {
        setLayout(new BorderLayout());

        setMinimumSize(new Dimension(150, 300));
        setPreferredSize(new Dimension(150, 400));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Title Label
        titleLabel = new JLabel("Order Entry");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(titleLabel, BorderLayout.NORTH);

        // Form Panel for inputs
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1.0;

        // InputComponents for ticker and quantity
        tickerField = new InputComponent("Ticker", 10);
        quantityField = new InputComponent("Quantity", 10);

        gbc.gridy = 0;
        formPanel.add(tickerField, gbc);
        gbc.gridy = 1;
        formPanel.add(quantityField, gbc);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buyButton = new ButtonComponent("Buy");
        sellButton = new ButtonComponent("Sell");

        // Buy Button Action
        buyButton.addActionListener(e -> {
            String ticker = tickerField.getText();
            String quantity = quantityField.getText();

            ViewManager.Instance().broadcastEvent(new DialogEvent("Buy Order", "You want to buy " + quantity + " shares of " + ticker));

            // Retrieve the controller and execute the buy action
            ExecuteBuyController controller = ServiceManager.getService(ExecuteBuyController.class);
            controller.execute(ticker, quantity);
        });

        buttonPanel.add(buyButton);
        buttonPanel.add(sellButton);

        // Add Panels to Layout
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Order Entry Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        OrderEntryPanel orderEntryPanel = new OrderEntryPanel();
        frame.add(orderEntryPanel);
        frame.setVisible(true);
    }

    @Override
    public void receiveViewEvent(ViewEvent event) {
        // Placeholder for future event handling
    }

    @Override
    public EnumSet<EventType> getSupportedEventTypes() {
        // OrderEntryPanel currently supports no event types
        return EnumSet.noneOf(EventType.class);
    }
}