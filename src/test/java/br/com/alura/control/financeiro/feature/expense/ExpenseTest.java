package br.com.alura.control.financeiro.feature.expense;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.alura.control.financeiro.core.response.ExpenseResponse;
import br.com.alura.control.financeiro.feature.AbstractSteps;
import br.com.alura.control.financeiro.feature.TestContext;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class ExpenseTest extends AbstractSteps{

    TestContext context = testContext();

	private String offset;
	private String limit;

    @Dado("que informo pagina {string}")
    public void que_informo_pagina(String string) {
        this.offset = string;
    }

    @Dado("que informo o tamanho {string}")
    public void que_informo_o_tamanho(String string) {
        this.limit = string;
    }

    @Quando("solicito a consulta")
    public void solicito_a_consulta() {
        ResponseEntity<List<ExpenseResponse>> response = getAllExpensesPage(this.offset, this.limit);
		context.setResponse(response);
		context.set(HTTP_CODE_RESPONSE, response.getStatusCode().value());
    }

    @Entao("o status deve retornar {int}")
    public void o_status_deve_retornar(Integer int1) {
        Integer httpCodeResponse = testContext().get(HTTP_CODE_RESPONSE);
		assertEquals(int1,httpCodeResponse);
    }

}
