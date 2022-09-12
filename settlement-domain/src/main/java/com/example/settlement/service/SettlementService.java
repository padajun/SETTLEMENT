package com.example.settlement.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.settlement.api.dto.SettlementListResponse;
import com.example.settlement.api.dto.SettlementRequest;
import com.example.settlement.api.dto.SettlementResponse;
import com.example.settlement.domain.Settlement;
import com.example.settlement.domain.SettlementRequestStatus;
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
    public long createSettlement(SettlementRequest request){
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
    public SettlementResponse getSettlement(final Long id, final String businessId){   
        Settlement settlement = getSettlementById(id);
        validateBusiness(settlement, businessId);
        return SettlementResponse.of(settlement);
    }
    
    private Settlement getSettlementById(Long id) {
        log.debug("Find Settlement.. id : {}", id);
        return settlementRepository.findById(id).orElseThrow(()-> new ApiException(ApiStatus.NOT_FOUND_SETTLEMENT));

    }

    private void validateBusiness(Settlement settlement, String businessId){
        log.debug("validate businessId. id:{}, businessId : {}", settlement.getId(), businessId);
        if(settlement.isNotBussinessId(businessId)){
            throw new ApiException(ApiStatus.INVALID_MODIFY_SETTLEMENT);
        }
    }

    public SettlementListResponse getSettlementList(final String businessId){
        
        return SettlementListResponse.of(settlementRepository.findByBusinessId(businessId));
    }



    @Transactional
    public SettlementResponse settlementApprve(Long id){
        final Settlement settlement = getSettlementById(id);
        validateStatus(settlement);
        settlement.setId(id);
        settlement.setGameReservationId(settlement.getGameReservationId());
        settlement.setBusinessId(settlement.getGameReservationId());
        settlement.setAmount(settlement.getAmount());
        settlement.setSatus(SettlementRequestStatus.APPV);
        settlement.setCreatedDateTime(settlement.getCreatedDateTime());
        settlement.setSettleDateTime(LocalDateTime.now());
        settlementRepository.save(settlement);
        return SettlementResponse.of(settlement);

    }

    @Transactional
    public SettlementResponse settlementReject(Long id){
        final Settlement settlement = getSettlementById(id);
        validateStatus(settlement);
        settlement.setId(id);
        settlement.setGameReservationId(settlement.getGameReservationId());
        settlement.setBusinessId(settlement.getGameReservationId());
        settlement.setAmount(settlement.getAmount());
        settlement.setSatus(SettlementRequestStatus.REJECT);
        settlement.setCreatedDateTime(settlement.getCreatedDateTime());
        settlement.setSettleDateTime(LocalDateTime.now());
        settlementRepository.save(settlement);
        return SettlementResponse.of(settlement);

    }

    private void validateStatus(Settlement settlement){
        log.debug("validate settlement status id :{}, status : {}", settlement.getId(), settlement.getSettlementRequestStatus());
        if(settlement.isNotRequestedStatus()){
            
            throw new ApiException(ApiStatus.INVALID_STATUS_SETTLEMENT);
        }
    }

    
}

