import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

public class Tablero {

    private Elemento[][] grilla;
    private int filas;
    private int columnas;

    public Tablero(String nombreNivel) throws IOException {
        cargarNivel(nombreNivel);
    }

    private void cargarNivel(String nombreNivel) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        BufferedReader reader = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream("TP1 TB025 levels/" + nombreNivel + ".dat")));
        String linea;
        boolean segundaSeccion = false;

        while ((linea = reader.readLine()) != null) {
            if (linea.trim().isEmpty()) {
                segundaSeccion = true;
                //inmplementar posicion laser y objertivo

            }

            // implementar en la  grilla

        }

        reader.close();
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

    public void imprimirTablero(){
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Elemento elemento = grilla[i][j];
                if (elemento == null) {
                    System.out.print(" ");
                } else {
                    System.out.print(elemento);
                }
            }
            System.out.println();
        }
    }
}