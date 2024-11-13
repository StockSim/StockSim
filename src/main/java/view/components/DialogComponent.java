package view.components;

import utility.ViewManager;
import view.IComponent;
import view.view_events.DialogEvent;
import view.view_events.EventType;
import view.view_events.ViewEvent;

import javax.swing.*;
import java.util.EnumSet;

public class DialogComponent implements IComponent {

    private boolean dialogShown = false; // Flag to check if a dialog is already being shown

    public DialogComponent() {
        // Register this component with ViewManager
        ViewManager.Instance().registerComponent(this);
    }

    @Override
    public void receiveViewEvent(ViewEvent event) {
        if (event instanceof DialogEvent dialogEvent && !dialogShown) {
            // Set dialogShown to true to indicate that a dialog is open
            dialogShown = true;
            // Show dialog and reset dialogShown once the dialog is closed
            SwingUtilities.invokeLater(() -> showDialog(dialogEvent.getTitle(), dialogEvent.getMessage()));
        }
    }

    // Helper method to show dialog with title and message
    private void showDialog(String title, String message) {
        JOptionPane.showMessageDialog(
                null,  // Parent component (null centers it on the screen)
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE
        );
        // Reset the flag after the dialog is closed
        dialogShown = false;
    }

    @Override
    public EnumSet<EventType> getSupportedEventTypes() {
        // This component only supports DIALOG events
        return EnumSet.of(EventType.DIALOG);
    }
}