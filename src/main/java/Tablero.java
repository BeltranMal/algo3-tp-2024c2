import java.io.IOException;
import java.util.ArrayList;


public class Tablero {

    private Elemento[][] grilla;
    private int filas;
    private int columnas;

    public Tablero(int filas, int columnas) throws IOException {

        this.grilla = new Elemento[filas][columnas];
        this.filas = filas;
        this.columnas = columnas;
    }


    public void agregarElemento(Elemento elemento) {
        Coordenada posicion = elemento.getPosicion();

        ArrayList<Integer> listaPosicion = posicion.getCoordenada();
        int x = listaPosicion.get(0);
        int y = listaPosicion.get(1);
        grilla[x][y] = elemento;
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