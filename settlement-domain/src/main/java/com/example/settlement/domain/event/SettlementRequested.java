package com.example.settlement.domain.event;

import java.time.LocalDateTime;

import com.example.settlement.AbstractEvent;
import com.example.settlement.domain.SettlementRequestStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettlementRequested extends AbstractEvent{
  Long id;
  Long gameReservationId;
  Long businessId;
  Long amount;
  SettlementRequestStatus settleRequestStatus;
  LocalDateTime settleDate;

}