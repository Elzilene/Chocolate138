package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryItemPage {
    //Elements
    @FindBy(css = "div.inventory_details_name large_size")
    WebElement  lblTituloProduto;
    @FindBy(css = "inventory_details_price")
    WebElement  lblPrecoDoProduto;
    @FindBy(css = "btn btn_primary btn_small btn_inventory")
    WebElement  btnAdicionarOuRemoverNoCarrinho;

    // Construtor
    public InventoryItemPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Funções e Métodos
    public String lerTituloDoProduto(){
        return lblTituloProduto.getText();
    }
    public String lerPrecoProduto(){
        return lblPrecoDoProduto.getText();
    }
    public String lerTextoDoBotaoAdicionarRemoverDoCarrinho(){
        return btnAdicionarOuRemoverNoCarrinho.getText();
    }
    public void clicarNoBotaoAdicionarOuRemoverNoCarrinho(){
        btnAdicionarOuRemoverNoCarrinho.click();
    }
}
