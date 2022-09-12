package com.example.settlement.domain.event;

import java.time.LocalDateTime;

import com.example.settlement.AbstractEvent;
import com.example.settlement.domain.SettlementRequestStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettlementCompleted extends AbstractEvent{
  Long id;
  String gameReservationId;
  String businessId;
  Long amount;
  SettlementRequestStatus settlementRequestStatus;
  LocalDateTime settleDateTime;

}