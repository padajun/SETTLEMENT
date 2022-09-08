package com.example.settlement.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// aggerigate root
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name="settlements")
public class Settlement {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      @Column(name = "game_reservation_id", nullable = false)
      private Long gameReservationId;

      @Column(name = "business_id", nullable = false)
      private Long businessId;

      @Column(name = "amount")
      private Long amount;

      @Column(name = "settlement_request_status", nullable = false)
      @Enumerated(EnumType.STRING)
      private SettlementRequestStatus settlementRequestStatus;

      @CreatedDate
      @Column(name = "created_date_time")
      private LocalDateTime createdDateTime;

      @LastModifiedDate
      @Column(name = "settle_date_time")
      private LocalDateTime settleDateTime;

      @Builder
      public Settlement(Long businessId, Long gameReservationid, Long amount){
            this.gameReservationId = gameReservationid;
            this.businessId = businessId;
            this.amount = amount;
            this.settlementRequestStatus = SettlementRequestStatus.REGISTERED;
            this.createdDateTime = LocalDateTime.now();

      }

      public void setSatus(SettlementRequestStatus settlementRequestStatus){
            this.settlementRequestStatus = settlementRequestStatus;
      }

      public boolean isNotBussinessId(Long businessId){
            return !this.businessId.equals(businessId);
      }

      public boolean isNotRegistedStatus(){
            return this.settlementRequestStatus !=SettlementRequestStatus.REGISTERED;
      }

      public boolean isNotRequestgitStatus(){
            return this.settlementRequestStatus !=SettlementRequestStatus.REQUEST;
      }

      

      @Override
      public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Settlement that = (Settlement) o;
        return Objects.equals(id, that.id);
      }

      @Override
      public int hashCode() {
        return Objects.hash(id);
      }
}
