package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class BasePage {
    @FindBy(id = "shopping_cart_container")
    public WebElement imgCarrinho;
    @FindBy(css = "span.title")
    WebElement lblTituloPagina;

    @FindBy(css = "button.btn btn_primary btn_small btn_inventory")
    @FindBy(css = "button.btn.btn_primary.btn_small.btn_inventory")
    WebElement btnAdicionarNoCarrinho;

    public WebDriver driver;
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
    // fun��o para retornar o titulo escrito na guia do browser
    public String lerTituloAba(){
        return driver.getTitle();
    }
    public void clicarNoCarrinho(){
        imgCarrinho.click();    // Clica na imagem do carrinho
    }
    public String lerTituloDaPagina(){
        return lblTituloPagina.getText(); // retorna o que estiver escrito no elemento
    }
    // Esta fun��o � apenas um exemplo, ela n�o vai ser usado no exerc�cio
    public String lerTextoDoBotaoAdicionarRemoverDoCarrinho(){
        return btnAdicionarNoCarrinho.getText();
    }
    public void clicarNoBotaoAdicionarOuRemoverNoCarrinho(){
        btnAdicionarNoCarrinho.click();
    }
}