package entity;

/**
 * A class representing a stock owned by a user
 */
public class UserStock {

    private final Stock stock;
    private double cost;
    private int quantity;

    /**
     * Constructor for UserStock class
     *
     * @param stock:    the stock object
     * @param cost:     the average cost of the stock
     * @param quantity: the quantity of the stock owned by user
     */
    public UserStock(Stock stock, double cost, int quantity) {
        this.stock = stock;
        this.cost = cost;
        this.quantity = quantity;
    }

    public Stock getStock() {
        return stock;
    }

    public double getCost() {
        return cost;
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     * Calculate the total cost spent on this stock
     *
     * @return the total cost spent on this stock
     */
    public double getPurchasedTotalCost() {
        return cost * quantity;
    }

    /**
     * Calculate the total market value of this stock in the stock market
     *
     * @return the current market value of this stock in the stock market
     */
    public double getCurrentMarketValue() {
        return stock.getPrice() * quantity;
    }

    /**
     * Update the average cost and quantity of the stock
     *
     * @param price:    the price of the stock
     * @param quantity: the quantity of the stock
     */
    public void updateUserStock(double price, int quantity) {
        this.cost = (this.cost * this.quantity + price * quantity) / (quantity + this.quantity);
        this.quantity += quantity;
    }
}