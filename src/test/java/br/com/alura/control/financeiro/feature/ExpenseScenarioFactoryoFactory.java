package br.com.alura.control.financeiro.feature;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.com.alura.control.financeiro.core.entities.Expense;
import br.com.alura.control.financeiro.core.request.ExpenseRequest;
import br.com.alura.control.financeiro.core.response.ExpenseResponse;

public class ExpenseScenarioFactoryoFactory {
    
    public static final Expense EXPENSE_ONE=loadExpanse();

    public static final Expense EXPENSE_TWO=loadExpanseTwo();

    public static final ExpenseResponse EXPENSE_RESPONSE=loadExpenseResponse();

    public static final ExpenseResponse EXPENSE_RESPONSE_TWO=loadExpenseResponseTwo();

    public static final ExpenseRequest EXPENSE_REQUEST=loadExpenseRequest();

    public static final Page<Expense> EXPENSE_FIND_ALL=loadFindAll();

    private static Expense loadExpanse() {
        return Expense.builder().id(1L).description("ReceitaUm")
        .value(BigDecimal.ONE).data("13/06/2022").build();
    }

    private static ExpenseResponse loadExpenseResponseTwo() {
        return ExpenseResponse.builder().id(1L).description("ReceitaTres")
        .value(BigDecimal.ONE).data("16/06/2022").build();
    }

    private static Page<Expense> loadFindAll() {

        PageRequest page=PageRequest.of(0, 2);

        List<Expense> list=new ArrayList<>();

        list.add(EXPENSE_ONE);

        list.add(EXPENSE_TWO);

        return new PageImpl<Expense>(list, page, 10);
    }

    private static ExpenseRequest loadExpenseRequest() {
        return ExpenseRequest.builder().description("ReceitaTres")
        .value(BigDecimal.ONE).data("16/06/2022").build();
    }

    private static ExpenseResponse loadExpenseResponse() {
        return ExpenseResponse.builder().id(1L).description("ReceitaDois")
        .value(BigDecimal.ONE).data("16/06/2022").build();
    }

    private static Expense loadExpanseTwo() {
        return Expense.builder().id(1L).description("ReceitaDois")
        .value(BigDecimal.ONE).data("16/06/2022").build();
    }
}
