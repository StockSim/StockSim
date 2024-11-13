package view.view_events;

import java.util.EnumSet;

public class SwitchPanelEvent extends ViewEvent {
    private final String panelName;

    /**
     * Creates a SwitchPanelEvent with only the SWITCH_PANEL type.
     *
     * @param panelName The name of the panel to switch to.
     */
    public SwitchPanelEvent(String panelName) {
        super(EnumSet.of(EventType.SWITCH_PANEL));
        this.panelName = panelName;
    }

    /**
     * Creates a SwitchPanelEvent with a customizable set of event types.
     * This is useful if additional types are needed for this event.
     *
     * @param panelName The name of the panel to switch to.
     * @param types     The set of event types associated with this event.
     */
    public SwitchPanelEvent(String panelName, EnumSet<EventType> types) {
        super(types);
        this.panelName = panelName;
    }

    /**
     * Returns the name of the panel to switch to.
     *
     * @return The panel name.
     */
    public String getPanelName() {
        return panelName;
    }
}