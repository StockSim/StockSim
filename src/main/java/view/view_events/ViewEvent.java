package view.view_events;

import java.util.EnumSet;

public abstract class ViewEvent {
    private final EnumSet<EventType> types;

    public ViewEvent(EnumSet<EventType> types) {
        this.types = types;
    }

    /**
     * Returns the set of event types associated with this event.
     *
     * @return EnumSet of EventType.
     */
    public EnumSet<EventType> getTypes() {
        return types;
    }

    /**
     * Checks if this event has a specific type.
     *
     * @param type The EventType to check.
     * @return True if the event contains this type; false otherwise.
     */
    public boolean hasType(EventType type) {
        return types.contains(type);
    }
}