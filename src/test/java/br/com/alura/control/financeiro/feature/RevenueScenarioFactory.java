package br.com.alura.control.financeiro.feature;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.com.alura.control.financeiro.core.entities.Revenue;
import br.com.alura.control.financeiro.core.request.RevenueRequest;
import br.com.alura.control.financeiro.core.response.RevenueResponse;

public class RevenueScenarioFactory {

    public static final Revenue REVENUE_ONE = loadExpense();

    public static final Revenue REVENUE_TWO = loadExpenseTwo();

    public static final RevenueResponse REVENUE_RESPONSE = loadRevenueResponse();

    public static final RevenueResponse REVENUE_RESPONSE_TWO = loadRevenueResponseTwo();

    public static final RevenueRequest REVENUE_REQUEST = loadExpenseRequest();

    public static final Page<Revenue> REVENUE_FIND_ALL = loadFindAll();

    private static Revenue loadExpense() {
        return Revenue.builder().id(1L).description("ReceitaUm").data(LocalDate.parse("2021-10-02")).value(BigDecimal.ONE).build();
    }

    private static Page<Revenue> loadFindAll() {

        PageRequest page=PageRequest.of(0, 2);

        List<Revenue> list=new ArrayList<>();

        list.add(REVENUE_ONE);

        list.add(REVENUE_TWO);

        return new PageImpl<Revenue>(list, page, 10);
    }

    private static RevenueRequest loadExpenseRequest() {
        return RevenueRequest.builder().description("ReceitaTres")
                .value(BigDecimal.ONE).data(LocalDate.now()).build();
    }

    private static RevenueResponse loadRevenueResponseTwo() {
        return RevenueResponse.builder().id(2L).description("ReceitaTres")
                .value(BigDecimal.ONE).data(LocalDate.now()).build();
    }

    private static RevenueResponse loadRevenueResponse() {
        return RevenueResponse.builder().id(2L).description("ReceitaTres")
                .value(BigDecimal.ONE).data(LocalDate.now()).build();
    }

    private static Revenue loadExpenseTwo() {
        return Revenue.builder().id(1L).description("ReceitaDois").data(LocalDate.parse("2021-10-02")).value(BigDecimal.ONE).build();
    }
}
