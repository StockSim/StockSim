package use_case.logout;

import utility.ClientSessionManager;
import utility.ServiceManager;
import utility.SessionManager;

public class LogoutInteractor implements LogoutInputBoundary {

    private final LogoutOutputBoundary outputPresenter;

    public LogoutInteractor(LogoutOutputBoundary outputPresenter) {
        this.outputPresenter = outputPresenter;
        ServiceManager.Instance().registerService(LogoutInputBoundary.class, this);
    }

    @Override
    public void execute(LogoutInputData data) {
        SessionManager.Instance().endSession(data.credential());
        ClientSessionManager.Instance().clearCredential();
        outputPresenter.prepareSuccessView();
    }
}
