package com.example.settlement.api;

import java.net.URI;

import javax.validation.Valid;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.example.settlement.domain.Settlement;
import com.example.settlement.service.SettlementService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:8080","http://localhost:8088"})
@Tag(name ="SETTLEMENT", description = "정산 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/settlements")
public class SettlementController {

    private final SettlementService settlementService;

    
    @Operation(summary = "정산 생성", description = "정산요청을 등록한다")
    @ApiResponses(value = {
        @ApiResponse(responseCode ="201", description="정산생성성공")
    })
    @PostMapping
    public ResponseEntity<?> createSettlement(@Valid @RequestBody final SettlementRequest request){
        log.debug("################ SettlementController INPUT : "+ToStringBuilder.reflectionToString(request));
        //Settlement settlement = SettlementRequest.toEntity(request);
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
    public ResponseEntity<?> getSettlement(@Parameter(description = "정산 ID") @PathVariable Long id, @RequestParam String businessId){
        SettlementResponse response = settlementService.getSettlement(id, businessId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "사업장 별 정산 리스트 조회")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "정산 리스트 조회 성공",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = SettlementListResponse.class))})
    })
    @GetMapping
    public ResponseEntity<SettlementListResponse> getSettlementList(@RequestParam String businessId){
        SettlementListResponse resoponse = settlementService.getSettlementList(businessId);
        return ResponseEntity.ok(resoponse);
    }
 
    @Operation(summary = "정산 승인 처리")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description ="정산 승인 완료",
                content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Settlement.class))})
    })
    @PutMapping("/{id}")
    public ResponseEntity<SettlementResponse> ApproveSettlement(@Parameter(description = "정산id") @RequestParam Long id){
        SettlementResponse response = settlementService.settlementApprve(id);
        return ResponseEntity.ok(response);
    }
    
    @Operation(summary = "정산 반려 처리")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description ="정산 반려 완료",
                content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = SettlementResponse.class))})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<SettlementResponse> setSatusSettement(@Parameter(description = "정산id") @RequestParam Long id){
        SettlementResponse response = settlementService.settlementReject(id);
        return ResponseEntity.ok(response);
    }

}

