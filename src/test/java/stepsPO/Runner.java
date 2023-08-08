package stepsPO;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(features = {"src/test/resources/features"}, // Onde est�o os cenarios em Gherkin
        glue = {"steps", "stepsPO"},                       // Onde est�o as defini��es de passos
        dryRun = false,                               // Exibi��o de log
        monochrome = true                              // Detalhes do log)
)
@Test
public class Runner extends AbstractTestNGCucumberTests {

}