package chatGPT;

public class TesteCalculadora {
    public static void main(String[] args) {
        double a = 20;
        double b = 10;

        System.out.println("Adi��o: " + CalculadoraGPT.adicao(a, b));
        System.out.println("Subtra��o: " + CalculadoraGPT.subtracao(a, b));
        System.out.println("Multiplica��o: " + CalculadoraGPT.multiplicacao(a, b));
        System.out.println("Divis�o: " + CalculadoraGPT.divisao(a, b));
    }
}

