package view.panels;

import utility.ViewManager;
import view.IComponent;
import view.components.ButtonComponent;
import view.components.InputComponent;
import view.view_events.EventType;
import view.view_events.SwitchPanelEvent;
import view.view_events.ViewEvent;

import javax.swing.*;
import java.awt.*;
import java.util.EnumSet;

public class LogInPanel extends JPanel implements IComponent {
    private final InputComponent usernameInput;
    private final InputComponent passwordInput;
    private final ButtonComponent logInButton;
    private final ButtonComponent signUpButton;

    public LogInPanel() {
        ViewManager.Instance().registerComponent(this);

        setLayout(new BorderLayout());

        setMinimumSize(new Dimension(150, 300));
        setPreferredSize(new Dimension(250, 400));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Log In");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1.0;

        // Initialize InputComponents for username and password
        usernameInput = new InputComponent("Username", 15);
        passwordInput = new InputComponent("Password", 15);

        gbc.gridy = 0;
        formPanel.add(usernameInput, gbc);
        gbc.gridy = 1;
        formPanel.add(passwordInput, gbc);

        // Initialize ButtonComponents for login and sign-up
        logInButton = new ButtonComponent("Log In");
        logInButton.addActionListener(e -> ViewManager.Instance().broadcastEvent(new SwitchPanelEvent("DashboardPanel")));
        gbc.gridy = 2;
        formPanel.add(logInButton, gbc);

        signUpButton = new ButtonComponent("Sign Up");
        signUpButton.addActionListener(e -> ViewManager.Instance().broadcastEvent(new SwitchPanelEvent("SignUpPanel")));
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(signUpButton, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    @Override
    public void receiveViewEvent(ViewEvent event) {
        if (event instanceof SwitchPanelEvent) {
            System.out.println("LogInPanel received a SwitchPanelEvent to switch panels.");
        }
    }

    @Override
    public EnumSet<EventType> getSupportedEventTypes() {
        return EnumSet.of(EventType.SWITCH_PANEL);
    }
}