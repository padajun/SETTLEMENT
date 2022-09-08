package com.example.settlement.api.dto;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SettlementRequest {
    
    @Schema(description = "게임예약ID")
    @NotNull(message = "{gameReservationId.not.empty}")
    private Long gameReservationId;


    @Schema(description = "사업장ID")
    @NotNull(message = "{businessId.not.empty}")
    private Long businessId;
    
    @Schema(description = "정산금액")
    private Long amount;
    
    
}
