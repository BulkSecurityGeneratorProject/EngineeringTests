package com.currencyfair.engineering.test.web.rest.dto;

import java.math.BigDecimal;

import org.joda.time.DateTime;

public class TradeDTO
{
    private String userId;
    private String currencyFrom;
    private String currencyTo;
    private BigDecimal amountSell;
    private BigDecimal amountBuy;
    private BigDecimal rate;
    private String timePlaced;
    private String originatingCountry;
    
    
    public String getUserId ()
    {
        return userId;
    }
    public void setUserId ( String userId )
    {
        this.userId = userId;
    }
    public String getCurrencyFrom ()
    {
        return currencyFrom;
    }
    public void setCurrencyFrom ( String currencyFrom )
    {
        this.currencyFrom = currencyFrom;
    }
    public String getCurrencyTo ()
    {
        return currencyTo;
    }
    public void setCurrencyTo ( String currencyTo )
    {
        this.currencyTo = currencyTo;
    }
    public BigDecimal getAmountSell ()
    {
        return amountSell;
    }
    public void setAmountSell ( BigDecimal amountSell )
    {
        this.amountSell = amountSell;
    }
    public BigDecimal getAmountBuy ()
    {
        return amountBuy;
    }
    public void setAmountBuy ( BigDecimal amountBuy )
    {
        this.amountBuy = amountBuy;
    }
    public BigDecimal getRate ()
    {
        return rate;
    }
    public void setRate ( BigDecimal rate )
    {
        this.rate = rate;
    }
    public String getTimePlaced ()
    {
        return timePlaced;
    }
    public void setTimePlaced ( String timePlaced )
    {
        this.timePlaced = timePlaced;
    }
    public String getOriginatingCountry ()
    {
        return originatingCountry;
    }
    public void setOriginatingCountry ( String originatingCountry )
    {
        this.originatingCountry = originatingCountry;
    }
    
    
    

}
