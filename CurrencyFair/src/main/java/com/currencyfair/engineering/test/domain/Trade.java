package com.currencyfair.engineering.test.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.currencyfair.engineering.test.domain.util.CustomDateTimeDeserializer;
import com.currencyfair.engineering.test.domain.util.CustomDateTimeSerializer;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A Trade.
 */
@Entity
@Table(name = "T_TRADE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Trade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "currency_from")
    private String currencyFrom;

    @Column(name = "currency_to")
    private String currencyTo;

    @Column(name = "amount_sell", precision=10, scale=2)
    private BigDecimal amountSell;

    @Column(name = "amount_buy", precision=10, scale=2)
    private BigDecimal amountBuy;

    @Column(name = "rate", precision=10, scale=2)
    private BigDecimal rate;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Column(name = "time_placed", nullable = false)
    private DateTime timePlaced;

    @Column(name = "originating_country")
    private String originatingCountry;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public BigDecimal getAmountSell() {
        return amountSell;
    }

    public void setAmountSell(BigDecimal amountSell) {
        this.amountSell = amountSell;
    }

    public BigDecimal getAmountBuy() {
        return amountBuy;
    }

    public void setAmountBuy(BigDecimal amountBuy) {
        this.amountBuy = amountBuy;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public DateTime getTimePlaced() {
        return timePlaced;
    }

    public void setTimePlaced(DateTime timePlaced) {
        this.timePlaced = timePlaced;
    }

    public String getOriginatingCountry() {
        return originatingCountry;
    }

    public void setOriginatingCountry(String originatingCountry) {
        this.originatingCountry = originatingCountry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Trade trade = (Trade) o;

        if (id != null ? !id.equals(trade.id) : trade.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", userId='" + userId + "'" +
                ", currencyFrom='" + currencyFrom + "'" +
                ", currencyTo='" + currencyTo + "'" +
                ", amountSell='" + amountSell + "'" +
                ", amountBuy='" + amountBuy + "'" +
                ", rate='" + rate + "'" +
                ", timePlaced='" + timePlaced + "'" +
                ", originatingCountry='" + originatingCountry + "'" +
                '}';
    }
}
