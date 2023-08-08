package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage extends BasePage {
    // Mapeamemento

    //@FindBy(id ="item_4_title_link")
    WebElement lnkTituloProduto;


    //Construtor
    public InventoryPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
    // Funções e Métodos
    public String lerTituloDaPagina(){
        return lblTituloPagina.getText();   //
    }
    public void clicarNoTituloDaPagina(String productId){

    }
}
