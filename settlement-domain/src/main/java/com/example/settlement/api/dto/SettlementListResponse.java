package com.example.settlement.api.dto;

import java.time.format.DateTimeFormatter;
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
        List<SettlementInfoResponse> settlementResponseList = settlementList.stream()
            .map(SettlementInfoResponse::of)
            .collect(Collectors.toList());

        return new SettlementListResponse(settlementResponseList);
    }


    @EqualsAndHashCode
    @Getter
    @Builder
    public static class SettlementInfoResponse {

        @Schema(description = "정산ID")
        private long id;

        @Schema(description = "사업장ID")
        private long businessId;

        @Schema(description = "게임예약ID")
        private long gameReservationId;

        @Schema(description = "정산요청금액")
        private long amount;

        @Schema(description = "정산요청상태")
        private SettlementRequestStatus settlementRequestStatus;

        @Schema(description = "정산요청일시")
        private String settleDate;

        public static SettlementInfoResponse of(Settlement settlement){
            return SettlementInfoResponse.builder()
                    .id(settlement.getId())
                    .businessId(settlement.getBusinessId())
                    .gameReservationId(settlement.getGameReservationId())
                    .settlementRequestStatus(settlement.getSettlementRequestStatus())
                    .amount(settlement.getAmount())
                    .settleDate(settlement.getSettleDateTime().format(DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss")))
                    .build();
        }
    }

}
