package com.example.settlement.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.settlement.domain.Settlement;


public interface SettlementRepository extends PagingAndSortingRepository<Settlement, Long>{    // Repository Pattern Interface  // Dependency Inversion Principle

    List<Settlement> findByBusinessId(String businessId); // select * from item where businessId=<businessId>
    Optional<Settlement> findById(Long Id); // select * from item where settlementId=<settlementId>

}