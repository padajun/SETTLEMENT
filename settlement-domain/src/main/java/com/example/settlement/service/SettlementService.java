package com.example.settlement.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.settlement.api.dto.SettlementListResponse;
import com.example.settlement.api.dto.SettlementRequest;
import com.example.settlement.api.dto.SettlementResponse;
import com.example.settlement.domain.Settlement;
import com.example.settlement.domain.repository.SettlementRepository;
import com.example.settlement.exception.ApiException;
import com.example.settlement.exception.ApiStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SettlementService {
    
    private final SettlementRepository settlementRepository;

    @Transactional
    public long createSettlement(final SettlementRequest request){

    //log.info("businessId:{}", request.getBusinessId());
    long id =settlementRepository.save(
            Settlement.builder()
            .gameReservationid(request.getGameReservationId())
            .businessId(request.getBusinessId())
            .amount(request.getAmount())
            .build()
        ).getId();
        log.info("Saved Settlement. id :{}",id);
        return id;
    }

    public SettlementResponse getSettlement(final Long id, final Long businessId){
        
        Settlement settlement = getSettlementById(id);
        validateBusiness(settlement, businessId);
        return SettlementResponse.of(settlement);
    }
    
    private Settlement getSettlementById(Long id) {
        log.debug("Find Settlement.. id : {}", id);
        return settlementRepository.findById(id).orElseThrow(()-> new ApiException(ApiStatus.NOT_FOUND_SETTLEMENT));

    }

    public SettlementListResponse getSettlementList(final Long businessId){
        return SettlementListResponse.of(settlementRepository.findByBusinessId(businessId));
    }

    private void validateBusiness(Settlement settlement, Long businessId){
        log.debug("validate businessId. id:{}, businessId : {}", settlement.getId(), businessId);
        if(settlement.isNotBussinessId(businessId)){
            throw new ApiException(ApiStatus.INVALID_MODIFY_SETTLEMENT);
        }
    }

    @Transactional
    public Optional<SettlementResponse> updateStutus(final Long id, SettlementResponse settlementResponse){
        Settlement settlement = getSettlementById(id);
        
        validateStatus(settlement);
        settlement.

    }

    private void validateStatus(Settlement settlement){
        log.debug("validate settlement status id :{}, status : {}", settlement.getId(), settlement.getSettlementRequestStatus());
        if(settlement.isNotRegisterdStatus()){
            throw new ApiException(ApiStatus.INVALID_STATUS_SETTLEMENT);
        }
    }
}

