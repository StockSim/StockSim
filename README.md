# StockSim

- [StockSim](#stocksim)
  - [Members](#members)
  - [User Stories and Use Cases](#user-stories-and-use-cases)
    - [Use Case 1: User Registration](#use-case-1-user-registration)
    - [Use Case 2: User Login](#use-case-2-user-login)
    - [Use Case 3: Execute Trades BUY](#use-case-3-execute-trades-buy)
    - [Use Case 4: Execute Trades SELL](#use-case-4-execute-trades-sell)
    - [Use Case 5: Execute Trades SHORTSELL](#use-case-5-execute-trades-shortsell)
    - [Use Case 6: View Portfolio (Position)](#use-case-6-view-portfolio-position)
    - [Use Case 7: View Transaction History](#use-case-7-view-transaction-history)
    - [Use Case 8: View Ranking](#use-case-8-view-ranking)
    - [Use Case 9: Password Reset + Email Verification](#use-case-9-password-reset--email-verification)
    - [Use Case 10: Feedback / Support](#use-case-10-feedback--support)
    - [Use Case 11: Minimum number of trades](#use-case-11-minimum-number-of-trades)
  - [Prototype](#prototype)

## Members

| Name          | GitHub Username                                         |
| ------------- | ------------------------------------------------------- |
| Angel Chen    | [`AngelChen09`](https://github.com/AngelChen09)         |
| Yue Cheng     | [`ivorkchan`](https://github.com/ivorkchan)             |
| Corrine Xiang | [`TheGreatCorrine`](https://github.com/TheGreatCorrine) |
| Raine Yang    | [`Raine-Yang-UofT`](https://github.com/Raine-Yang-UofT) |
| Jifeng Qiu    | [`thqi3541`](https://github.com/thqi3541)               |

## User Stories and Use Cases

> **WARNING**: Add log out case.

### Use Case 1: User Registration

User Story: As a new user, I want to create a new account, so that I can log into the app and start trading. To sign up/register, I need to choose a userID and a password.

- Interactor: RegistrationInteractor
- Controller: RegistrationController
- Presenter: RegistrationView
- Steps:
  - The user selects "Register" and inputs the required information (View -> Controller).
  - The RegistrationController loads the registration info to the Interactor. The Interactor verifies data compliance and creates a new account (Controller -> Interactor).
  - If registration is successful, the RegistrationInteractor instructs the RegistrationView to display a success message and guide the user to log in (Controller -> Presenter).
  - If registration fails, the RegistrationController instructs the RegistrationView to display an error message (Controller -> Presenter).

### Use Case 2: User Login

User Story: As a returning user, I want to log into my account with my username, and password (credentials), so that I can resume all trading activities, balance, etc.

- Interactor: LoginInteractor
- Controller: LoginController
- Presenter: LoginView
- Steps:
  - The user inputs their login information(username and password) and submits (View -> Controller).
  - The LoginController verifies credentials and processes the login (Interactor).
  - If the login is successful, the LoginInteractor loads the user data and directs the LoginView to the trading interface (Interactor -> Presenter).
  - If the login fails, the LoginInteractor instructs the LoginView to display an error message (Interactor -> Presenter).

### Use Case 3: Execute Trades BUY

User Story: As a user, I want to buy some shares of a stock so that I can hold a long position in it. For example, I can buy 10 shares of APPL (Apple Inc.) at $5 per share.

- Interactor: ExecuteBuyInteractor
- Controller: ExecuteBuyController
- Presenter: TradeView
- Steps:
  - The user selects a ticker(symbol of the stock) and quantity to buy, and submits a buy order in the trading window (Controller -> Interactor).
  - The ExecuteBuyInteractor processes the order and updates the account balance and portfolio (Interactor).
  - The ExecuteBuyInteractor returns the trade results to the TradeView, displaying success or error messages (Interactor -> Presenter)

### Use Case 4: Execute Trades SELL

User Story: As a user, I want to sell some shares of a stock that I am currently holding, so that the position of the stock will decrease and I will get some money. For example, I have 10 shares of APPL (Apple Inc.), so I can sell 5 shares.

- Interactor: ExecuteSellInteractor
- Controller: ExecuteSellController
- Presenter: TradeView
- Steps:
  - The user selects a ticker(symbol of the stock) and quantity to buy, and submits a sell order in the trading window (Controller -> Interactor).
  - The ExecuteSellInteractor processes the order and updates the account balance and portfolio (Interactor).
  - The ExecuteSellInteractor returns the trade results to the TradeView, displaying success or error messages (Interactor -> Presenter).

### Use Case 5: Execute Trades SHORTSELL

User Story: As a user, I am not holding any shares of APPL(Apple). I want to short-sell 20 shares of Apple so that I can earn some money. Even though my current position at Apple is 0, I can 'borrow' some shares of Apple from someone else and sell the shares I borrowed.

- Interactor: ExecuteShortSellInteractor
- Controller: ExecuteShortSellController
- Presenter: TradeView
- Steps:
  - The user selects a ticker(symbol of the stock) and quantity to short-sell, and submits a sell order in the trading window (Controller -> Interactor).
  - The ExecuteShortSellInteractor processes the order, updating the account balance and portfolio (Interactor).
  - The ExecuteShortSellInteractor returns the trade results to the TradeView, displaying success or error messages (Interactor -> Presenter)

**Below are optional, and still need constructing:**

### Use Case 6: View Portfolio (Position)

- User Story:
  - As a trader, I want to access my current stock holdings and check bought price, quantity, current market price, profit/loss per share, and total profit/loss so I can make better trades.
  - (Optional) As a trader, I want to sort by column and filter my current stock holdings to display select rows and quantities so I can view certain trades.
- Implementation:
  - Interactor: UserPortfolio
  - Controller: PortfolioController
  - Presenter: PortfolioView
- Steps:
  - The user selects a page and/or rows per page for pagination-based item selection from the selection provided near the bottom (Interactor to Controller).
  - The PortfolioController filters all user trades to the select rows, quantity, and column sort (Controller).
  - The PortfolioController returns the filtered data to the PortfolioView, displaying the new view of current stock holdings and its respective information.

### Use Case 7: View Transaction History

- User Story:
  - As a trader, I want to access my transaction history and check bought and/or sold price, quantity, current market price, profit/loss per share, total profit/loss, and [Optional for sold stocks] current mp profit/loss per share, current mp total profit/loss so I can learn from past trades.
  - (Optional) As a trader, I want to sort by column and filter my current stock holdings to display select rows and quantities so I can view certain trades.
- Implementation:
  - Interactor: UserHistory
  - Controller: HistoryController
  - Presenter: HistoryView
- Steps:
  - The user selects a page and/or rows per page for pagination-based item selection from the selection provided near the bottom (Interactor to Controller).
  - The HistoryController filters all user trades to the select rows, quantity, and column sort (Controller).
  - The HistoryController returns the filtered data to the HistoryView, displaying the new view of current stock holdings and its respective information.

**Below are some other use cases we might consider implementing:**

### Use Case 8: View Ranking

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

### Use Case 9: Password Reset + Email Verification

- User Story:
  - As a trader, I want to reset my password securely using email verification, so I can reobtain access to my account if I forgot my password.
- Implementation:
  - Interactor: ReturningUser
  - Controller: PasswordResetController
  - Presenter: PasswordResetView
- Steps:
  - The user selects "ForgotPassword" and enters their registered email address (Interactor to Controller).
  - The PasswordResetController verifies if the email exists in the system and if existed, generating a one-time link that allow the user for resetting the password.
  - The PasswordResetController sends an email to the user's registered email address with the one-time link using a service like JavaMail API (optional).
  - The user clicks the one-time link and the PasswordResetController checks if the one-time link is still valid (within the validity date). If the verification is successful, the PasswordResetView is prompted to enter a new password and confirm it.
  - The PasswordResetController validates the new password (checking if contains minimum number of characters, length or uppercases requirements) and updates it in the system.
  - The PasswordResetView confirms the successful password reset and directs the user back to login screen.

### Use Case 10: Feedback / Support

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

### Use Case 11: Minimum number of trades

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

## Prototype

[View in Figma](https://www.figma.com/proto/tm5D32ALPuOvfL2lvpir9c/StockSim?page-id=0%3A1&node-id=1-3&node-type=canvas&viewport=112%2C276%2C0.21&t=eb23w81NZYAyvS8O-1&scaling=contain&content-scaling=fixed&starting-point-node-id=1%3A3)
