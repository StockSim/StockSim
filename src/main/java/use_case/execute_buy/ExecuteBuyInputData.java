package use_case.execute_buy;

public record ExecuteBuyInputData(
        String credential,
        String ticker,
        int quantity
) {
}
