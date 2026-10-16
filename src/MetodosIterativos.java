import java.util.Arrays;

public class MetodosIterativos {

    // Metodo auxiliar para calcular la norma del error relativo máximo
    private static double calcularError(double[] xNuevo, double[] xViejo) {
        double maxError = 0.0;
        for (int i = 0; i < xNuevo.length; i++) {
            double error = Math.abs(xNuevo[i] - xViejo[i]);
            if (error > maxError) {
                maxError = error;
            }
        }
        return maxError;
    }

    // Metodo de Jacobi
    public static void resolverJacobi(SistemaEcuaciones sistema) {
        double[][] A = sistema.getA();
        double[] b = sistema.getB();
        double[] xViejo = Arrays.copyOf(sistema.getXInicial(), sistema.getN());
        double[] xNuevo = new double[sistema.getN()]; // Vector auxiliar para nuevos valores
        double tol = sistema.getTolerancia();
        int n = sistema.getN();
        int iteracion = 0;

        System.out.println("=== MÉTODO DE JACOBI ===");
        System.out.printf("%-10s %-12s %-12s %-12s %-12s\n", "Iteración", "x", "y", "z", "Error");
        System.out.printf("%-10d %-12.6f %-12.6f %-12.6f %-12s\n", iteracion, xViejo[0], xViejo[1], xViejo[2], "-");

        while (true) {
            iteracion++;
            for (int i = 0; i < n; i++) {
                double suma = 0;
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        suma += A[i][j] * xViejo[j];
                    }
                }
                xNuevo[i] = (b[i] - suma) / A[i][i];
            }

            double error = calcularError(xNuevo, xViejo);
            System.out.printf("%-10d %-12.6f %-12.6f %-12.6f %-12.6f\n", iteracion, xNuevo[0], xNuevo[1], xNuevo[2], error);

            if (error < tol) {
                break;
            }

            // Actualizar vector para la siguiente iteración
            System.arraycopy(xNuevo, 0, xViejo, 0, n);
        }
        System.out.println("Convergencia alcanzada en " + iteracion + " iteraciones.\n");
    }

    // Método de Gauss-Seidel
    public static void resolverGaussSeidel(SistemaEcuaciones sistema) {
        double[][] A = sistema.getA();
        double[] b = sistema.getB();
        double[] x = Arrays.copyOf(sistema.getXInicial(), sistema.getN()); // Actualización inmediata
        double tol = sistema.getTolerancia();
        int n = sistema.getN();
        int iteracion = 0;

        System.out.println("=== MÉTODO DE GAUSS-SEIDEL ===");
        System.out.printf("%-10s %-12s %-12s %-12s %-12s\n", "Iteración", "x", "y", "z", "Error");
        System.out.printf("%-10d %-12.6f %-12.6f %-12.6f %-12s\n", iteracion, x[0], x[1], x[2], "-");

        while (true) {
            iteracion++;
            double errorMax = 0.0;

            for (int i = 0; i < n; i++) {
                double suma = 0;
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        suma += A[i][j] * x[j]; // Usa los valores recién calculados en la iteración actual
                    }
                }
                double xAnterior = x[i];
                x[i] = (b[i] - suma) / A[i][i]; // Actualización inmediata

                double errorActual = Math.abs(x[i] - xAnterior);
                if (errorActual > errorMax) {
                    errorMax = errorActual;
                }
            }

            System.out.printf("%-10d %-12.6f %-12.6f %-12.6f %-12.6f\n", iteracion, x[0], x[1], x[2], errorMax);

            if (errorMax < tol) {
                break;
            }
        }
        System.out.println("Convergencia alcanzada en " + iteracion + " iteraciones.\n");
    }
}