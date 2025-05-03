package com.italoramon.itau_backend.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransactionRequest(BigDecimal valor, OffsetDateTime dataHora) {

}
