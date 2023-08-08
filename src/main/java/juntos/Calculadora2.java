package juntos;

public class Calculadora2 {

    public static double somar(double num1, double num2){
        double soma = num1 + num2;
        System.out.println("Soma de " + num1 + " + " + num2 + " = " + soma);
        return soma;
    }

    public static double subtrair(double num1, double num2){
        double resultado = num1 - num2;
        System.out.println("Subtra��o de " + num1 + " - " + num2 + " = " + resultado);
        return resultado;
    }

    public static double multiplicar(double num1, double num2){
        double resultado = num1 * num2;
        System.out.println("Multiplica��o de " + num1 + " * " + num2 + " = " + resultado);
        return resultado;
    }

    public static double dividir(double num1, double num2){
        if(num2 == 0.0) {
            System.out.println("Erro: divis�o por zero n�o � permitida.");
            return Double.NaN; // Retorne NaN para indicar uma opera��o inv�lida
        }
        double resultado = num1 / num2;
        System.out.println("Divis�o de " + num1 + " / " + num2 + " = " + resultado);
        return resultado;
    }

}
