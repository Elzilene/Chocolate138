
// 1 - Pacote
package apitest;

import com.google.gson.Gson;
import entities.AccountEntity;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.ITestContext; // Interface do TestNG para compartilhar variaveis

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

// 2 - Bibliotecas
// 3 - Classe
public class TestAccount {
    // 3.1 - Atributos

   //Ate
   // oi
    String userId;
    String ct = "application/json"; // contentType da API
    String jsonBody; // guardar o json que ser� enviado
    String uri = "https://bookstore.toolsqa.com/Account/v1/"; // Endere�o Base
    Response resposta; // guardar o retorno da API
    static String token; // guardar o token - autentica��o do usu�rio

    // 3.1.2 Instanciar Classes Externas
    Gson gson = new Gson(); // Instancia o objeto de convers�o classe para json
    AccountEntity account = new AccountEntity(); // Instancia a entidade usuario
    // 3.2 - M�todos e Fun��es

    // M�todo #1 - Criar Usu�rio
    @Test(priority = 1)
    public void testCreateUser(ITestContext context){
        // Arrange - Configura

        account.userName = "charlie191"; // entrada e saida (resultado esperado)
        account.password = "P@ss0rd1"; // entrada

        jsonBody = gson.toJson(account);  // Converte a entidade usuario no formato json

        // Act - Executa

        // Dado - Quando - Ent�o
        // Given - When - Then
        resposta = (Response) given()      // dado
                .contentType(ct)    // tipo do conteudo
                .log().all()                        // registre tudo na ida
                .body(jsonBody)    // corpo da mensagem que ser� enviada
                .when() // quando
                .post(uri + "User")
                // Assert - Valida
                .then() // ent�o
                .log().all()        // registre tudo na volta
                .statusCode(201) // valide a comunica��o
                .body("username", is(account.userName)) // valida o usuario
                .extract()
        ; // fim da linha do REST-assured

        // extrair o userID (identifica��o do usu�rio)

        userId = resposta.jsonPath().getString("userID");
        context.setAttribute("userID", userId);
        System.out.println("UserID extraido: " + userId);


    } // fim do m�todo de cria��o de usu�rio

    @Test(priority = 2)
    public void testGenerateToken(ITestContext context){ // Declarar a Interface de Contexto
        // Configura
        // --> Dados de Entrada s�o fornecidos pela AccountEntity
        // --> Resultado Esperado � que ele receba um token

        // Executa
        resposta = (Response) given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
                .when()
                .post(uri + "GenerateToken")
                .then()
                .log().all()
                .statusCode(200) // valida a comunica��o
                .body("status", is("Success")) // Status = Sucesso
                .body("result", is("User authorized successfully."))
                .extract()
        ;

        // Extra��o do Token
        token = resposta.jsonPath().getString("token");
        context.setAttribute("token", token);
        System.out.println("token: " + token);

        // Valida
        Assert.assertTrue(token.length() != 0);

    } // fim do m�todo de gera��o de token de identifica��o do usu�rio

    @Test(priority = 3)
    public void testAuthorized(){
        // Configura
        // Dados de Entrada
        // --> Fornecidos pele AccountEntity atrav�s do m�todo testCreateUser - priority = 1

        // Dados de Sa�da / Resultado Esperado
        // StatusCode = 200
        // Response Body = true

        // Executa
        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
                .when()
                .post(uri + "Authorized")
                // Valida
                .then()
                .log().all()
                .statusCode(200)
        // .body(true) // ToDo: como validar o retorno do body apenas como true
        ;

    }

    @Test(priority = 4)
    public void testResearchUserNotAuthorized(){
        // Configura
        // Dados de Entrada
        // userId foi extraido no m�todo testCreateUser e est� na mem�ria
        // Dados de Sa�da / Resultado Esperado
        // Status Code = 401, Code = 1200 e Message = User not authorized!

        // Executa
        given()                                     // Dado // Comandos do REST-assured
                .contentType(ct)                    // Formato da mensagem
                .log().all()                        // Exibir tudo que acontece na ida
                .when()                                     // Quando
                .get(uri + "User/" + userId)   // Consulta o usu�rio pelo userId
                // Valida
                .then()                                     // Ent�o
                .log().all()                        // Exibir tudo que acontece na volta
                .statusCode(401)     // Valida se n�o est� autorizado
                .body("code", is("1200")) // Valida o c�digo de mensagem "n�o autorizado"
                .body("message", is("User not authorized!"))
        ;                                           // Conclui o bloco do REST-assured
    }

    @Test(priority = 5)
    public void testResearchUser(){
        // Configura
        // Dados de Entrada
        // userId foi extraido no m�todo testCreateUser e est� na mem�ria
        // Dados de Sa�da / Resultado Esperado
        // userName vir� da classe Account e o status code deve ser 200

        // Executa
        given()                                     // Dado // Comandos do REST-assured
                .contentType(ct)                    // Formato da mensagem
                .log().all()                        // Exibir tudo que acontece na ida
                .header("Authorization", "Bearer " + token)
                .when()                                     // Quando
                .get(uri + "User/" + userId)   // Consulta o usu�rio pelo userId
                // Valida
                .then()                                     // Ent�o
                .log().all()                        // Exibir tudo que acontece na volta
                .statusCode(200)     // Valida se a conex�o teve sucesso
                .body("userId", is(userId))
                .body("username", is(account.userName)) // Valida o nome do usu�rio
        ;                                           // Conclui o bloco do REST-assured
    }
    @Test(priority = 20)
    public void testDeleteUser(){
        // Configura
        // Dados de entrada vem do m�todo de teste da cria��o do usu�rio (userId)
        // Resultado esperado � o c�digo e mensagem de sucesso na exclus�o do usu�rio

        // Executa
        given()
                .log().all()
                .contentType(ct)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete(uri + "User/" + userId)
                // Valida
                .then()
                .log().all()
                .statusCode(204)
        ;

    }

}