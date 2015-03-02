package com.currencyfair.engineering.test.repository;

import com.currencyfair.engineering.test.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Trade entity.
 */
public interface TradeRepository extends JpaRepository<Trade,Long>{

}
