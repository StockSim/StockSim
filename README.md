# 🔧 Installation
To use the app, please refer to the `main` branch, where we release the formal build

To learn about our latest update and new features we are working on, please check the `dev` branch


# ℹ️ Overview

## 📈 StockSim
StockSim is a desktop trading application designed to simulate real-world market conditions. It provides business students and traders with a risk-free setting to learn, practice, and polish their trading skills.

## ✍ Authors
| Name          | GitHub Username                                         |
| ------------- | ------------------------------------------------------- |
| Angel Chen    | [`AngelChen09`](https://github.com/AngelChen09)         |
| Yue Cheng     | [`ivorkchan`](https://github.com/ivorkchan)             |
| Corrine Xiang | [`TheGreatCorrine`](https://github.com/TheGreatCorrine) |
| Raine Yang    | [`Raine-Yang-UofT`](https://github.com/Raine-Yang-UofT) |
| Jifeng Qiu    | [`thqi3541`](https://github.com/thqi3541)               |

# 🔑 Key Features
__📑Table of Contents__
  - [Use Cases and User Stories](#use-cases-and-user-stories)
    - [Use Case 1: User Registration](#use-case-1-user-registration)
    - [Use Case 2: User Log In](#use-case-2-user-login)
    - [Use Case 3: User Log Out](#use-case-3-execute-trades-buy)
    - [Use Case 4: Execute Trades BUY](#use-case-4-execute-trades-buy)
    - [Use Case 5: Execute Trades SELL](#use-case-5-execute-trades-sell)
    - [Use Case 6: View Transaction History](#use-case-6-view-transaction-history)
  - [Optional Use Cases](#optional-use-cases) (not yet implemented)
    - [Use Case 7: View Ranking](#use-case-7-view-ranking)
    - [Use Case 8: Password Reset + Email Verification](#use-case-8-password-reset--email-verification)
    - [Use Case 9: Feedback / Support](#use-case-9-feedback--support)
    - [Use Case 10: Minimum number of trades](#use-case-10-minimum-number-of-trades)
  - [Installation Guide](#installation-guide)
  - [Financial Terminologies](#financial-terminologies)
  - [Accessibility](#accessibility)
  - [UI Design](#ui-design)



## Use Cases and User Stories

user can check portfolio/ position/ history - a complete flow

### Use Case 1: User Registration

User Story: As a new user, I want to create a new account, so that I can log into the app and start trading. To sign up/register, I need to type in a username, a password, and a repeat password.

- Interactor: `RegistrationInteractor`
- Controller: `RegistrationController`
- Presenter: `RegistrationView`
- Steps:
  - The user selects "Sign Up" and types in the required information (View -> Controller).
  - The Registration Controller loads the registration info to the Interactor. The Interactor verifies data compliance and creates a new account (Controller -> Interactor).
  - If registration is successful, the RegistrationInteractor instructs the RegistrationView to display a success message and guide the user to log in (Interactor -> Presenter).
  - If registration fails, the RegistrationInteractor instructs the RegistrationView to display an error message (Interactor -> Presenter).

### Use Case 2: User Login

User Story: As a returning user, I want to log into my account with my username, and password (credentials), so that I can resume all trading activities, balance, etc.

- Interactor: `LoginInteractor`
- Controller: `LoginController`
- Presenter: `LoginPresenter`
- Steps:
  - The user inputs their login information(username and password) and submits (View -> Controller).
  - The LoginController verifies credentials and processes the login (Interactor).
  - If the login is successful, the LoginInteractor loads the user data and directs the LoginView to the trading interface (Interactor -> Presenter).
  - If the login fails, the LoginInteractor instructs the LoginView to display an error message (Interactor -> Presenter).

### Use Case 4: Execute Trades BUY

User Story: As a user, I want to buy some shares of a stock so that I can hold a long position in it. For example, I can buy 10 shares of APPL (Apple Inc.) at $5 per share.

- Interactor: `ExecuteBuyInteractor`
- Controller: `ExecuteBuyController`
- Presenter: `ExecuteBuyPresenter`
- Steps:
  - The user selects a ticker(symbol of the stock) and quantity to buy, and submits a buy order in the trading window (Controller -> Interactor).
  - The ExecuteBuyInteractor processes the order and updates the account balance and portfolio (Interactor).
  - The ExecuteBuyInteractor returns the trade results to the TradeView, displaying success or error messages (Interactor -> Presenter)

### Use Case 5: Execute Trades SELL

User Story: As a user, I want to sell some shares of a stock that I am currently holding, so that the position of the stock will decrease and I will get some money. For example, I have 10 shares of APPL (Apple Inc.), so I can sell 5 shares.

- Interactor: `ExecuteSellInteractor`
- Controller: `ExecuteSellController`
- Presenter: `ExecuteSellPresenter`
- Steps:
  - The user selects a ticker(symbol of the stock) and quantity to buy, and submits a sell order in the trading window (Controller -> Interactor).
  - The ExecuteSellInteractor processes the order and updates the account balance and portfolio (Interactor).
  - The ExecuteSellInteractor returns the trade results to the TradeView, displaying success or error messages (Interactor -> Presenter).


# Optional Use Cases
**Below are optional features, and still need construction:**

### Use Case 6: View Transaction History

- User Story:
  - As a trader, I want to access my transaction history and so that I can make more informed decisions and learn from past trades.
  - (Optional) As a trader, I want to sort by column and filter my current stock holdings to display select rows and quantities so I can view certain trades.
- Implementation:
  - Interactor: `ViewHistoryInteractor`
  - Controller: `ViewHistoryController`
  - Presenter: `ViewHistoryPresenter`
- Steps:
  - The user clicks on the Transaction History button on the dashboard and the event is listened to and sent to the Controller. (View -> Controller).
  - The TransactionHistoryController gets the users' credentials and sends the data to the Interactor. (Controller -> Interactor)
  - The HistoryInteractor then prepares the view, so the panel is switched to the TransactionHistoryPanel. (Interactor -> Presenter)

**Below are some other use cases we might consider implementing:**

### Use Case 7: View Ranking

- User Story:
  - As a trader, I want to view my rank compared to other traders based on portfolio performance (portfolio values) so I can assess my relative performance and strive to improve.
- Implementation:
  - Interactor: CurrentUser
  - Controller: RankingController
  - Presenter: RankingView
- Steps:
  - The user selects a page and/or rows per page for pagination-based item selection from the selection provided near the bottom (Interator to Controller).
  - The RankingController retrieves the current user's current rank by comparing their portfolio's total values with other user and formats all user's assets and portfolio's total values by ranking them.
  - The RankingController returns the ranking data to the RankingView, and provide optional details, like viewing the top 3 or top 5 users by listing them in descending order.

### Use Case 8: Password Reset + Email Verification

- User Story:
  - As a trader, I want to reset my password securely using email verification, so I can reobtain access to my account if I forgot my password.
- Implementation:
  - Interactor: ReturningUser
  - Controller: PasswordResetController
  - Presenter: PasswordResetView
- Steps:
  - The user selects "ForgotPassword" and enters their registered email address (Interactor to Controller).
  - The PasswordResetController verifies if the email exists in the system and if existed, generating a one-time link that allow the user to reset the password.
  - The PasswordResetController sends an email to the user's registered email address with the one-time link using a service like JavaMail API (optional).
  - The user clicks the one-time link and the PasswordResetController checks if the one-time link is still valid (within the validity date). If the verification is successful, the PasswordResetView is prompted to enter a new password and confirm it.
  - The PasswordResetController validates the new password (checking if contains minimum number of characters, length or uppercases requirements) and updates it in the system.
  - The PasswordResetView confirms the successful password reset and directs the user back to login screen.

### Use Case 9: Feedback / Support

- User Story:
  - As a user, I want to report technical issues or provide feedback, so I can communicate with the support team if I encounter issues or have any suggestions.
- Implementation:
  - Interactor: CurrentUser
  - Controller: FeedbackController
  - Presenter: FeedbackView
- Steps:
  - The user navigates to the "Feedback/Support" section in the application.
  - The user submits a report ticket or feedback message, which can include details like issue types, description, and optional attachments (Interactor to Controller)
  - FeedbackController validates the user's message content (check for empty fields or inappropriate content). If valid, FeedbackController saves the feedback or support request in the database for tracking and management and sends an acknowledgment email to the user.
  - FeedbackView confirms the successful submission and provides the user with a ticket ID for future reference if needed.

### Use Case 10: Minimum number of trades

- User Story:
  - As a trader, I want to be aware of my total number of trades to ensure I meet the minimum trading requirements for account benefits or eligibility for promotions.
- Implementation:
  - Interactor: CurrentUser
  - Controller: TradeRequirementController
  - Presenter: TradeRequirementView
- Steps:
  - The trader selects "View Trade Activity" to view their trade count and check their progress toward any trade-related requirements.
  - The TradeRequirementController retrieves the user's trade history from the database and counts the current total number of trades.
  - The TradeRequirementController checks this total against the minimum threshold, or other requirements, such as qualifying for promotions or eligibility
  - The TradeRequirementController sends the data to the TradeRequirementView and displays the user's trade count, progress toward the minimum trade requirement, and any relevant messages (eg: "You need 5 more trades to qualify").

## Financial Terminologies
  - `Balance`: refers to the cash you are holding, e.g., $100 cash in hand.
  - `Portfolio`: refers to the collection of the stocks you are holding, e.g. if you buy 100 shares of APPL(Apple Inc.), and sell 50 shares of AMZN(Amazon), your portfolio will include these two stocks.
    - Portfolio Value: refers to the market value of the stocks you are holding, e.g., if the current market price of APPL is $10 per share and the current market price of AMZN is $5 per share, your portfolio value is 100*$10 + (-50)*$5 = $750
  - `Assets`: your wealth, `assets` = `cash balance` + `portfolio`
  - `Position`: refers to the __quantity__ of shares you hold. It can be __positive__(long position), __zero__, or __negative__(short). ❗notice that quantity can be negative
    - positive quantity is easy to understand, e.g., if you buy 100 shares of APPL, your position will be +100.
    - negative quantity means you borrow some shares from others, e.g. if sell 100 shares of APPL that you don't own, your position will be -100. However, you are expected to buy the 100 shares back one day.
  - Order Entry： You send `buy` or `sell` orders to the market
    - `Buy`(`+`): when you buy shares of stocks, you pay cash, and the stock's quantity increases.
    - `Sell`(`-`): when you sell shares of stocks, you get cash, and the stock's quantity decreases.

## API Keys Setup
To run this program locally, you need to set up two API keys in a `.env.local` file located in the project's root directory:

### STOCK_API_KEY

- Purpose: Used to access stock market data via the Finnhub API.
- Instructions to Obtain:
  - Create a Finnhub account at https://finnhub.io/.
  - Click "Get free API key" to generate your API key.
  - Add the key to your `.env.local` file as:
  ```STOCK_API_KEY=your_finnhub_api_key```

### MONGODB_API_KEY

- Purpose: Used to connect to our project's MongoDB cluster.
- Instructions to Obtain:
  - Create a MongoDB Atlas account at https://www.mongodb.com/.
  - Share your MongoDB username and IP address with our team to be added to the project's cluster.
    (Note: All University of Toronto IP addresses are pre-whitelisted.)
  - Once added, you'll receive the MongoDB API key.
  - Add the key to your `.env.local` file as:
  ```MONGODB_API_KEY=your_mongodb_api_key```


## Accessibility

## UI Design

[View in Figma](https://www.figma.com/proto/tm5D32ALPuOvfL2lvpir9c/StockSim?page-id=0%3A1&node-id=1-3&node-type=canvas&viewport=112%2C276%2C0.21&t=eb23w81NZYAyvS8O-1&scaling=contain&content-scaling=fixed&starting-point-node-id=1%3A3)
