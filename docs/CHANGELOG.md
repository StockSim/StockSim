# Project Changelog

## Tags

- [Unreleased](#Unreleased)
- [0.2.0](#0.2.0)
- [0.1.0](#0.1.0)

## Unreleased

### New Features

- Implemented `MarketObserver` to update user data when stock market changes
- Implemented `FontManager` to set custom font for GUI components
- Implemented log out use case and added tests

### Internal Changes

- Refactored `StockMarket` to `MarketTracker`
- Refined all UI components to have a consistent style
- Separate API update config into `market-tracker-config` file and implement `ConfigLoader` to load static configuration

### Bug Fixes

- Fixed `MarketTracker` not updating prices after first initialization
- Fixed `AssetPanel` and `PortfolioPanel` not updating after buy use case
- Fixed `DashboardPanel` to call the service manager when user clicks logout button

## 0.2.0

### New Features

- Added `ViewHistory` use case to view transaction history
- Added GUI components for view transaction history page
- Implemented login use case
- Implemented periodic update of stock information from data access interface in `StockMarket` that self-adjusts based
  on API rate limit
- Changed API to Finnhub API
- Implemented integration of `StockMarket` and `StockDataAccessObject`

### Internal Changes

- Implemented `ClientSessionManager` for client-side session management
- Implemented credential verification in `BuyStockController`
- Moved `ValidationException` into a separate class in utility
- Implemented `StockDataAccess` to retrieve current market prices for set tickers
- Modified `StockMarket` to utilize new `IStockDataAccess` return type
- Updated Maven configuration with API request dependencies
- Added config resource file for 30 preset ticker names
- Modified `InMemoryStockDataAccessObject` to utilize new `IStockDataAccess` return type
- Added new API calls in `StockDataAccess` to retrieve and store company name and industry
- Modified `Stock`, `StockMarket`, and `InMemoryStockDataAccessObject` to store and utilize new company and industry
  data
- Modified `InMemoryUserDataAccessObject` to utilize `ViewHistoryDataAccessInterface`
- Implemented filter field in market search panel
- Changed password input field to use `JPasswordField`
- Added `getAssets` method in user entity
- Reformatted app builder to accept more configs
- Implemented singleton pattern for service manager
- Added test cases for `ViewHistoryInteractor` with mock buy and sell transactions
- Refined UI components, extracted table component to its own class
- Refactored `ServiceManager` registration process into object constructor

### Bug Fixes

- Fixed `TransactionHistoryPanel` not updating after buy use case

## 0.1.0

### New Features

- Added `SessionManager` to validate client requests by credential and manage active sessions
- Added GUI components for trading page

### Internal Changes

- Created documentation for GitHub workflow and pull request template
- Added Maven workspace and configuration
- Added Use Cases 4 and 5
- Implemented entities for stock and stock market
- Implemented entities for user, portfolio, and transaction
- Added synchronization checks and null checks in `StockMarket`
- Modified `Portfolio` constructor to match the optional return value of `StockMarket.getStock`
- Modified `Portfolio` constructor to take `Map<String, UserStock>` as parameter and remove synchronization with
  `StockMarket` data
- Updated `UserStock`: Renamed `PurchasePrice` to `cost`
- Implemented controller and presenter for execute buy use case
- Implemented framework for view component update
- Added GUI components for trading page
- Implemented buy use case interactor and set up unit tests
- Removed outdated classes and methods that are incompatible with current codebase version
- Updated Maven configuration with unit test and API request dependencies
- Implemented the frontend framework

### Bug Fixes
