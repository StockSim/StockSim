package interface_adapter.logout;

import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInputData;
import utility.ClientSessionManager;
import utility.ServiceManager;

public class LogoutController {

    private final LogoutInputBoundary interactor;

    public LogoutController(LogoutInputBoundary interactor) {
        this.interactor = interactor;
        ServiceManager.Instance().registerService(LogoutController.class, this);
    }

    public void execute() {
        interactor.execute(new LogoutInputData(ClientSessionManager.Instance().getCredential()));
    }
}
