package br.com.alura.control.financeiro.core.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SummaryResponse {

    private Double sumExpense;

    private Double sumRevenue;

    private Double balance;

    private List<SummaryCategoryEnumResponse> expenseCategory;
    
}
