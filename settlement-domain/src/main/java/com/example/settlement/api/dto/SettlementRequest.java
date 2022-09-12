package com.example.settlement.api.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;

public class SettlementRequest {
    
    @Schema(description = "게임예약ID")
    @NotBlank(message = "{gameReservationId.not.empty}")
    private String gameReservationId;

    @Schema(description = "사업장ID")
    @NotBlank(message = "{businessId.not.empty}")
    private String businessId;
    
    @Schema(description = "정산금액")
    private Long amount;

    /**
     * @return the gameReservationId
     */
    public String getGameReservationId() {
        return gameReservationId;
    }

    /**
     * @return the businessId
     */
    public String getBusinessId() {
        return businessId;
    }

    /**
     * @return the amount
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * @param gameReservationId
     * @param businessId
     * @param amount
     */
    public SettlementRequest(@NotBlank(message = "{gameReservationId.not.empty}") String gameReservationId,
            @NotBlank(message = "{businessId.not.empty}") String businessId, Long amount) {
        this.gameReservationId = gameReservationId;
        this.businessId = businessId;
        this.amount = amount;
    }

    
    
}
