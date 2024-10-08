import java.util.ArrayList;

public class Tablero {

    private final Elemento[][] grilla;


    public Tablero(int filas, int columnas) {
        this.grilla = new Elemento[filas * 2][columnas * 2];
    }

    public boolean posicionValida(Coordenada coordenada){
        int x = coordenada.x;
        int y = coordenada.y;
        return (x >= 0 && x < grilla.length && y >= 0 && y < grilla[0].length);
    }

    public Elemento obtenerElemento(Coordenada coordenada){

        Coordenada ubicacion = new Coordenada(coordenada.x, coordenada.y).ubicarImpar();
        int x = ubicacion.x;
        int y = ubicacion.y;

        if (posicionValida(ubicacion)) {
            return grilla[x][y];
        }
        return null;
    }

    public boolean movimientoValido(Coordenada coordenada){
        Elemento elemento = obtenerElemento(coordenada);
        if (posicionValida(elemento.getPosicion())) {

            return (elemento instanceof Piso && ((Piso) elemento).estaVacio());
        }
        return false;
    }


    public void agregarElemento(Elemento elemento, Coordenada coordenada){
        int x = coordenada.x;
        int y = coordenada.y;
        grilla[x][y] = elemento;
    }

    public void actualizarTablero(Coordenada coordenadaNueva, Bloque bloque) {

        Coordenada posicion;
        if (movimientoValido(coordenadaNueva)) {

            Coordenada antiguaPosicion = bloque.moverBloque(coordenadaNueva);

            if (!antiguaPosicion.equals(new Coordenada(-1, -1))) {
                Piso piso = (Piso) obtenerElemento(coordenadaNueva);
                piso.colocarBloque(bloque);

                posicion = coordenadaNueva.ubicarImpar();

                grilla[posicion.x][posicion.y] = bloque;

                Piso pisoNuevo = new Piso(bloque.getPosicion());
                pisoNuevo.removerBloque();
                agregarElemento(pisoNuevo, antiguaPosicion);
            }
        }
    }



    public void inicializarElemento(Elemento elemento) {
        if (elemento != null) {
            elemento.ubicarElemento(this);
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
        int columnas = 0, filas = lineas.size();
        for (int i = 0; i < filas; i++) {
            columnas = cantidadColumnas.get(i);
            for (int j = 0; j < columnas; j++) {

                posicion = new Coordenada(i, j).ubicarImpar();

                char caracter = lineas.get(i).charAt(j);
                Elemento elemento = nivel.interpretarElementosGrilla(caracter, posicion); // solo primera parte

                inicializarElemento(elemento);

            }
        }
    }
}