package stepsPO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
public class selectProductPO {
    // Atributos
    final WebDriver driver; // este � objeto final do Selenium (Uma �nica bola em campo)
    // Definindo objetos para receber os mapeamentos de p�ginas j� importados
    private CartPage cartPage;
    private HomePage homePage;
    private InventoryItemPage inventoryItemPage;
    private InventoryPage inventoryPage;

    // Construtor
    public selectProductPO(BasePage basePage){
        this.driver = basePage.driver;  // passagem de bola = integra��o Selenium dentro e fora
    public selectProductPO(Base base){
            this.driver = base.driver;  // passagem de bola = integra��o Selenium dentro e fora
        }

        @Given("I access SauceDemo store PO")
        public void i_access_sauce_demo_store() {
            driver.get("https://www.saucedemo.com");
            this.homePage = new HomePage(this.driver);
        }

        @When("I filled a user {string} and password {string} PO")
        public void i_filled_a_user_and_password(String user, String password) {
            homePage.logar(user, password);
        }
        @And("I click in Login PO")
        public void i_click_in_login() {
            homePage.clicarNoBotaoLogin();
            inventoryPage = new InventoryPage(this.driver);
        }
        // @Then("show page's title {string}")
        @Then("I verify the page's title {string} PO")
        public void show_page_s_title(String pageTitle) {
            // Verifica se o titulo da p�gina coincide com o informado na feature
            assertEquals(inventoryPage.lerTituloDaPagina(), pageTitle);
        }
        @And("show cart's link PO")
        public void show_cart_s_link() {
            // Verifica se o elemento do carrinho de compras est� vis�vel
            assertTrue(inventoryPage.imgCarrinho.isDisplayed());
        }
        @When("I click in product {string} PO")
        public void i_click_in_product(String productId) {
            // Clica no elemento correspondente ao c�digo do produto informado na feature
            inventoryPage.clicarNoTituloDoProduto(productId);
            inventoryItemPage = new InventoryItemPage(this.driver);
        }
        @Then("I verify the product title {string} PO")
        public void i_verify_the_product_title(String productTitle) {
            @@ -75,6 +78,7 @@ public void i_click_in_add_to_cart() {
                public void i_click_in_cart_icon() {
                    // Clica no icone do carrinho de compras
                    inventoryPage.clicarNoCarrinho();
                    cartPage = new CartPage(this.driver);
                }

                @And("I verify the quantity is {string} PO")
                public void i_verify_the_quantity_is(String quantity) {
                    // Verifica se a quantidade corresponde a feature
                    assertEquals(cartPage.lerQuantidadeDoProdutoNoCarrinho(), quantity);
                }
                @Then("I verify the product title {string} in cart PO")
                public void i_verify_the_product_title_in_cart(String productTitle) throws InterruptedException {
                    for (int i = 1; i < cartPage.contarProdutosNoCarrinho(); i++) {
                        cartPage.clicarNoBotaoRemovernoCarrinho();
                    }
                    assertEquals(cartPage.lerTituloProdutoNoCarrinho(), productTitle);
                    Thread.sleep(2000);
                }
                @Then("I verify the product price {string} in cart PO")
                public void i_verify_the_product_price_in_cart(String productPrice) {
                    assertEquals(cartPage.lerPrecoProdutoNoCarrinho(), productPrice);
                }
