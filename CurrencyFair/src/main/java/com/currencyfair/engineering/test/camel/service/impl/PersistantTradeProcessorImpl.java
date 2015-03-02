package com.currencyfair.engineering.test.camel.service.impl;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.currencyfair.engineering.test.camel.service.TradeProcessor;
import com.currencyfair.engineering.test.domain.Trade;
import com.currencyfair.engineering.test.repository.TradeRepository;
import com.currencyfair.engineering.test.web.rest.TradeResource;
import com.currencyfair.engineering.test.web.rest.dto.TradeDTO;

@Service(value="persistantTradeProcessor")
public class PersistantTradeProcessorImpl implements TradeProcessor
{
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Inject
    private TradeRepository tradeRepository; 
    
    DateTimeFormatter format = DateTimeFormat.forPattern("dd-MMM-yy HH:mm:ss");

    @Override
    public void process ( TradeDTO tradeDTO ) throws Exception
    {
	//log.debug("persistantTradeProcessor got message");
	
	Trade trade = new Trade();
	trade.setUserId(tradeDTO.getUserId());
	trade.setAmountBuy(tradeDTO.getAmountBuy());
	trade.setAmountSell(tradeDTO.getAmountSell());
	trade.setCurrencyFrom(tradeDTO.getCurrencyFrom());
	trade.setCurrencyTo(tradeDTO.getCurrencyTo());
	trade.setRate(tradeDTO.getRate());
	trade.setOriginatingCountry(tradeDTO.getOriginatingCountry());
	 
	try
	{
	    DateTime dateTime = format.parseDateTime(tradeDTO.getTimePlaced());
	    trade.setTimePlaced(dateTime);	    
	}
	catch (IllegalArgumentException e)
	{
	    log.error("error",e);
	    throw new Exception(e);
	}
	
	
	tradeRepository.save(trade);

    }

}
