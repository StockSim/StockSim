package entity;

import java.util.Date;

/**
 * A class representing a transaction
 */
public record Transaction(
        Date timestamp,
        String ticker,
        int quantity,
        double price,
        String type
) {
}
