package br.com.alura.control.financeiro.core.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@Getter
public class RevenueResponse {
    private Long id;

    private String description;

    private BigDecimal value;

    private String data;
}
