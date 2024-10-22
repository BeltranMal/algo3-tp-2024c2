import java.util.ArrayList;

public class Tablero {
    private final Elemento[][] grilla;

    public Tablero(int filas, int columnas) {
        this.grilla = new Elemento[filas * 2][columnas * 2];
    }

    private boolean posicionValida(Coordenada coordenada){
        int x = coordenada.x;
        int y = coordenada.y;
        return (x >= 0 && x < grilla.length && y >= 0 && y < grilla[0].length);
    }

    public Elemento obtenerElemento(Coordenada coordenada){
        if (posicionValida(coordenada)) {
            return grilla[coordenada.x][coordenada.y];
        }
        return null;
    }

    private boolean movimientoValido(Coordenada coordenada){

        if (!posicionValida(coordenada)) {
            return false;
        }

        Elemento elemento = obtenerElemento(coordenada);

        return (!elemento.puedeSerGolpeadoPorLaser());

    }
    
    public void agregarElemento(Elemento elemento, Coordenada coordenada){
        int x = coordenada.x;
        int y = coordenada.y;
        grilla[x][y] = elemento;
    }

    public void actualizarTablero(Coordenada coordenadaNueva, Bloque bloque) {

        if (movimientoValido(coordenadaNueva)) {

            Coordenada antiguaPosicion = bloque.moverBloque(coordenadaNueva);
            if (bloque.movible()) {
                Piso pisoNuevo = new Piso(antiguaPosicion);
                grilla[coordenadaNueva.x][coordenadaNueva.y] = bloque;
                agregarElemento(pisoNuevo, antiguaPosicion);
            }
        }
    }

    public int getFilas() {
        return grilla.length;
    }

    public int getColumnas() {
        return grilla[0].length;
    }

    public void generarTablero(ArrayList<Integer> cantidadColumnas, ArrayList<String> lineas, Nivel nivel) {
        Coordenada posicion;
        int columnas, filas = lineas.size();
        for (int i = 0; i < filas; i++) {
            columnas = cantidadColumnas.get(i);
            for (int j = 0; j < columnas; j++) {

                posicion = new Coordenada(i, j).ubicarImpar();
                char caracter = lineas.get(i).charAt(j);
                Elemento elemento = nivel.interpretarElementosGrilla(caracter, posicion);
                if (elemento != null) {
                    elemento.ubicarElemento(this);
                }

            }
        }
    }
}