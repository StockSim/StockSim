package interface_adapter.execute_buy;

import use_case.execute_buy.ExecuteBuyDataAccessInterface;
import use_case.execute_buy.ExecuteBuyInputBoundary;
import use_case.execute_buy.ExecuteBuyInputData;
import utility.ClientSessionManager;

public class ExecuteBuyController {

    private final ExecuteBuyInputBoundary interactor;

    public ExecuteBuyController(ExecuteBuyInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String ticker, String quantity) {
        final ExecuteBuyInputData data = new ExecuteBuyInputData(
                ClientSessionManager.Instance().getCredential(),
                ticker,
                Integer.parseInt(quantity));

        interactor.execute(data);
    }
}
