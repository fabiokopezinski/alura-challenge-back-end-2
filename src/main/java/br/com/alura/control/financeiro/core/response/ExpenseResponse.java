package br.com.alura.control.financeiro.core.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ExpenseResponse {

    private Long id;

    private String description;

    private BigDecimal valor;

    private String data;
}
