package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

    // Mapeamento cria ou reflete uma DSL
    //Domain Structured language
    //Linguagem estruturada de domingo
    //" Como falamos aqui na empresa"


    // Elementos Web /  Web elements ............> os elementos serão mapeados
    @FindBy(id = "user-name")  // Mapeia pelo seletor
            WebElement txtUsuario;

    @FindBy(id = "login-button") //
    WebElement txtLogin;

    // Construtor / Construct
    public HomePage(WebDriver driver)

    // Funçõe e Métodos

    public void logar(String user, String password){
        txtUsuario.sendKeys(user);
        txtLogin.sendKeys(password);
        btnLogin.click();

    }
    public void clicarnoBotaoLogin(){
        btnLogin.click();
    }
}
