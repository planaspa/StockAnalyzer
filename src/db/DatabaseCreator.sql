/* Values in the stock market */
CREATE TABLE Values(
ticker varchar (20),
region varchar (100) NOT NULL,
PRIMARY KEY (ticker)
);

/* Prices per day of a ticker */
CREATE TABLE Prices(
ticker varchar (20),
day int,
minimum float NOT NULL,
maximum float NOT NULL,
open float NOT NULL,
close float NOT NULL,
volume int,
FOREIGN KEY (ticker) REFERENCES Values(ticker),
PRIMARY KEY (ticker,day)
);

/* Relation between Sectors and tickers, e.g. Banking and SAN.MC */
CREATE TABLE Sectors(
ticker varchar (20),
sector varchar (100),
FOREIGN KEY (ticker) REFERENCES Values(ticker),
PRIMARY KEY (ticker,sector)
);

/* Relation between companies and indexes */
CREATE TABLE Indexes(
tickerCompany varchar (20),
tickerIndex varchar (20),
FOREIGN KEY (tickerCompany) REFERENCES Values(ticker),
FOREIGN KEY (tickerIndex) REFERENCES Values(ticker),
PRIMARY KEY (tickerCompany,tickerIndex)
);