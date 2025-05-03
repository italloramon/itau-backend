package com.italoramon.itau_backend.service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.stereotype.Service;

import com.italoramon.itau_backend.dto.StatisticResponse;
import com.italoramon.itau_backend.dto.TransactionRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatisticService {
    private final TransactionService transactionService;

    public StatisticResponse getStatistics(Integer intervalSeconds) {
        log.info("Calculating statistics for the last {} seconds", intervalSeconds);
        List<TransactionRequest> transactions = transactionService.getTransactions(intervalSeconds);

        if (transactions.isEmpty()) {
            log.info("No transactions found in the last {} seconds", intervalSeconds);
            return new StatisticResponse(0L, 0.0, 0.0, 0.0, 0.0);
        }

        log.info("Calculating statistics for {} transactions", transactions.size());
        DoubleSummaryStatistics statisticsSummary = transactions.stream().mapToDouble(transaction -> transaction.valor().doubleValue()).summaryStatistics();

        return new StatisticResponse(
                statisticsSummary.getCount(),
                statisticsSummary.getSum(),
                statisticsSummary.getAverage(),
                statisticsSummary.getMin(),
                statisticsSummary.getMax()
        );
    }
}
