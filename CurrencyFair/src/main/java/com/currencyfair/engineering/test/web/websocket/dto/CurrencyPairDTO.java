package com.currencyfair.engineering.test.web.websocket.dto;

import java.math.BigDecimal;

public class CurrencyPairDTO
{
    private String pairName;
    private BigDecimal transactionVolume;
    
    
    public String getPairName ()
    {
        return pairName;
    }
    public void setPairName ( String pairName )
    {
        this.pairName = pairName;
    }
    public BigDecimal getTransactionVolume ()
    {
        return transactionVolume;
    }
    public void setTransactionVolume ( BigDecimal transactionVolume )
    {
        this.transactionVolume = transactionVolume;
    }
    
    

}
