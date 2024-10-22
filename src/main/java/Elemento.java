public abstract class Elemento  {

    private Coordenada coordenada;

    public Elemento(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public Coordenada getPosicion() {
        return coordenada;
    }

    public void setPosicion(Coordenada posicion) {
        coordenada = posicion;
    }

    public  void ubicarElemento(Tablero tablero){
        tablero.agregarElemento(this, this.getPosicion());
    }

    public  boolean puedeSerGolpeadoPorLaser(){
        return true;
    }

    public  boolean movible(){
        return false;
    };

}