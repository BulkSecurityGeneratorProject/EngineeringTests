package com.currencyfair.engineering.test.camel.service;

import com.currencyfair.engineering.test.web.rest.dto.TradeDTO;

public interface TradeProcessor
{

    public void process(TradeDTO tradeDTO) throws Exception;
}
