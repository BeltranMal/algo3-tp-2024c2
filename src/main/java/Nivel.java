import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Nivel {
    Tablero tablero;
    ArrayList<Elemento> emisoryObjetivo = new ArrayList<>();
    ArrayList<Laser> laseresActivos = new ArrayList<>();

    public Nivel(String nombreNivel) throws IOException {
        cargarNivel(nombreNivel);
    }

    public void cargarNivel(String nombreNivel) throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();
        BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(classLoader.getResourceAsStream("TP1 TB025 levels/" + nombreNivel + ".dat"))));
        String linea = reader.readLine();

        ArrayList<String> renglones = new ArrayList<>();
        ArrayList<Integer> cantidadColumnas = new ArrayList<>();
        cantidadColumnas.add(linea.length());

        while (!linea.trim().isEmpty()) {
            renglones.add(linea);

            linea = reader.readLine();
            cantidadColumnas.add(linea.length());
        }

        int filas = renglones.size();
        this.tablero = new Tablero(filas, Collections.max(cantidadColumnas));
        tablero.generarTablero(cantidadColumnas, renglones, this);
        linea = reader.readLine();

        while (linea != null) {
            String[] partes = linea.split(" ");
            Elemento elemento = interpretarElementosEspeciales(partes);
            emisoryObjetivo.add(elemento);
            linea = reader.readLine();
        }
    }

    public ArrayList<Elemento> elementosEspeciales() {
        return emisoryObjetivo;
    }

    private Elemento interpretarElementosEspeciales(String[] partes) {
        int y = (Integer.parseInt(partes[1]));
        int x = (Integer.parseInt(partes[2]));
        Coordenada posicion = new Coordenada(x, y);


        return switch (partes[0]) {
            case "E" -> new EmisorLaser(posicion, Direccion.valueOf(partes[3]));
            case "G" -> new Objetivo(posicion);
            default -> null;
        };
    }

    public Elemento interpretarElementosGrilla(char elemento, Coordenada posicion) {
        return switch (elemento) {
            case '.' -> new Piso(posicion);
            case 'F' -> new BloqueOpacoFijo(posicion);
            case 'B' -> new BloqueOpacoMovil(posicion);
            case 'R' -> new BloqueEspejo(posicion);
            case 'G' -> new BloqueVidrio(posicion);
            case 'C' -> new BloqueCristal(posicion);
            default -> null;
        };
    }

    public Tablero devolverTablero() {
        return tablero;
    }

    public boolean juegoGanado() {
        for (Elemento e : emisoryObjetivo) {
            if (e.puedeSerGolpeadoPorLaser()) {
                Objetivo objetivo = (Objetivo) e;
                if (!objetivo.alcanzado()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void agregarLaser(Laser laser) {
        laseresActivos.add(laser);
    }

    public ArrayList<Laser> getLaseresActivos() {
        return laseresActivos;
    }

    public void limpiarLaseres() {
        laseresActivos.clear();
    }

    public void objetivosAlcanzados(ArrayList<Coordenada> ruta) {

        for (Elemento elemento : emisoryObjetivo) {
            if (elemento.puedeSerGolpeadoPorLaser()) {
                for (Coordenada coordenada : ruta) {
                    if (elemento.getPosicion().x == coordenada.x && elemento.getPosicion().y == coordenada.y) {

                        Objetivo objetivo = (Objetivo) elemento;
                        objetivo.setCompleto(true);
                    }
                }
            }
        }
    }
}

