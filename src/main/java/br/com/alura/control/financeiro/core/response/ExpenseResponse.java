package br.com.alura.control.financeiro.core.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.alura.control.financeiro.enums.CategoryEnum;
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
public class ExpenseResponse {

    private String description;

    private BigDecimal value;

    private LocalDate data;

    private CategoryEnum category;
}
