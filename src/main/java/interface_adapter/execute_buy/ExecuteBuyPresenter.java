package interface_adapter.execute_buy;

import use_case.execute_buy.ExecuteBuyOutputBoundary;
import use_case.execute_buy.ExecuteBuyOutputData;
import view.*;
import view.view_event.*;

public class ExecuteBuyPresenter implements ExecuteBuyOutputBoundary {

    @Override
    public void prepareSuccessView(ExecuteBuyOutputData outputData) {
        ViewManager.Instance().sendViewEvent(
                new UpdatePortfolioEvent(
                        outputData.newPortfolio(),
                        outputData.newBalance()
                )
        );
    }

    @Override
    public void prepareInsufficientBalanceExceptionView() {
    }

    @Override
    public void prepareStockNotFoundExceptionView() {
    }

    @Override
    public void prepareValidationExceptionView() {
    }
}