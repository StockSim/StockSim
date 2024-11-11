package view;

import view.view_event.ViewEvent;

import java.util.HashMap;
import java.util.Map;

public class ViewManager {

    // singleton instance
    private volatile static ViewManager instance;

    // map to store view
    private final Map<String, ViewBase> views;

    // singleton constructor
    private ViewManager() {
        views = new HashMap<>();
    }

    public static synchronized ViewManager Instance() {
        if (instance == null) {
            instance = new ViewManager();
        }
        return instance;
    }

    public void registerView(ViewBase view) {
        views.put(view.getName(), view);
    }

    public void unregisterView(ViewBase view) {
        views.remove(view.getName());
    }

    // broadcast event to target view
    public void sendViewEvent(ViewEvent event) {
        for (ViewBase view : views.values()) {
            view.receiveViewEvent(event);
        }
    }
}