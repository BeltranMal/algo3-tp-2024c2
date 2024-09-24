import java.util.ArrayList;

public class Coordenada {
    private int x;
    private int y;


    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public ArrayList<Integer> getCoordenada() {
        ArrayList<Integer> listaPosicion = new ArrayList<Integer>();
        listaPosicion.add(this.x);
        listaPosicion.add(this.y);
        return listaPosicion;

    }



}
