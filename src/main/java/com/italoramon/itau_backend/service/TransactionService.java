package com.italoramon.itau_backend.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.italoramon.itau_backend.dto.TransactionRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionService {
    private final List<TransactionRequest> transactions = new ArrayList<>();

    public void addTransaction(TransactionRequest transaction) {
        log.info("Adding transaction: {}", transaction);

        if (transaction.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("Transaction date is in the future: {}", transaction.dataHora());
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (transaction.valor().compareTo(BigDecimal.ZERO) < 0) {
            log.error("Transaction value is invalid: {}", transaction.valor());
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY);
        }   
        
        transactions.add(transaction);
        log.info("Transaction added successfully: {}", transaction);
    }

    public void clearTransactions() {
        log.info("Clearing all transactions");
        transactions.clear();
        log.info("All transactions cleared");
    }
}
