package br.com.alura.control.financeiro.feature;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/br/com/alura/control/financeiro/resources/br/com/alura/control/financeiro/feature"},
        plugin = { "pretty"},
        extraGlue = {"br.com.alura.control.financeiro.feature.start"},
        stepNotifications = true
        )
public class RunCucumberTestIT {
    
}
