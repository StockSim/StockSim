package interface_adapter.logout;

import use_case.logout.LogoutOutputBoundary;
import utility.ServiceManager;
import view.ViewManager;
import view.view_events.SwitchPanelEvent;

public class LogoutPresenter implements LogoutOutputBoundary {
    public LogoutPresenter() {
        ServiceManager.Instance().registerService(LogoutOutputBoundary.class, this);
    }

    @Override
    public void prepareSuccessView() {
        ViewManager.Instance().broadcastEvent(new SwitchPanelEvent("LogInPanel"));
    }
}
