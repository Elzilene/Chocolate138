package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends  {
    // Elements
    @FindBy(css = "inventory_item_name")
    WebElement lblTituloProduto;



    @FindBy(css = "div.inventory_item_price")
    WebElement lblPrecoProduto;

    @FindBy(css = "cart_quantity")
    WebElement lblQuantidade;


    @FindBy(id = "remove-sauce-labs-bike-light")
    WebElement btnRemover;
    //Constutor
    public CartPage(WebDriver driver){
        super(driver);
        PageFactory.initElements((driver, this));
    }

    //Funções e Métodos
    public String lerTituloProdutoNoCarrinho(){
        return lblPrecoProduto.getText();
    }
    public String lerQuantidaddeDoProduto(){
        return  lblQuantidade.getText();
    }
    //Constutor
}
