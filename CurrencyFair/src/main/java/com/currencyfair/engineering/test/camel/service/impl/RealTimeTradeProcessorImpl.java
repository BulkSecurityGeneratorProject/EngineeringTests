package com.currencyfair.engineering.test.camel.service.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import com.currencyfair.engineering.test.camel.service.TradeProcessor;
import com.currencyfair.engineering.test.web.rest.dto.TradeDTO;

@Service(value="realTimeTradeProcessor")
public class RealTimeTradeProcessorImpl implements TradeProcessor
{
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    SimpMessageSendingOperations messagingTemplate;

    @Override
    public void process ( TradeDTO tradeDTO ) throws Exception
    {
	messagingTemplate.convertAndSend("/topic/realtimetrade", tradeDTO);
	
    }

}
