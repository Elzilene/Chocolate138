package chatGPT;
public class CalculadoraGPT {
    // M�todo para adi��o
    public static double adicao(double a, double b) {
        return a + b;
    }

    // M�todo para subtra��o
    public static double subtracao(double a, double b) {
        return a - b;
    }

    // M�todo para multiplica��o
    public static double multiplicacao(double a, double b) {
        return a * b;
    }

    // M�todo para divis�o
    public static double divisao(double a, double b) {
        if(b != 0) {
            return a / b;
        } else {
            System.out.println("Erro: divis�o por zero n�o � permitida.");
            return Double.NaN;
        }
    }
}
