# Project Changelog

## Unreleased

### New Features:

### Bug Fixes:

### Internal Changes:

- Created documentation for GitHub workflow and pull request template.
- Added maven workspace and configuration.
- Added Use Cases 4 and 5.
- Implemented entities for stock and stock market.
- Implemented entities for user, portfolio, and transaction.
- Added synchronization checks and null checks in `StockMarket`
- Modified `Portfolio` constructor to match the optional return value of `StockMarket.getStock`
- Modified `Portfolio` constructor to take `Map<String, UserStock>` as parameter and remove synchronization with `StockMarket` data
- Updated `UserStock.java`: Rename `PurchasePrice` to `cost`
- Implemented `StockDataAccessObject` for api stock data retrieval and adjusted `StockMarket` to use new return type
