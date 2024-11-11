package view;

import view.view_event.ViewEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class ViewBase {

    private final String name;
    private final List<IComponent> components;

    public ViewBase(String name) {
        this.name = name;
        this.components = new ArrayList<IComponent>();
    }

    public void registerComponent(IComponent component) {
        this.components.add(component);
    }

    public void unregisterComponent(IComponent component) {
        this.components.remove(component);
    }

    public String getName() {
        return name;
    }

    // broadcast event to components
    public void receiveViewEvent(ViewEvent event) {
        for (IComponent component : components) {
            component.receiveViewEvent(event);
        }
    }

}