public abstract class Elemento {

    private Coordenada coordenada;

    public Elemento(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public Coordenada getPosicion() {
        return this.coordenada;
    }

    public void setPosicion(Coordenada posicion) {
        this.coordenada = posicion;
    }

    public abstract void ubicarElemento(Tablero tablero);

}