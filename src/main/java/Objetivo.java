public class Objetivo extends Elemento {
    private boolean completo = false;

    public Objetivo(Coordenada posicion) {
        super(posicion);
    }

    @Override
    public void ubicarElemento(Tablero tablero) {
        tablero.agregarElemento(this, this.getPosicion());
    }

    public boolean Alcanzado() {
        return completo;
    }

    public void setCompleto() {

        this.completo = true;
    }

}
