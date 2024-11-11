package use_case.execute_buy;

import entity.Portfolio;

public record ExecuteBuyOutputData(
        double newBalance,
        Portfolio newPortfolio
) {
}
