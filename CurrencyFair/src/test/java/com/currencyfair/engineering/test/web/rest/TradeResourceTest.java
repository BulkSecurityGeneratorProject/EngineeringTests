package com.currencyfair.engineering.test.web.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.math.BigDecimal;
import java.util.List;

import com.currencyfair.engineering.test.Application;
import com.currencyfair.engineering.test.domain.Trade;
import com.currencyfair.engineering.test.repository.TradeRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TradeResource REST controller.
 *
 * @see TradeResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class TradeResourceTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    private static final String DEFAULT_USER_ID = "SAMPLE_TEXT";
    private static final String UPDATED_USER_ID = "UPDATED_TEXT";
    private static final String DEFAULT_CURRENCY_FROM = "SAMPLE_TEXT";
    private static final String UPDATED_CURRENCY_FROM = "UPDATED_TEXT";
    private static final String DEFAULT_CURRENCY_TO = "SAMPLE_TEXT";
    private static final String UPDATED_CURRENCY_TO = "UPDATED_TEXT";

    private static final BigDecimal DEFAULT_AMOUNT_SELL = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_AMOUNT_SELL = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_AMOUNT_BUY = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_AMOUNT_BUY = BigDecimal.ONE;

    private static final BigDecimal DEFAULT_RATE = BigDecimal.ZERO;
    private static final BigDecimal UPDATED_RATE = BigDecimal.ONE;

    private static final DateTime DEFAULT_TIME_PLACED = new DateTime(0L, DateTimeZone.UTC);
    private static final DateTime UPDATED_TIME_PLACED = new DateTime(DateTimeZone.UTC).withMillisOfSecond(0);
    private static final String DEFAULT_TIME_PLACED_STR = dateTimeFormatter.print(DEFAULT_TIME_PLACED);
    private static final String DEFAULT_ORIGINATING_COUNTRY = "SAMPLE_TEXT";
    private static final String UPDATED_ORIGINATING_COUNTRY = "UPDATED_TEXT";

    @Inject
    private TradeRepository tradeRepository;

    private MockMvc restTradeMockMvc;

    private Trade trade;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TradeResource tradeResource = new TradeResource();
        ReflectionTestUtils.setField(tradeResource, "tradeRepository", tradeRepository);
        this.restTradeMockMvc = MockMvcBuilders.standaloneSetup(tradeResource).build();
    }

    @Before
    public void initTest() {
        trade = new Trade();
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
    public void createTrade() throws Exception {
        // Validate the database is empty
        assertThat(tradeRepository.findAll()).hasSize(0);

        // Create the Trade
        restTradeMockMvc.perform(post("/api/trades")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(trade)))
                .andExpect(status().isOk());

        // Validate the Trade in the database
        List<Trade> trades = tradeRepository.findAll();
        assertThat(trades).hasSize(1);
        Trade testTrade = trades.iterator().next();
        assertThat(testTrade.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testTrade.getCurrencyFrom()).isEqualTo(DEFAULT_CURRENCY_FROM);
        assertThat(testTrade.getCurrencyTo()).isEqualTo(DEFAULT_CURRENCY_TO);
        assertThat(testTrade.getAmountSell()).isEqualTo(DEFAULT_AMOUNT_SELL);
        assertThat(testTrade.getAmountBuy()).isEqualTo(DEFAULT_AMOUNT_BUY);
        assertThat(testTrade.getRate()).isEqualTo(DEFAULT_RATE);
        assertThat(testTrade.getTimePlaced().toDateTime(DateTimeZone.UTC)).isEqualTo(DEFAULT_TIME_PLACED);
        assertThat(testTrade.getOriginatingCountry()).isEqualTo(DEFAULT_ORIGINATING_COUNTRY);
    }

    @Test
    @Transactional
    public void getAllTrades() throws Exception {
        // Initialize the database
        tradeRepository.saveAndFlush(trade);

        // Get all the trades
        restTradeMockMvc.perform(get("/api/trades"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(trade.getId().intValue()))
                .andExpect(jsonPath("$.[0].userId").value(DEFAULT_USER_ID.toString()))
                .andExpect(jsonPath("$.[0].currencyFrom").value(DEFAULT_CURRENCY_FROM.toString()))
                .andExpect(jsonPath("$.[0].currencyTo").value(DEFAULT_CURRENCY_TO.toString()))
                .andExpect(jsonPath("$.[0].amountSell").value(DEFAULT_AMOUNT_SELL.intValue()))
                .andExpect(jsonPath("$.[0].amountBuy").value(DEFAULT_AMOUNT_BUY.intValue()))
                .andExpect(jsonPath("$.[0].rate").value(DEFAULT_RATE.intValue()))
                .andExpect(jsonPath("$.[0].timePlaced").value(DEFAULT_TIME_PLACED_STR))
                .andExpect(jsonPath("$.[0].originatingCountry").value(DEFAULT_ORIGINATING_COUNTRY.toString()));
    }

    @Test
    @Transactional
    public void getTrade() throws Exception {
        // Initialize the database
        tradeRepository.saveAndFlush(trade);

        // Get the trade
        restTradeMockMvc.perform(get("/api/trades/{id}", trade.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(trade.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.toString()))
            .andExpect(jsonPath("$.currencyFrom").value(DEFAULT_CURRENCY_FROM.toString()))
            .andExpect(jsonPath("$.currencyTo").value(DEFAULT_CURRENCY_TO.toString()))
            .andExpect(jsonPath("$.amountSell").value(DEFAULT_AMOUNT_SELL.intValue()))
            .andExpect(jsonPath("$.amountBuy").value(DEFAULT_AMOUNT_BUY.intValue()))
            .andExpect(jsonPath("$.rate").value(DEFAULT_RATE.intValue()))
            .andExpect(jsonPath("$.timePlaced").value(DEFAULT_TIME_PLACED_STR))
            .andExpect(jsonPath("$.originatingCountry").value(DEFAULT_ORIGINATING_COUNTRY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTrade() throws Exception {
        // Get the trade
        restTradeMockMvc.perform(get("/api/trades/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    //@Test
    @Transactional
    public void updateTrade() throws Exception {
        // Initialize the database
        tradeRepository.saveAndFlush(trade);

        // Update the trade
        trade.setUserId(UPDATED_USER_ID);
        trade.setCurrencyFrom(UPDATED_CURRENCY_FROM);
        trade.setCurrencyTo(UPDATED_CURRENCY_TO);
        trade.setAmountSell(UPDATED_AMOUNT_SELL);
        trade.setAmountBuy(UPDATED_AMOUNT_BUY);
        trade.setRate(UPDATED_RATE);
        trade.setTimePlaced(UPDATED_TIME_PLACED);
        trade.setOriginatingCountry(UPDATED_ORIGINATING_COUNTRY);
        restTradeMockMvc.perform(post("/api/trades")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(trade)))
                .andExpect(status().isOk());

        // Validate the Trade in the database
        List<Trade> trades = tradeRepository.findAll();
        assertThat(trades).hasSize(1);
        Trade testTrade = trades.iterator().next();
        assertThat(testTrade.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testTrade.getCurrencyFrom()).isEqualTo(UPDATED_CURRENCY_FROM);
        assertThat(testTrade.getCurrencyTo()).isEqualTo(UPDATED_CURRENCY_TO);
        assertThat(testTrade.getAmountSell()).isEqualTo(UPDATED_AMOUNT_SELL);
        assertThat(testTrade.getAmountBuy()).isEqualTo(UPDATED_AMOUNT_BUY);
        assertThat(testTrade.getRate()).isEqualTo(UPDATED_RATE);
        assertThat(testTrade.getTimePlaced().toDateTime(DateTimeZone.UTC)).isEqualTo(UPDATED_TIME_PLACED);
        assertThat(testTrade.getOriginatingCountry()).isEqualTo(UPDATED_ORIGINATING_COUNTRY);
    }

    @Test
    @Transactional
    public void deleteTrade() throws Exception {
        // Initialize the database
        tradeRepository.saveAndFlush(trade);

        // Get the trade
        restTradeMockMvc.perform(delete("/api/trades/{id}", trade.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Trade> trades = tradeRepository.findAll();
        assertThat(trades).hasSize(0);
    }
}
