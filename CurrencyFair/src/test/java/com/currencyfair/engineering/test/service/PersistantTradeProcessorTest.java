package com.currencyfair.engineering.test.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.currencyfair.engineering.test.Application;
import com.currencyfair.engineering.test.camel.service.TradeProcessor;
import com.currencyfair.engineering.test.camel.service.impl.PersistantTradeProcessorImpl;
import com.currencyfair.engineering.test.domain.Trade;
import com.currencyfair.engineering.test.repository.TradeRepository;
import com.currencyfair.engineering.test.web.rest.dto.TradeDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@Transactional
public class PersistantTradeProcessorTest
{
    @Inject
    private TradeRepository tradeRepository;

    @Inject
    private PersistantTradeProcessorImpl processorImpl;

    private TradeDTO trade;

    private static final String DEFAULT_USER_ID = "134256";
    private static final String DEFAULT_CURRENCY_FROM = "EUR";
    private static final String DEFAULT_CURRENCY_TO = "GBP";
    
    private static final BigDecimal DEFAULT_AMOUNT_SELL = BigDecimal.ZERO;
    
    private static final BigDecimal DEFAULT_AMOUNT_BUY = BigDecimal.ZERO;
    
    private static final BigDecimal DEFAULT_RATE = BigDecimal.ZERO;
    
    private static final String DEFAULT_TIME_PLACED = "14-JAN-15 10:27:44";
    private static final String DEFAULT_ORIGINATING_COUNTRY = "FR";

    @Before
    public void initTest ()
    {
	trade = new TradeDTO();
	trade.setUserId(DEFAULT_USER_ID);
	trade.setCurrencyFrom(DEFAULT_CURRENCY_FROM);
	trade.setCurrencyTo(DEFAULT_CURRENCY_TO);
	trade.setAmountSell(DEFAULT_AMOUNT_SELL);
	trade.setAmountBuy(DEFAULT_AMOUNT_BUY);
	trade.setRate(DEFAULT_RATE);
	trade.setTimePlaced(DEFAULT_TIME_PLACED);
	trade.setOriginatingCountry(DEFAULT_ORIGINATING_COUNTRY);
    }

    @Test
    @Transactional
    public void testProcess ()
    {
	// Validate the database is empty
	assertThat(tradeRepository.findAll()).hasSize(0);
	try
	{
	    processorImpl.process(trade);
	    List<Trade> trades = tradeRepository.findAll();
	    assertThat(trades).hasSize(1);

	    Trade testTrade = trades.iterator().next();
	    assertThat(testTrade.getUserId()).isEqualTo(DEFAULT_USER_ID);
	    assertThat(testTrade.getCurrencyFrom()).isEqualTo(
		    DEFAULT_CURRENCY_FROM);
	    assertThat(testTrade.getCurrencyTo())
		    .isEqualTo(DEFAULT_CURRENCY_TO);
	    assertThat(testTrade.getAmountSell())
		    .isEqualTo(DEFAULT_AMOUNT_SELL);
	    assertThat(testTrade.getAmountBuy()).isEqualTo(DEFAULT_AMOUNT_BUY);
	    assertThat(testTrade.getRate()).isEqualTo(DEFAULT_RATE);
	    //assertThat(testTrade.getTimePlaced().toDateTime(DateTimeZone.UTC)).isEqualTo(DEFAULT_TIME_PLACED);
	    assertThat(testTrade.getOriginatingCountry()).isEqualTo(
		    DEFAULT_ORIGINATING_COUNTRY);
	}
	catch (Exception e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}
