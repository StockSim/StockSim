# Trading simulation app - helps users practice before making real trades

> **WARNING:** This documentation version is deprecated.
>
> Please review the `README.md` file in root directory for latest updates.

Within our project documentation, the features in black are the basic functionalities we need to implement first. These are our priorities and must be fully functional for our initial version. The features in gray are advanced options that we can consider adding later. These advanced features will enhance our app's functionality and user experience but are not critical for the initial launch. The first three pages explain briefly how the app works. Starting on page 4, we will give detailed descriptions of the mechanisms for each part.

## Users' information

Users should **register** (new users) or **log in**.

Once they have an account, new users are given an initial **balance** of $100k, while the balance for returning users will be loaded based on the last recorded state from their previous session.

### Registration

New users will create an account by providing a Trader ID, a password (not necessary), and an email. They will also select a difficulty level for their trading simulation (Beginner, Intermediate, Advanced), which will determine the initial balance. An email verification process can be implemented to validate the account.

### Login

Returning users can log in using their trader ID/email and password. If they forget the password, they can reset it.

### Balances

Initial Balance: Depending on the difficulty level chosen during registration, the initial balance will vary. For instance:

- Beginner: $50,000
- Intermediate: $100,000
- Advanced: $150,000

For returning users, their balance will be loaded based on the last recorded state from their previous session. The balance has to be greater than 0. If the balance = initial money - loss <0, then the game is over. We will delete all the user information and the user has to create a new account. We'll explain in detail later.

## Making Trades

**Order Entry(Buy/Sell)**: When users click on 'Order Entry,' a trading window will appear.

They can place **market orders** by selecting a stock (**Ticker**) from the three stocks provided, entering the **quantity**, and choosing to 'Buy' or 'Sell' to execute the trade.

### Buy/Sell

Buy(`+`): This is when you purchase the shares of a stock. When the stock price goes up, you can sell it later at a higher price.

Sell(`-`): This usually happens when you already have some shares of the stock. Normally, the maximum quantity you can sell can not exceed the current quantity you are holding.

However, there is a special case of selling, which is called short-selling. Even if you don't own any shares of a stock, you can temporarily 'borrow' some shares from others and sell them. Later, you have to buy the shares you borrowed back. If we don't allow short-selling, we need to make the position of a stock always >= 0.(Buy >= Sell)

There are typically two order types of orders.

- Market orders: Users can place market orders by selecting a stock (Ticker), entering the quantity, and choosing 'Buy' or 'Sell'. Orders will be executed immediately at the current market price, so the users can not choose the price. (That's why it looks darker.)
- ~~Limit Orders: Users can specify the price at which they are willing to buy or sell a stock. The order will only execute if the market price meets or exceeds this limit.~~

For the sake of simplicity in our initial implementation, we'll only be using market orders. This means we won't need to handle the logic of checking whether trades are successful based on price and volume conditions. Instead, all trades will execute immediately at the current market price, ensuring that every transaction is completed.

### Ticker

To simplify our initial implementation, we'll limit the range of stocks available for trading to a few well-known companies, such as Apple, Amazon, and Tesla. We might also limit the number of different stocks each user can hold. (Maybe 3)

We might enable users to enter any stock ticker. This feature would involve integrating a search and validation mechanism to ensure the ticker is valid and available in the market data we have access to with our API key.

## Checking current positions

### Portfolio

When a user clicks on 'Portfolio', a window will appear showing all the stocks they are currently holding, which helps traders in decision-making. The window includes:

- Buy/Sell Price: The price at which the user bought or sold the stock.
- Current Market Price: The latest market price of the stock.
- Quantity: The number of shares the user owns.
- Profit/Loss per Share: The gain or loss per share, calculated as the difference between the current market price and the buy/sell price.
- Total Profit/Loss: The overall gain or loss, which is the profit/loss per share multiplied by the quantity of shares owned.

## Balance check

Continuously monitor the user's balance after every transaction (buy or sell). Implement a function that checks the balance every time a transaction is processed.

### Game Over Condition

If a balance check reveals that the balance has fallen below zero, trigger a game over condition. This condition could be set by a simple conditional statement in the transaction processing logic: `if (currentBalance < 0) { triggerGameOver(); }`.

### Game Over Functionality

Define a function `triggerGameOver()` that handles the termination of the account. This function could log the user out, display a message indicating that the game is over due to insufficient funds, and optionally delete or deactivate the user's account, depending on your requirements.

### User Feedback

Provide immediate feedback to the user through a pop-up message or a screen redirect explaining why the game ended.

### Database Update

Update the database to reflect the account's status as terminated, ensuring that the user cannot log in unless they start a new game.
