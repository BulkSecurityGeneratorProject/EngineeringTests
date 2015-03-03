# EngineeringTests
## Thoughts behind Choosing Technologies
for rapid development, I thought, let's give a try with JHipster. <br>
to handle huge volumen of Post call on Message Consumption endpoint, I picked camel routing. Mainly because it has huge support for defining endpoints. and very easily changeable. Right now, the implementation in TradeResource is writing a file with data in a directory. It can be easily taken to a JMS system and the listenrs will read jms queue to process incoming request.<br>


# Running the app
CurrencyFair/README.md contains some details.
