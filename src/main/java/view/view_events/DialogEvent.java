package view.view_events;

import java.util.EnumSet;

public class DialogEvent extends ViewEvent {
    private final String title;
    private final String message;

    public DialogEvent(String title, String message) {
        super(EnumSet.of(EventType.DIALOG));
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }
}