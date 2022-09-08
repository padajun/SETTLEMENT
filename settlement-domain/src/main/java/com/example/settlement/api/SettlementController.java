package com.example.settlement.api;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.settlement.api.dto.SettlementListResponse;
import com.example.settlement.api.dto.SettlementRequest;
import com.example.settlement.api.dto.SettlementResponse;
import com.example.settlement.domain.SettlementRequestStatus;
import com.example.settlement.service.SettlementService;

import io.swagger.models.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;


@Tag(name ="SETTLEMENT", description = "정산 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/settlements")
public class SettlementController {

    private final SettlementService settlementService;

    
    @Operation(summary = "정산 생성")
    @ApiResponses(value = {
        @ApiResponse(responseCode ="201", description="정산생성성공")
    })
    @PostMapping
    public ResponseEntity<SettlementRequest> createSettlement(@Valid @RequestBody SettlementRequest request){

        Long id = settlementService.createSettlement(request);
        return ResponseEntity.created(URI.create("api/settlements/"+ id)).build();
        
    }


    @Operation(summary = "정산 조회")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정산조회성공",
                        content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = SettlementResponse.class))})
    })
    @GetMapping("/{id}")
    public ResponseEntity<SettlementResponse> getSettlement(@Parameter(description = "정산 ID") @PathVariable Long id, @RequestParam Long businessId){
        SettlementResponse response = settlementService.getSettlement(id, businessId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "정산 리스트 조회")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "정산 리스트 조회 성공",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = SettlementResponse.class))})
    })
    @GetMapping
    public ResponseEntity<SettlementListResponse> getSettlementList(@RequestParam Long businessId){
        SettlementListResponse resoponse = settlementService.getSettlementList(businessId);
        return ResponseEntity.ok(resoponse);
    }

    @Operation(summary = "정산 상태 변경")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description ="정산 상태 변경 성공",
                content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = SettlementResponse.class))})
    })
    @PutMapping("/{id}")
    public ResponseEntity<SettlementResponse> setSatusSettement(@Parameter(description = "정산id") @RequestParam Long id, @RequestParam SettlementRequestStatus settlementRequestStatus){
        
        return ResponseEntity.ok(response);
    }
    


}

