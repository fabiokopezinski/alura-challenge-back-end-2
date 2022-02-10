package br.com.alura.control.financeiro.feature.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StartScenario {

    @Autowired
    protected TestRestTemplate template;
	
	@Before
    public void beforeall(Scenario scenario) {

    }

    @After
    public void hookToEachAfter(Scenario scenario) {
    }
    
}
