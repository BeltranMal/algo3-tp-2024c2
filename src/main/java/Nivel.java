import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Nivel {
    Tablero tablero;


    public Nivel(String nombreNivel) throws IOException {
        cargarNivel(nombreNivel);
    }

    private void cargarNivel(String nombreNivel) throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        BufferedReader reader = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream("TP1 TB025 levels/" + nombreNivel + ".dat")));
        String linea = null;

        linea = reader.readLine();

       int filas = linea.length(); // como sacar las filas ????
        int columnas = linea.length();
        tablero = new Tablero(filas, columnas);

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Coordenada posicion = new Coordenada(i, j);
                Elemento elemento = interpretarCaracterParte1(linea.charAt(j), posicion); // solo primera parte
                if (elemento != null) {
                    tablero.agregarElemento(elemento);
                }
            }
            linea = reader.readLine();
        }
        tablero.imprimirTablero();


    }

    private Elemento interpretarCaracterParte1(char elemento, Coordenada posicion) {
        return switch (elemento) {
            case '.' -> new Piso(posicion);
            case 'F' -> new BloqueOpacoFijo(posicion);
            case 'B' -> new BloqueOpacoMovil(posicion);
            case 'R' -> new BloqueEspejo(posicion);
            case 'G' -> new BloqueVidrio(posicion);
            case 'C' -> new BloqueCristal(posicion);
            default -> null; // Celda vacía
        };
    }
}
