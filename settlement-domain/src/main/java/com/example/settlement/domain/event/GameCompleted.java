package com.example.settlement.domain.event;

import java.time.LocalDateTime;

import com.example.settlement.AbstractEvent;
import com.example.settlement.domain.SettlementRequestStatus;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class GameCompleted extends AbstractEvent{
    long id;
    private long biddingId;
    private long gameReservationId;
    private String businessId;        // 사업장ID
    private String businessName;      // 사업장명
    private String businessPhoneNum;  // 사업장연락처


    private String biddingAmount;     // 응찰금액
    private String biddingStatus;     // 진행상태


    private String memberId; // 회원 ID
    private String memberName; // 회원이름20
    private String memberPhoneNum; // 회원 휴대전화번호
    private String reservationStatus; // 예약상태
    //SettlementRequestStatus settleRequestStatus;
    //LocalDateTime settleDate;
}
