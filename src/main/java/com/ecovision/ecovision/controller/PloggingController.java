package com.ecovision.ecovision.controller;

import com.ecovision.ecovision.dto.PloggingRequestDto;
import com.ecovision.ecovision.dto.PloggingResponseDto;
import com.ecovision.ecovision.service.PloggingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plogging")
public class PloggingController {

    private final PloggingService ploggingService;
    public PloggingController(PloggingService ploggingService) {
        this.ploggingService = ploggingService;
    }

    @PostMapping("/record")
    public ResponseEntity<?> record(@RequestBody PloggingRequestDto requestDto) {
        PloggingResponseDto responseDto = ploggingService.createPlogging(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/viewbyid")
    public ResponseEntity<?> view(@RequestParam(name = "id") Long id) {
        try {
            PloggingResponseDto response = ploggingService.ploggingViewById(id);
            return ResponseEntity.ok(response);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
