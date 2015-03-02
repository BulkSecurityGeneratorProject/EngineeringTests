package com.currencyfair.engineering.test.camel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.currencyfair.engineering.test.camel.service.TradeProcessor;
import com.currencyfair.engineering.test.web.rest.TradeResource;
import com.currencyfair.engineering.test.web.rest.dto.TradeDTO;


@Service(value="analyseTradeProcessor")
public class AnalyseTradeProcessorImpl implements TradeProcessor
{
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void process ( TradeDTO tradeDTO ) throws Exception
    {
	log.debug("analysis got message.");

    }

}
