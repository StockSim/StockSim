# StockSim

- [StockSim](#stocksim)
  - [Members](#members)
  - [User Stories and Use Cases](#user-stories-and-use-cases)
    - [Use Case 1: User Registration](#use-case-1-user-registration)
    - [Use Case 2: User Login](#use-case-2-user-login)
    - [Use Case 3: Execute Trades](#use-case-3-execute-trades)
    - [Use Case 4: View Portfolio (Position)](#use-case-4-view-portfolio-position)
    - [Use Case 5: View Transaction History](#use-case-5-view-transaction-history)
    - [Use Case 6: View Ranking](#use-case-6-view-ranking)
    - [Use Case 7: Password Reset + Email Verification](#use-case-7-password-reset--email-verification)
    - [Use Case 8: Feedback / Support](#use-case-8-feedback--support)
    - [Use Case 9: Minimum number of trades](#use-case-9-minimum-number-of-trades)
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

### Use Case 1: User Registration

- User Story: new user, sign up/register, username, password, confirm password, set initial balance, initialize everything(portfolio, etc.)
  - Interactor: NewUser
  - Controller: RegistrationController
  - Presenter: RegistrationView
- Steps:
  - The user selects "Register" and inputs required information (Interactor to Controller).
  - The RegistrationController processes the registration info, verifies data compliance, and creates a new account (Controller).
  - If registration is successful, the RegistrationController instructs the RegistrationView to display a success message and guide the user to log in (Controller to Presenter).
  - If registration fails, the RegistrationController instructs the RegistrationView to display an error message (Controller to Presenter).

### Use Case 2: User Login

- User Story: returning user, log in with username, password [credentials] to access the account, resume trading activities/balance, etc.
  - Interactor: ReturningUser
  - Controller: LoginController
  - Presenter: LoginView
- Steps:
  - The user inputs their login information and submits (Interactor to Controller).
  - The LoginController verifies credentials and processes the login (Controller).
  - If the login is successful, the LoginController loads the user data and directs the LoginView to the trading interface (Controller to Presenter).
  - If the login fails, the LoginController instructs the LoginView to display an error message (Controller to Presenter).

### Use Case 3: Execute Trades

- User Story: Buy/Sell
  - Interactor: CurrentUser
  - Controller: TradeController
  - Presenter: TradeView
- Steps:
  - The user selects a stock, quantity, and submits a buy or sell order in the trading window (Interactor to Controller).
  - The TradeController processes the order, updating account balance and portfolio (Controller).
  - The TradeController returns the trade results to the TradeView, displaying success or error messages (Controller to Presenter).

### Use Case 4: View Portfolio (Position)

- User Story:
  - As a trader, I want to access my current stock holdings and check bought price, quantity, current market price, profit/loss per share, and total profit/loss so I can make better trades.
  - (Optional) As a trader, I want to sort by column and filter my current stock holdings to display select rows and quantity so I can view certain trades.
- Implementation:
  - Interactor: UserPortfolio
  - Controller: PortfolioController
  - Presenter: PortfolioView
- Steps:
  - The user selects a page and/or rows per page for pagination-based item selection from the selection provided near the bottom (Interactor to Controller).
  - The PortfolioController filters all user trades to the select rows, quantity, and column sort (Controller).
  - The PortfolioController returns the filtered data to the PortfolioView, displaying the new view of current stock holdings and its respective information.

### Use Case 5: View Transaction History

- User Story:
  - As a trader, I want to access my transaction history and check bought and/or sold price, quantity, current market price, profit/loss per share, total profit/loss, and [Optional for sold stocks] current mp profit/loss per share, current mp total profit/loss so I can learn from past trades.
  - (Optional) As a trader, I want to sort by column and filter my current stock holdings to display select rows and quantity so I can view certain trades.
- Implementation:
  - Interactor: UserHistory
  - Controller: HistoryController
  - Presenter: HistoryView
- Steps:
  - The user selects a page and/or rows per page for pagination-based item selection from the selection provided near the bottom (Interactor to Controller).
  - The HistoryController filters all user trades to the select rows, quantity, and column sort (Controller).
  - The HistoryController returns the filtered data to the HistoryView, displaying the new view of current stock holdings and its respective information.

Below are some other use cases we might consider implementing:

### Use Case 8: View Ranking

- User Story: 
  - As a trader, I want to view my rank compared to other traders based on portfolio performance (portfolio values) so I can assess my relative performance and strive to improve. 
- Implementation:
  - Interactor: CurrentUser
  - Controller: RankingController
  - Presenter: RankingView
- Steps:
  - The user selects a page and/or rows per page for pagination-based item selection from the selection provided near the bottom (Interator to Controller).
  - The RankingController retreives the current user's current rank by comparing their portfolio's total values with other user and formats all user's assets and portifolio's total values by ranking them. 
  - The RankingController returns the ranking data to the RankingView, and provide optional details, like viewing the top 3 or top 5 users by listing them in descending order.

### Use Case 9: Password Reset + Email Verification

- User Story:
  - As a trader, I want to reset my password securely using email verification, so I can reobtain access to my account if I forgot my password. 
- Implementation:
  - Interator: ReturningUser
  - Controller: PasswordResetController
  - Presenter: PasswordResetView
- Steps:
  - The user selects "ForgotPassword" and enters their registered email address (Interactor to Controller).
  - The PasswordReserController verifies if the email exists in the system and if existed, generating a one-time link that allow the user for resetting the password.
  - The PasswordResetController sends an email to the user's registered email address with the one-time link using a service like JavaMail API (optional).
  - The user clicks the one-time link and the PasswordResetController checks if the one-time link is still valid (within the validity date). If the verification is successful, the PasswordResetView is prompted to enter a new password and confirm it. 
  - The PasswordResetController validates the new password (checking if contains minimum number of characters, length or uppercases requirements) and updates it in the system. 
  - The PasswordResetView confirms the successful password reset and directs the user back to login screen. 

### Use Case 10: Feedback / Support

- User Story:
  - As a user, I want to report technical issues or provide feedback, so I can communicate with the support team if I ecounter issues or have any suggestions. 
- Implementation:
  - Interactor: CurrentUser
  - Controller: FeedbackController 
  - Presenter: FeedbackView
- Steps:
   - The user navigates to the "Feedback/Support" section in the application.
   - The user submits a report ticket or feedback message, which can include details like issue types, description, and optional attachments (Iteractor to Controller)
   - FeedbackController validates the user's meesage content (checks for empty fields or inappropriate content). If valid, FeedbackController saves the feedback or support request in the database for tracking and management and sends an acknowledgment email to the user.
   - FeedbackView confirms the successful submission and provides the user with a ticket ID for future reference if needed.

### Use Case 11: Minimum number of trades

- User Story:
   - As a trader, I want to be aware of my total number of trades to ensure I meet the minimum trading requirements for account benefits or eligibility for promotions.
- Implementation:
  - Interactor: CurrentUser
  - Controller: TradeRequirementController
  - Presenter: TradeRequirementView
- Steps:
  - The trader selects "View Trade Activity" to view their trade count and checks their progress toward any trade-related requirements.
  - The TradeRequirementController retreives the user's trade history from the database and counts the current total number of trades.
  - The TradeRequirementController checks this total againist the minimum threshold, or other requirements, such as qualifying for promotions or eligibility)
  - The TradeRequirementController sends the data to the TradeRequirementView, and display the user's trade count, progress toward the minimum trade requirement and any relevant messages (eg: "You need 5 more trades to qualify").
## Prototype

[View in Figma](https://www.figma.com/proto/tm5D32ALPuOvfL2lvpir9c/StockSim?page-id=0%3A1&node-id=1-3&node-type=canvas&viewport=112%2C276%2C0.21&t=eb23w81NZYAyvS8O-1&scaling=contain&content-scaling=fixed&starting-point-node-id=1%3A3)
