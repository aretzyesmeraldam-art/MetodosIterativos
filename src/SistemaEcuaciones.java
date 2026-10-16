public class SistemaEcuaciones {
    // Matriz de coeficientes A
    private double[][] A = {
            {5, 1, 1},
            {1, 4, 1},
            {1, 2, 5}
    };

    // Vector de términos independientes b
    private double[] b = {7, 6, 8};

    // Vector de aproximación inicial X0 = [0, 0, 0]
    private double[] xInicial = {0, 0, 0};

    // Tolerancia de error epsilon = 0.0001
    private double tolerancia = 0.0001;

    // Getters para acceder a los datos desde los demás módulos
    public double[][] getA() { return A; }
    public double[] getB() { return b; }
    public double[] getXInicial() { return xInicial; }
    public double getTolerancia() { return tolerancia; }
    public int getN() { return b.length; }
}