package com.fabriciomoreira.exchangerate.domain.repository;

import com.fabriciomoreira.exchangerate.domain.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

}
