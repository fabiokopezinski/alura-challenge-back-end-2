package br.com.alura.control.financeiro.feature;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import br.com.alura.control.financeiro.core.response.ExpenseResponse;

public class AbstractSteps {
    
    public static final String HTTP_CODE_RESPONSE = "httpCodeResponse";

	@Autowired
	protected TestRestTemplate template;

	@LocalServerPort
	private int port;
	
	public String baseUrl() {
		return String.format("http://localhost:%d", port);
	}

	public TestContext testContext() {
		return TestContext.CONTEXT;
	}

    protected ResponseEntity<List<ExpenseResponse>> getAllExpensesPage(String offset, String limit) {
		HttpHeaders headers = new HttpHeaders();
		String pageQuery = "";

		if (offset != null && limit != null) {
			pageQuery = String.format("?offset=%s&limit=%s", offset, limit);
		}

		String url = String.format("%s/despesas%s", baseUrl(), pageQuery);
		HttpEntity<List<ExpenseResponse>> body = new HttpEntity<List<ExpenseResponse>>(
				headers);
		return template.exchange(url, HttpMethod.GET, body,
				new ParameterizedTypeReference<List<ExpenseResponse>>() {
				});
	}
	
}
