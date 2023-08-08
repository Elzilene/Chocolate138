// 1 - Pacote
package webtest;
// 2 - Bibliotecas

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

// 3 - Classe
public class seleniumSimples {
    // 3.1 Atributos
    WebDriver driver; // objeto do Selenium WebDriver

    // 3.2 Funções e Métodos de Apoio
    // Não vamos criar

    // 3.3 Antes do Teste
    @BeforeMethod
    public void setUp(){
        // Instalar e configurar o driver do navegador/browser
        WebDriverManager.chromedriver().setup(); // download e instalação do Chrome Driver

        // Configurar as opções para o driver do navegador (a partir do Selenium 4.8.0)
        ChromeOptions options = new ChromeOptions(); // objeto de configuração para o Chrome Driver
        options.addArguments("--remote-allow-origins=*"); // permitir qualquer origem remota

        // Instanciar o Selenium como driver de um navegador especifico
        driver = new ChromeDriver(options); // Instancia o Selenium para o Chrome Driver com opções
        // Configura o tempo geral de espera de elementos em até 5 segundos
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        driver.manage().window().maximize(); // maximiza a janela do navegador
    }

    // 3.4 Depois do Teste
    @AfterMethod
    public void tearDown(){
        driver.quit(); // destroi o objeto do Selenium WebDriver
    }

    // 3.5 Teste em Si
    @Test
    public void testarSelectBackdrop(){
        // Abrir a página inicial do site SauceDemo
        driver.get("https://www.saucedemo.com");

        // Digitar o usuário e a senha
        // Clicar no elemento antes de escrever

        WebElement username = driver.findElement(By.id("user-name")); // Controla a caixa de texto
        username.click(); // clica na caixa de texto username
        username.clear(); // limpa a caixa de texto
        username.sendKeys("standard_user"); // escreve na caixa (colar o texto)

        // username.sendKeys(Keys.chord("standard_user")); // escreve na caixa (letra por letra)
        // driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        driver.findElement(By.id("login-button")).click();

        // Transição de página / Carregamento de nova página (lentidão)

        // Verificar se estamos na página interna (se conseguimos entrar)
        // Verifica a palavra "Products" em determinado elemento
        assertEquals(driver.findElement(By.cssSelector("span.title")).getText(), "Products");
        // Verifica se está presente o elemento do carrinho de compras
        assertTrue(driver.findElement(By.id("shopping_cart_container")).isDisplayed());

        // Selecionar o produto que seria o id nº 4
        driver.findElement(By.id("item_4_title_link")).click();
        // Transição de tela para a página do produto

        // Validar o nome e o valor
        assertEquals(driver.findElement(By.cssSelector("div.inventory_details_name.large_size")).getText(),
                "Sauce Labs Backpack");
        assertEquals(driver.findElement(By.cssSelector("div.inventory_details_price")).getText(),
                "$29.99");

        // Clicar no botão Adicionar no Carrinho
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();


        // Clica no icone do Carrinho
        driver.findElement(By.id("shopping_cart_container")).click();
        // Outra transição/carregamento de página

        // Verificar o Titulo da Página, nome do produto, quantidade e preço
        assertEquals(driver.findElement(By.cssSelector("span.title")).getText(), "Your CartPage");
        assertEquals(driver.findElement(By.id("item_4_title_link")).getText(), "Sauce Labs Backpack");
        assertEquals(driver.findElement(By.cssSelector("div.cart_quantity")).getText(), "1");
        assertEquals(driver.findElement(By.cssSelector("div.inventory_item_price")).getText(), "$29.99");

    }

    public static class Hooks {
    BasePage basePage;

    public Hooks(BasePage basePage){
        this.basePage basePage;
    }
    }

    public static class ProductSelectPO {
        //Atributos
        final WebDriver driver; // esse é objeto final do selenium (uma única bola em campo dentro e fora)

        private CartPage cartPage;
        private HomePage homePage;
        private InventoryItemPage inventoryItemPage;
        private InventoryPage inventoryPage;

        private BasePage basePage;

        // Construtor
        public selectProductPO(BasePage basePage){
            this.driver = basePage.driver;   //passagem de bola = Integração Selenium dentro e fora

        }



       // userForCookie = user; // guardar o usuário para apagar o cookie no final

        @BeforeAll     // Execute antes de todos os blocos de passos -------------> usar do cucumber
        @Given("I access SauceDemo store PO")
        public void i_access_sauce_demo_store() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");  // Permite qualquer origem remota
            WebDriverManager.chromedriver().setup();  //baixar a versão mais atual do chromedriver
            basePage = new BasePage(this, driver); // instanciar com o driver

            basePage.driver = new ChromeDriver(options);
            driver = new ChromeDriver(options);



            // Estabelece uma espera implicita de 5 segundos para carregar qualquer elemento
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
            driver.manage().window().maximize();       // maximiza a janela do navegador

        }
        @AfterAll //Executa apos todos os blocos de passos -----------> usar do cucumber
        public void tearDown(){

        }
        @When("I filled a user {string} and password {string}PO")
        public void i_filled_a_user_and_password(String  user, String passwor) {
            homePage.logar(user,passwor);


        }
        @And("I click in Login")
        public void i_click_in_login() {]
            homePage.clicarnoBotaoLogin();

        }
        @Then("show page's title {string}PO")
        public void show_page_s_title(String pageTitle) {
            AssertJUnit.assertEquals((inventoryPage.lerTituloDaPagina(),pageTitle);

        }
        @Then("show cart's link")
        public void show_cart_s_link() {
            assertTrue(BasePage.imgCarrinho.isDisplay);

        }
        @When("I click in product {string}PO")
        public void i_click_in_product(String string) {
            inventoryPage.clicarNoTituloDoProduto();

        }
        @Then("I verify the product title {string}PO")
        public void i_verify_the_product_title(String string) {
            assertEquals(inventoryPage.lerTituloDaProduto());

        }
        @Then("I verify the product price {string}PO")
        public void i_verify_the_product_price(String string) {
            assertEquals(inventoryItemPage.lerPreçoDoProduto,productPrice);

        }
        @When("I click in Add to CartPage")
        public void i_click_in_add_to_cart() {

        }
        @When("I click in CartPage icon")
        public void i_click_in_cart_icon() {

        }
        @Then("I verify the page's title {string}PO")
        public void i_verify_the_page_s_title(String string) {

        }
        @Then("I verify the quantity is {string}")
        public void i_verify_the_quantity_is(String string) {

        }
        @Then("I verify the product price {string} in cart")
        public void i_verify_the_product_price(String productPrice) {
            assertEquals(driver.findElement(By.cssSelector("div.inventory_item_price")).getText())

        }
    }
}