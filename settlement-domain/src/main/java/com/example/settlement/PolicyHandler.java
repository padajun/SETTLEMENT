package com.example.settlement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.example.settlement.domain.Settlement;
import com.example.settlement.domain.event.GameCompleted;
import com.example.settlement.domain.repository.SettlementRepository;
import com.example.settlement.kafka.KafkaProcessor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PolicyHandler {
    @Autowired
    SettlementRepository settlementRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    // @StreamListener(KafkaProcessor.INPUT)
    // public void wheneverPetReserved_displayOnTheStore(@Payload PetReserved petReserved){
    //     if(!petReserved.validate())
    //         return;

    //     Item item = new Item();
    //     item.setAppearance(petReserved.getAppearance());
    //     item.setHealth(petReserved.getEnergy());
    //     item.setPetId(petReserved.getId());

    //     itemRepository.save(item);
    // }
   
    // @StreamListener(KafkaProcessor.INPUT)
    // public void wheneverPetUpdate_updateItem(@Payload PetUpdated petUpdated){
    //     if(!petUpdated.validate())
    //         return;

    //     itemRepository.findByPetId(petUpdated.getId()).ifPresent(item->{
    //         item.setAppearance(petUpdated.getAppearance());
    //         item.setHealth(petUpdated.getEnergy());
    //         itemRepository.save(item);
    //     });

    // }
    
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverGameCompleted_CreateSettlement(@Payload GameCompleted gameCompleted){
        if(!gameCompleted.validate())
            return;

            long id =settlementRepository.save(
            Settlement.builder()
            .gameReservationid(gameCompleted.getGameReservationId())
            .businessId(gameCompleted.getBusinessId())
            .amount(gameCompleted.getAmount())
            .build()
            ).getId();
            log.info("Saved Settlement. id :{}",id);
            

    }
    
    ///// *** Example ****

    // @StreamListener(KafkaProcessor.INPUT)
    // public void wheneverChargeStarted_recordHistory(@Payload ChargeStarted chargeStarted){
    //     if(!chargeStarted.validate())
    //         return;

    //     ChargedHistory chargedHistory = new ChargedHistory();

    //     chargedHistory.setChargedCustomer(new ChargedCustomer());
    //     chargedHistory.getChargedCustomer().setCustomerId(chargeStarted.getCustomerId());
    //     chargedHistory.getChargedCustomer().setCustomerName(chargeStarted.getCustomerName());

    //     chargedHistory.setChargerId(chargeStarted.getChargerId());
    //     chargedHistory.setStartTime(chargeStarted.getTimestamp());


    //     chargedHistoryRepository.save(chargedHistory);
    // }


    // @StreamListener(KafkaProcessor.INPUT)
    // public void wheneverChargeEnded_recordHistory(@Payload ChargeEnded chargeEnded){
    //     if(!chargeEnded.validate())
    //         return;

    //     //변경 case
    //     chargedHistoryRepository.findChargerId(chargeEnded.getId()).ifPresent(chargedHistory->{
    //         chargedHistory.setEndTime(chargeEnded.getTimestamp());
    //         chargedHistory.setStatus(ChargeStatus.ENDED);
    //         chargedHistoryRepository.save(item);
    //     });


    //     // 계속 누적

    //     ChargedHistory chargedHistory = new ChargedHistory();

    //     chargedHistory.setChargedCustomer(new ChargedCustomer());
    //     chargedHistory.getChargedCustomer().setCustomerId(chargeStarted.getCustomerId());
    //     chargedHistory.getChargedCustomer().setCustomerName(chargeStarted.getCustomerName());

    //     chargedHistory.setChargerId(chargeStarted.getChargerId());
    //     chargedHistory.setTime(chargeStarted.getTimestamp());
    //     chargedHistory.setHistoryType(HistoryType.CHARGE_ENDED);


    //     chargedHistoryRepository.save(chargedHistory);

    // }



}