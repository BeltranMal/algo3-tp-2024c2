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
        int columnas;
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
                Coordenada posicion = new Coordenada(i, j);
                char caracter = renglones.get(i).charAt(j);
                Elemento elemento = interpretarCaracterParte1(caracter, posicion); // solo primera parte
                tablero.inicializarGrilla(elemento);

            }
        }
        tablero.imprimirTablero();

        linea = reader.readLine();

        int x;
        int y;
        while (linea != null) {

            if(linea.startsWith("E")){


                String[] partes = linea.split(" ");
                x = Integer.parseInt(partes[1]);
                y = Integer.parseInt(partes[2]);
                Coordenada posicion = new Coordenada(x, y);
                String direccion = partes[3];
                new EmisorLaser(posicion, direccion);

            }
            else if (linea.startsWith("G")) {
                String[] partes = linea.split(" ");
                x = Integer.parseInt(partes[1]);
                y = Integer.parseInt(partes[2]);
                Coordenada posicion = new Coordenada(x, y);
                new Objetivo(posicion);
            }
            // Leer la siguiente línea
            linea = reader.readLine();
        }

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
