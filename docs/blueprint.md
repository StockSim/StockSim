# Project Specification for Group 184

## Team Name

StockSim (TBD)

## Domain

Stock trading simulation and management.

## Software Specification

The Trading Simulation App aims to provide users with a practice environment to simulate trading activities and develop trading skills. Users will receive an initial balance depending on the selected difficulty level and perform buy/sell operations using various stock tickers. A key feature is monitoring balances and enforcing a "game over" scenario if the balance drops below zero.

## User Stories

1. As a new user,
I want to register an account and receive an initial virtual balance,
so that I can start practicing trades in the simulation environment.

2. As a user participating in the trading simulation,
I want to buy or sell stocks through the trading window,
so that I can practice trading strategies and see how market fluctuations impact my portfolio.

3. As a user with active trades,
I want to monitor my portfolio in real time,
so that I can make informed decisions about when to buy or sell stocks.

4. **Yue Cheng**: I won a million in the lottery, and I plan to use it for investment. However, before making any real transactions, I would like to conduct some practice. I am interested in using this program to register an account, log in, and receive an initial sum of startup funds that will allow me to simulate buying and selling stocks. I hope the software will provide basic stock information, such as ticker symbols, prices, and available quantities.

## Proposed Entities for the Domain

1. User
   - Instance Variables:
     - `String username`
     - `String email` [OPTIONAL]
     - `String password`
     - `double balance` [cash?] // not need?
     - `Cash cash`
     - `Portfolio portfolio`
   - Methods:
     - `getUsername()`
     - `getCash() { return this.cash }`
     - `getPortfolio() { return this.portfolio }`

2. NetAsset [Abstract Class]
   - Instance Variables:
     - `double totalBalance`
   - Methods:
     - `getValue(User user) { return this.totalBalance = User1.getCash().getValue() + User1.getPortfolio().getValue() }`

3. Portfolio [Extends Asset]
   - Instance Variables:
     - `List<Stock> stocks`
   - Methods:
     - `getValue() { [some calculation] }`

4. Cash [Extends Asset]
   - Instance Variables:
     - `double cash`
   - Methods:
     - `getValue() { return this.cash }`

5. Stock
   - Instance Variables:
     - `String ticker`
   - Methods:
     - `cost(priceOfLastTrade)`


## Proposed API for the Project

- [Alpha Vantage](https://www.alphavantage.co/)

## Scheduled Meeting Times + Mode of Communication

- Every Monday 5pm-6pm in person
