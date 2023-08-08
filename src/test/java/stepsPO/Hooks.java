package stepsPO;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.BasePage;
import pages.Base;

import java.time.Duration;

public class Hooks {
    BasePage basePage;
    Base base;

    public Hooks(BasePage basePage){
        this.basePage = basePage;
    public Hooks (Base base) {
            this.base = base;
        }

        @BeforeClass // Executa antes de todos os blocos de passos --> usar do Cucumber
        @Before // Executa antes de todos os blocos de passos --> usar do Cucumber
        public void before_all(){
            ChromeOptions options = new ChromeOptions();      // instancia o objeto de Opções do ChromeDriver
            options.addArguments("--remote-allow-origins=*"); // Permite qualquer origem remota
            WebDriverManager.chromedriver().setup();          // baixa a versão mais atual do ChromeDriver

            basePage.driver = new ChromeDriver(options);               // instancia o objeto do Selenium como ChromeDriver
            base.driver = new ChromeDriver(options);               // instancia o objeto do Selenium como ChromeDriver

            // Estabelece uma espera implicita de 5 segundos para carregar qualquer elemento
            basePage.driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
            basePage.driver.manage().window().maximize();              // Maximiza a janela do navegador
            base.driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
            base.driver.manage().window().maximize();              // Maximiza a janela do navegador
        }

        @AfterClass // Executa após todos os blocos de passos --> usar do Cucumber
        @After // Executa após todos os blocos de passos --> usar do Cucumber
        public void after_all()  {
            basePage.driver.quit();                                    // Encerrar o objeto do Selenium WebDriver
            base.driver.quit();                                    // Encerrar o objeto do Selenium WebDriver
        }


    }