package com.example.settlement.api.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.example.settlement.domain.Settlement;
import com.example.settlement.domain.SettlementRequestStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SettlementListResponse {
    
    @Schema(description = "정산내역리스트")
    private List<SettlementInfoResponse> settlements;

    public static SettlementListResponse of(List<Settlement> settlementList){
        List<SettlementInfoResponse> settlementInfoResponse = settlementList.stream()
            .map(SettlementInfoResponse::of)
            .collect(Collectors.toList());

        return new SettlementListResponse(settlementInfoResponse);
    }


    @EqualsAndHashCode
    @Getter
    @Builder
    public static class SettlementInfoResponse {

        @Schema(description = "정산ID")
        private long id;

        @Schema(description = "사업장ID")
        private String businessId;

        @Schema(description = "게임예약ID")
        private Long gameReservationId;

        @Schema(description = "정산요청금액")
        private String amount;

        @Schema(description = "정산요청상태")
        private SettlementRequestStatus settlementRequestStatus;
         
        @Schema(description = "정산생성일시")
        private LocalDateTime createdDateTime;

        @Schema(description = "정산처리일시")
        private LocalDateTime settleDate;
        
        public static SettlementInfoResponse of(Settlement settlement){
            return SettlementInfoResponse.builder()
                    .id(settlement.getId())
                    .businessId(settlement.getBusinessId())
                    .gameReservationId(settlement.getGameReservationId())
                    .settlementRequestStatus(settlement.getSettlementRequestStatus())
                    .amount(settlement.getAmount())
                    .createdDateTime(settlement.getCreatedDateTime())
                    .settleDate(settlement.getSettleDateTime())
                    .build();
        }
    }

}
