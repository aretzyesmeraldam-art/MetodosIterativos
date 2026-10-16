public class Main {
    public static void main(String[] args) {
        // Instancia del Módulo 1
        SistemaEcuaciones sistema = new SistemaEcuaciones();

        // Ejecución de Módulo 2 mediante el Módulo 3
        MetodosIterativos.resolverJacobi(sistema);
        MetodosIterativos.resolverGaussSeidel(sistema);
    }
}
