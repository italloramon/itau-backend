package com.italoramon.itau_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.italoramon.itau_backend.dto.StatisticResponse;
import com.italoramon.itau_backend.service.StatisticService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/estatistica")
@RequiredArgsConstructor
public class StatisticController {
    private final StatisticService statisticService;

    @GetMapping
    public ResponseEntity<StatisticResponse> getStatistics(
        @RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervalSeconds) {
        StatisticResponse statistics = statisticService.getStatistics(intervalSeconds);
        return ResponseEntity.ok(statistics);
    }
}
