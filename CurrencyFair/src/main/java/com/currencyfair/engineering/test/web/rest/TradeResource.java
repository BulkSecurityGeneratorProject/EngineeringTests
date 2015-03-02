package com.currencyfair.engineering.test.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.currencyfair.engineering.test.domain.Trade;
import com.currencyfair.engineering.test.repository.TradeRepository;
import com.currencyfair.engineering.test.web.rest.dto.TradeDTO;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * REST controller for managing Trade.
 */
@RestController
@RequestMapping("/api")
public class TradeResource {

    private final Logger log = LoggerFactory.getLogger(TradeResource.class);

    @Inject
    private TradeRepository tradeRepository;
    
    @Autowired
    CamelContext camelContext;
    
    @Autowired
    private ProducerTemplate producerTemplate;

    /**
     * POST  /trades -> Create a new trade.
     */
    @RequestMapping(value = "/trades",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody TradeDTO tradeDTO) {
        log.debug("REST request to save Trade : {}", tradeDTO);
        //tradeRepository.save(trade);
        producerTemplate.sendBody("seda:trade", tradeDTO);
    }

    /**
     * GET  /trades -> get all the trades.
     */
    @RequestMapping(value = "/trades",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Trade> getAll() {
        log.debug("REST request to get all Trades");
        return tradeRepository.findAll();
    }

    /**
     * GET  /trades/:id -> get the "id" trade.
     */
    @RequestMapping(value = "/trades/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Trade> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Trade : {}", id);
        Trade trade = tradeRepository.findOne(id);
        if (trade == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trade, HttpStatus.OK);
    }

    /**
     * DELETE  /trades/:id -> delete the "id" trade.
     */
    @RequestMapping(value = "/trades/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Trade : {}", id);
        tradeRepository.delete(id);
    }
}
