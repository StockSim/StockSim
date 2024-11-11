package view;

import view.view_event.ViewEvent;

public interface IComponent {
    void receiveViewEvent(ViewEvent event);
}