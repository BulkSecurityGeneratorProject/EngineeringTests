README for CurrencyFairTest
==========================
# How to run 
To run the application in dev mode with embedded tomcat server, simply type <b>mvn spring-boot:run</b><br>
To run the application in prod mode, <br>
mvn -Pprod package<br>
cd target/<br>
java -jar jhipster-0.0.1-SNAPSHOT.war --spring.profiles.active=prod <br>
or deploy the war file in a tomcat server. 

# Feature List
## Message Consumption
  Expose an endpoint which can consume trade messages. The url is : http://{host}:{port}/api/trades.
  Trade messages will be POST’d to this endpoint and will take the JSON form of:

{"userId": "134256", "currencyFrom": "EUR", "currencyTo": "GBP", "amountSell": 1000, "amountBuy": 747.10, "rate": 0.7471, "timePlaced" : "14-JAN-15 10:27:44", "originatingCountry" : "FR"}

## Message Processor
Process messages received via the message consumption endpoint.
The interface is com.currencyfair.engineering.test.camel.service.TradeProcessor

Implementaions are under the package com.currencyfair.engineering.test.camel.service.impl.
PersistantTradeProcessorImpl -> persists the Trade message in database.
AnalyseTradeProcessorImpl -> sends a CurrencyPairDTO to front end via websocket. 
RealTimeTradeProcessorImpl -> sends the Trade Message to front end via websocket.

To add more processor, simple implement the TradeProcessor interface, and add the service name in com.currencyfair.engineering.test.camel.CurrencyFairRouter in appropriate position. (currrent line number 27).

## Message Frontend
Developed with AngularJS and HTML5. 

## Security
The web application is fully secured. The end point for Message Consumption is open for all to submit a post request. Assuming that, some real application will post data to the endpoint, VPN tunnel is an option to secure this end point.

## Documentation 
The api's has been documented with Swagger. To access this page, login in the webapp with admin/admin and browse Administration->Api from the menu.

# Known Issues
###errorHandler in multicasting with Camel has issue. Need to watch this.
###joda DateTime needs to fix for timePlaced in TradeDTO. implemented with String.
###currencyFrom, currencyTo and originatingCountry are simple String, maybe a lookup table for validation is better.


