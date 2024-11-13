package view.panels;

import utility.ViewManager;
import view.IComponent;
import view.components.ButtonComponent;
import view.components.InputComponent;
import view.view_events.DialogEvent;
import view.view_events.EventType;
import view.view_events.SwitchPanelEvent;
import view.view_events.ViewEvent;

import javax.swing.*;
import java.awt.*;
import java.util.EnumSet;

public class SignUpPanel extends JPanel implements IComponent {
    private final InputComponent usernameField;
    private final InputComponent passwordField;
    private final InputComponent repeatPasswordField;
    private final ButtonComponent signUpButton;
    private final ButtonComponent logInButton;

    public SignUpPanel() {
        ViewManager.Instance().registerComponent(this);

        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(150, 300));
        setPreferredSize(new Dimension(250, 400));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Create a new account");
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

        // Initialize InputComponents for username and passwords
        usernameField = new InputComponent("Username", 15);
        passwordField = new InputComponent("Password", 15);
        repeatPasswordField = new InputComponent("Repeat password", 15);

        gbc.gridy = 0;
        formPanel.add(usernameField, gbc);
        gbc.gridy = 1;
        formPanel.add(passwordField, gbc);
        gbc.gridy = 2;
        formPanel.add(repeatPasswordField, gbc);

        // Initialize sign-up button with DialogEvent action
        signUpButton = new ButtonComponent("Sign Up");
        signUpButton.addActionListener(e ->
                ViewManager.Instance().broadcastEvent(new DialogEvent("Sign Up", "Sign Up button clicked."))
        );
        gbc.gridy = 3;
        formPanel.add(signUpButton, gbc);

        // Initialize log-in button with SwitchPanelEvent action
        logInButton = new ButtonComponent("Go to Log In");
        logInButton.addActionListener(e ->
                ViewManager.Instance().broadcastEvent(new SwitchPanelEvent("LogInPanel"))
        );
        gbc.gridy = 4;
        formPanel.add(logInButton, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    @Override
    public void receiveViewEvent(ViewEvent event) {
        if (event instanceof SwitchPanelEvent || event instanceof DialogEvent) {
            System.out.println("SignUpPanel received an event: " + event.getClass().getSimpleName());
        }
    }

    @Override
    public EnumSet<EventType> getSupportedEventTypes() {
        // SignUpPanel supports both SWITCH_PANEL and DIALOG events
        return EnumSet.of(EventType.SWITCH_PANEL, EventType.DIALOG);
    }
}