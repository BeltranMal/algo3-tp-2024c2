import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

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
        ArrayList<Integer> cantidadColumnas = new ArrayList<>();

        int filas = 0;
        int columnas, centro_x, centro_y;
        cantidadColumnas.add(linea.length());

        // Leer el archivo línea por línea
        while (!linea.trim().isEmpty()) {
            renglones.add(linea); // Almacenar la línea
            filas++; // Incrementar el contador de filas
            linea = reader.readLine(); // Leer la siguiente línea
            cantidadColumnas.add(linea.length());
        }

        this.tablero = new Tablero(filas, Collections.max(cantidadColumnas));
        for (int i = 0; i < filas; i++) {
            columnas = cantidadColumnas.get(i);
            for (int j = 0; j < columnas; j++) {

                centro_x = (i + 1) * 2 - 1;
                centro_y = (j + 1) * 2 - 1;
                Coordenada posicion = new Coordenada(centro_x, centro_y);
                char caracter = renglones.get(i).charAt(j);
                Elemento elemento = interpretarCaracterParte1(caracter, posicion); // solo primera parte
                tablero.inicializarGrilla(elemento);
            }
        }
        linea = reader.readLine();

        while (linea != null) {
            String[] partes = linea.split(" ");
            Elemento elemento = interpretarParte2(partes);
            tablero.inicializarGrilla(elemento);
            linea = reader.readLine();
        }
        tablero.imprimirTablero();
    }



    private Elemento interpretarParte2(String[] partes) {
        int y = Integer.parseInt(partes[1]);
        int x = Integer.parseInt(partes[2]);
        Coordenada posicion = new Coordenada(x, y);
        return switch (partes[0]) {
            case "E" -> new EmisorLaser(posicion, partes[3]);
            case "G" -> new Objetivo(posicion);
            default -> null;
        };

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
