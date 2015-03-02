package com.currencyfair.engineering.test.camel.service.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import com.currencyfair.engineering.test.camel.service.TradeProcessor;
import com.currencyfair.engineering.test.web.rest.TradeResource;
import com.currencyfair.engineering.test.web.rest.dto.TradeDTO;
import com.currencyfair.engineering.test.web.websocket.dto.CurrencyPairDTO;


@Service(value="analyseTradeProcessor")
public class AnalyseTradeProcessorImpl implements TradeProcessor
{
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    SimpMessageSendingOperations messagingTemplate;
    
    
    @Override
    public void process ( TradeDTO tradeDTO ) throws Exception
    {
	log.debug("analysis got message.");
	
	CurrencyPairDTO currencyPairDTO = new CurrencyPairDTO();
	currencyPairDTO.setPairName(tradeDTO.getCurrencyFrom() + "-" + tradeDTO.getCurrencyTo() );
	currencyPairDTO.setTransactionVolume(tradeDTO.getAmountBuy());
	
	messagingTemplate.convertAndSend("/topic/currencypair", currencyPairDTO);

    }

}
