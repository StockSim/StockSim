# Alpha Vantage API Documentation

## Introduction

We utilize the Alpha Vantage API. Detailed documentation is available [here](https://www.alphavantage.co/documentation/).

### Response Data Type

The Alpha Vantage API can return responses in either `JSON` or `CSV` format.

Specify your preferred format by setting the `datatype` parameter in your API request to either `json` or `csv`.

### Language-specific guides: Java wrapper

Alpha Vantage does not directly support Java. We use this wrapper on GitHub: [alphavantage-java](https://github.com/crazzyghost/alphavantage-java).

Note: In issues, a contributor named `Creeeeger` added a search section.

## API Usage Examples

### Time Series Intraday

**Parameters:**

- `function`
- `symbol`
- `interval`
- `apikey`

**Sample request:**

```txt
https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=5min&apikey=demo
```

**Sample response (JSON output):**

```json
{
  "Meta Data": {
    "Information": "Intraday (5min) open, high, low, close prices and volume",
    "Symbol": "IBM",
    "Last Refreshed": "2023-11-20 16:00:00",
    "Interval": "5min",
    "Output Size": "Compact",
    "Time Zone": "US/Eastern"
  },
  "Time Series (5min)": {
    "2023-11-20 16:00:00": {
      "1. open": "125.6700",
      "2. high": "125.8850",
      "3. low": "125.5700",
      "4. close": "125.8800",
      "5. volume": "300158"
    }
  }
}
```

### Real-Time Bulk Quotes

**Parameters:**

- `function`
- `symbol`
- `apikey`

**Sample request:**

```txt
https://www.alphavantage.co/query?function=REALTIME_BULK_QUOTES&symbol=MSFT,AAPL,IBM&apikey=demo
```

**Sample response (JSON output):**

```json
{
  "Stock Quotes": [
    {
      "symbol": "MSFT",
      "price": "280.00",
      "volume": "2045683"
    },
    {
      "symbol": "AAPL",
      "price": "150.00",
      "volume": "1584231"
    },
    {
      "symbol": "IBM",
      "price": "130.00",
      "volume": "305689"
    }
  ]
}
```
