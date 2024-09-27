import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Nivel {
    Tablero tablero;


    public Nivel(String nombreNivel) throws IOException {
        cargarNivel(nombreNivel);
    }




    private void cargarNivel(String nombreNivel) throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        BufferedReader reader = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream("TP1 TB025 levels/" + nombreNivel + ".dat")));
        String linea = reader.readLine();
        ArrayList<String> renglones = new ArrayList<>();

        int filas = 0;
        int columnas = linea.length();

        // Leer el archivo línea por línea
        while (!linea.trim().isEmpty()) { // no usar isEmpty(
            renglones.add(linea); // Almacenar la línea
            filas++; // Incrementar el contador de filas
            linea = reader.readLine(); // Leer la siguiente línea
        }

        this.tablero = new Tablero(filas, columnas);

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Coordenada posicion = new Coordenada(i, j);

                char caracter = renglones.get(i).charAt(j);
                Elemento elemento = interpretarCaracterParte1(caracter, posicion); // solo primera parte

                if (elemento != null) { // si no es una celda vacia
                    tablero.inicializarGrilla(elemento);
                }
                else{
                    System.out.print("Celda vacía");
                }
            }
        }

        tablero.imprimirTablero();


        // leer emisores y objetivos
        // leer segunda parte -> como y donde guardarlo
        // guardar en algun lado y mandarselo a la parte grafica -> en la mitad de un bloque -> no se puede ubicar en un tablero


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
