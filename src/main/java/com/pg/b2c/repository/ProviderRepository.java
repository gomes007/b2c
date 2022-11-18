package com.pg.b2c.repository;

import com.pg.b2c.domain.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProviderRepository extends JpaRepository<Provider, Long> {

    List<Provider> findByFantasyNameContaining(String fantasyName);

}
