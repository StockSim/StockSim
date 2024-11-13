package use_case.execute_buy;

import entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import utility.exceptions.ValidationException;

class ExecuteBuyInteractorTest {

    private ExecuteBuyDataAccessInterface dataAccess;
    private ExecuteBuyOutputBoundary outputPresenter;
    private UserFactory userFactory;

    @BeforeEach
    void setUp() {
        userFactory = new UserFactory();
        dataAccess = Mockito.mock(ExecuteBuyDataAccessInterface.class);
        outputPresenter = Mockito.mock(ExecuteBuyOutputBoundary.class);
    }

    @Test
    void successTest() throws ValidationException {
        User mockUser = userFactory.create("testUser", "password");
        double initialBalance = 10000.0;
        mockUser.addBalance(initialBalance);

        when(dataAccess.getUserWithCredential("dummy")).thenReturn(mockUser);

        Stock stock = new Stock("XXXX", 100.0);

        try (MockedStatic<StockMarket> mockedStatic = Mockito.mockStatic(StockMarket.class)) {
            // prepare StockMarket mock
            StockMarket stockMarketMock = Mockito.mock(StockMarket.class);
            mockedStatic.when(StockMarket::Instance).thenReturn(stockMarketMock);

            // prepare StockMarket mock to return the stock
            when(stockMarketMock.getStock("XXXX")).thenReturn(Optional.of(stock));

            // prepare input data
            int quantityToBuy = 100;
            ExecuteBuyInputData inputData = new ExecuteBuyInputData("dummy", "XXXX", quantityToBuy);

            // create interactor
            ExecuteBuyInteractor interactor = new ExecuteBuyInteractor(dataAccess, outputPresenter);
            interactor.execute(inputData);

            // check if message is prepared
            verify(outputPresenter).prepareSuccessView(new ExecuteBuyOutputData(
                    mockUser.getBalance(),
                    mockUser.getPortfolio()
            ));

            // check if user portfolio contains the stock
            assertTrue(mockUser.getPortfolio().getUserStock("XXXX").isPresent(), "Portfolio should contain the ticker XXXX");

            // check if user stock quantity is correct
            UserStock userStock = mockUser.getPortfolio().getUserStock("XXXX").get();
            assertEquals(100, userStock.getQuantity(), "Portfolio should contain 100 shares of XXXX");

            // check if user balance is correctly reduced
            double totalPurchaseCost = stock.getPrice() * quantityToBuy;
            double expectedBalance = initialBalance - totalPurchaseCost;
            assertEquals(expectedBalance, mockUser.getBalance(), "User balance should be correctly reduced after purchase.");
        }
    }

    @Test
    void insufficientBalanceTest() throws ValidationException {
        // prepare user with insufficient balance
        User mockUser = userFactory.create("testUser", "password");
        mockUser.addBalance(500.0);

        // prepare dataAccess mock
        when(dataAccess.getUserWithCredential("dummy")).thenReturn(mockUser);

        // prepare Stock mock
        Stock stock = new Stock("XXXX", 100.0);

        try (MockedStatic<StockMarket> mockedStatic = Mockito.mockStatic(StockMarket.class)) {
            // prepare StockMarket mock
            StockMarket stockMarketMock = Mockito.mock(StockMarket.class);
            mockedStatic.when(StockMarket::Instance).thenReturn(stockMarketMock);

            // prepare StockMarket mock to return the stock
            when(stockMarketMock.getStock("XXXX")).thenReturn(Optional.of(stock));

            // prepare input data
            ExecuteBuyInputData inputData = new ExecuteBuyInputData("dummy", "XXXX", 100);

            // create interactor
            ExecuteBuyInteractor interactor = new ExecuteBuyInteractor(dataAccess, outputPresenter);
            interactor.execute(inputData);

            // check if unique view is prepared
            verify(outputPresenter).prepareInsufficientBalanceExceptionView();

            // check if user portfolio does not contain the stock
            assertFalse(mockUser.getPortfolio().getUserStock("XXXX").isPresent(), "Portfolio should not contain the ticker XXXX due to insufficient funds");
        }
    }
}