package com.example.settlement.domain.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.settlement.domain.Settlement;

public interface SettlementRepository extends JpaRepository<Settlement, Long>{    // Repository Pattern Interface  // Dependency Inversion Principle

    List<Settlement> findByBusinessId(Long businessId); // select * from item where settlementId=<settlementId>


}