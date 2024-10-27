public class Objetivo extends Elemento {
    private boolean completo = false;

    public Objetivo(Coordenada posicion) {
        super(posicion);
    }

    @Override
    public void ubicarElemento(Tablero tablero) {
        // no va en el tablero
    }

    public boolean alcanzado() {
        return completo;
    }

    public void setCompleto(boolean alcanzado) {
        completo = alcanzado;
    }
}
