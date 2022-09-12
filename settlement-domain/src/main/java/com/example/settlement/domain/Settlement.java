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
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.settlement.domain.event.SettlementCompleted;
import com.example.settlement.domain.event.SettlementRejected;
import com.example.settlement.domain.event.SettlementRequested;

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
      private String gameReservationId;

      @Column(name = "business_id", nullable = false)
      private String businessId;

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
      public Settlement(String businessId, String gameReservationid, Long amount){
            this.gameReservationId = gameReservationid;
            this.businessId = businessId;
            this.amount = amount;
            this.settlementRequestStatus = SettlementRequestStatus.REQUEST;
            this.createdDateTime = LocalDateTime.now();
            this.settleDateTime = LocalDateTime.now();

      }

      @PostPersist
      public void onPostPersist(){
            SettlementRequested settlementRequested = new SettlementRequested();
            BeanUtils.copyProperties(this, settlementRequested);
            settlementRequested.publishAfterCommit();
      }

      @PostUpdate
      public void onPostUpdate(){
            if(this.getSettlementRequestStatus().equals(SettlementRequestStatus.APPV))
            {
            SettlementCompleted settlementCompleted = new SettlementCompleted();
            BeanUtils.copyProperties(this, settlementCompleted);
            settlementCompleted.publishAfterCommit();
            }
            else if(this.getSettlementRequestStatus().equals(SettlementRequestStatus.REJECT))
            {
            SettlementRejected settlementRejected = new SettlementRejected();
            BeanUtils.copyProperties(this, settlementRejected);
            settlementRejected.publishAfterCommit();
            }
      }

      public void setSatus(SettlementRequestStatus settlementRequestStatus){
            this.settlementRequestStatus = settlementRequestStatus;
      }

      public boolean isNotBussinessId(String businessId){
            return !this.businessId.equals(businessId);
      }

      public boolean isNotRequestedStatus(){
            return this.settlementRequestStatus !=SettlementRequestStatus.REQUEST;
      }

      public boolean isRejectedOrCompletedStatus(){
            return this.settlementRequestStatus == SettlementRequestStatus.REJECT || this.settlementRequestStatus == SettlementRequestStatus.APPV;
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
