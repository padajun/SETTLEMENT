package com.example.settlement.api.dto;

import com.example.settlement.domain.Settlement;
import com.example.settlement.domain.SettlementRequestStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SettlementResponse {
    @Schema(description = "정산ID")
    private Long id;

    @Schema(description = "사업장ID")
    private String businessId;

    @Schema(description = "게임예약ID")
    private Long gameReservationId;

    @Schema(description = "정산금액")
    private String amount;

    @Schema(description = "정산상태")
    private SettlementRequestStatus settlementRequestStatus;

    public static SettlementResponse of(Settlement settlement){
        return SettlementResponse.builder()
        .id(settlement.getId())
        .businessId(settlement.getBusinessId())
        .gameReservationId(settlement.getGameReservationId())
        .amount(settlement.getAmount())
        .settlementRequestStatus(settlement.getSettlementRequestStatus())
        .build();
    }
}
