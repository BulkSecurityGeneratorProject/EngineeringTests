package com.currencyfair.engineering.test.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import com.currencyfair.engineering.test.web.rest.dto.TradeDTO;

@Component
public class CurrencyFairRouter extends RouteBuilder
{

    @Override
    public void configure () throws Exception
    {
	from("seda:trade")
	.marshal()
	.json(JsonLibrary.Jackson, TradeDTO.class)
	.to("file:data/trade")
	;
	
	from("file:data/trade?idempotent=true&delete=true")
	//.errorHandler(deadLetterChannel("file:data/trade/error"))
	.unmarshal()
	.json(JsonLibrary.Jackson , TradeDTO.class)
	.multicast()
	.to("persistantTradeProcessor","analyseTradeProcessor","realTimeTradeProcessor")	
	;
	
	

    }

}
