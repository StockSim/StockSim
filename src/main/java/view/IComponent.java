package view;

import view.view_events.EventType;
import view.view_events.ViewEvent;

import java.util.EnumSet;

public interface IComponent {
    /**
     * Handles a received event.
     *
     * @param event The event to handle.
     */
    void receiveViewEvent(ViewEvent event);

    /**
     * Checks if the component is interested in a specific type of event.
     *
     * @param type The type of the event.
     * @return True if the component supports this event type; false otherwise.
     */
    default boolean supportsEvent(EventType type) {
        return getSupportedEventTypes().contains(type);
    }

    /**
     * Returns the set of supported event types for this component.
     * Components can override this method to specify their supported events.
     *
     * @return EnumSet of supported event types.
     */
    default EnumSet<EventType> getSupportedEventTypes() {
        return EnumSet.noneOf(EventType.class); // Default: no supported events
    }
}