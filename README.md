*StockzProject by Nicolai Bah, Jonas Grøn & Alexander Tholle*

**User-Guide**

Here is a guide for new users on what the Stockz app can do and how to use it. The guide is divided into the three tabs that are present in the app, which represent the three main functionalities of the app.

**Portfolio:**

Here you can see your own personal portfolio, including the prices of the stocks per share when you added them, the amount and their total value. At the top of the screen you can find your portfolio overall stats such as its total value and the overall stats (whether it has increased or decreased in value).

Pressing on any of your stocks in the list will take you to a detailed view of that stock. Here you can see the current price per share, todays rise (in percentage) and other important information such as todays high, low and opening price.

**Market:**

Here you can search the market for any stock you would like to inspect and/or add to your personal portfolio. Enter the short name for the stock (i.e. TSLA for Tesla) and press search. When searching you will be presented with the current price per share and a field where you can enter an amount of the stock you wish to add to your own portfolio.

**Inspiration:**

Here you can get some inspiration of what stocks you might want to add to your own portfolio. You will be presented with a randomly selected themed list of stocks (fx. Tech Stocks etc.). You can always press the button to get a new random list.


**Key functionalities**

The app features the following key functionalities:
- *API Calls to get stocks + information*
- *External network library Retrofit to ease the use of APIs*
- *Multi-threading and Coroutines to ensure a positive user experience*
- *SQLite database to save your personal portfolio locally*


**Important Information**

As the app is using free APIs, there are some limitations. You can only make 5 API calls within 30-60 seconds of eachother, meaning if you suddenly stop seeing stock information, you have to wait a minute and try again. This applies for all three core functionalities (portfolio, market and inspiration). If you wait several minutes and it still does not provide stock information / inspiration list, it is most likely because the API key has expired. Please message us directly, so we can create a new key for you.
