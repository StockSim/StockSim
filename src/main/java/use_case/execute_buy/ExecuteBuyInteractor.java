package use_case.execute_buy;

import entity.*;

import java.util.Date;

/**
 * The Execute Buy Interactor.
 */
public class ExecuteBuyInteractor implements ExecuteBuyInputBoundary {

    private final ExecuteBuyDataAccessInterface dataAccess;
    private final ExecuteBuyOutputBoundary outputPresenter;

    public ExecuteBuyInteractor(ExecuteBuyDataAccessInterface dataAccess, ExecuteBuyOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputPresenter = outputBoundary;
    }

    @Override
    public void execute(ExecuteBuyInputData data) {
        try {
            // Get current user
            User currentUser = dataAccess.getUserWithCredential(data.credential());

            // Get stock and quantity
            String ticker = data.ticker();
            int quantity = data.quantity();
            Stock stock = StockMarket.Instance().getStock(ticker).orElseThrow(StockNotFoundException::new);

            double totalCost = stock.getPrice() * quantity;
            if (isBalanceSufficient(currentUser, totalCost)) {
                // Deduct balance
                currentUser.deductBalance(totalCost);

                // Update portfolio
                Portfolio portfolio = currentUser.getPortfolio();
                updateOrAddStockToPortfolio(portfolio, stock, quantity);

                // Add transaction
                Date timestamp = new Date();
                Transaction transaction = new Transaction(timestamp, ticker, quantity, stock.getPrice(), "buy");
                currentUser.getTransactionHistory().addTransaction(transaction);

                // Prepare success view
                outputPresenter.prepareSuccessView(new ExecuteBuyOutputData(
                        currentUser.getBalance(),
                        currentUser.getPortfolio()
                ));
            } else {
                throw new InsufficientBalanceException();
            }
        } catch (ExecuteBuyDataAccessInterface.ValidationException e) {
            outputPresenter.prepareValidationExceptionView();
        } catch (StockNotFoundException e) {
            outputPresenter.prepareStockNotFoundExceptionView();
        } catch (InsufficientBalanceException e) {
            outputPresenter.prepareInsufficientBalanceExceptionView();
        }
    }

    private boolean isBalanceSufficient(User user, double totalCost) {
        return user.getBalance() >= totalCost;
    }

    private void updateOrAddStockToPortfolio(Portfolio portfolio, Stock stock, int quantity) {
        portfolio.getUserStock(stock.getTicker())
                .ifPresentOrElse(
                        existingStock -> existingStock.updateUserStock(stock.getPrice(), quantity),
                        () -> portfolio.addStock(new UserStock(stock, stock.getPrice(), quantity))
                );
    }

    static class InsufficientBalanceException extends Exception {
    }

    static class StockNotFoundException extends Exception {
    }
}