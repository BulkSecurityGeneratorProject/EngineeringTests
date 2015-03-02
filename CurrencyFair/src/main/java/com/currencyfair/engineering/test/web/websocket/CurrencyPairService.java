package com.currencyfair.engineering.test.web.websocket;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.inject.Inject;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import com.currencyfair.engineering.test.web.rest.dto.TradeDTO;
import com.currencyfair.engineering.test.web.websocket.dto.CurrencyPairDTO;

@Controller
public class CurrencyPairService
{    
    private List<CurrencyPairDTO> pairs = new CopyOnWriteArrayList<CurrencyPairDTO>(); 
    
    @MessageMapping("/websocket/currencypair")
    @SendTo("/topic/currencypair")
    public CurrencyPairDTO sendCurrencyPair(TradeDTO tradeDTO)
    {
	CurrencyPairDTO currencyPairDTO = new CurrencyPairDTO();
	currencyPairDTO.setPairName(tradeDTO.getCurrencyFrom() + "-" + tradeDTO.getCurrencyTo());
	currencyPairDTO.setTransactionVolume(tradeDTO.getAmountBuy());
	
	
	return new CurrencyPairDTO();
	
    }

}
