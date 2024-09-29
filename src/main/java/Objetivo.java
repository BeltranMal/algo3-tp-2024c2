public class Objetivo implements interactuarConLaser {
    private final Coordenada posicion;

    public Objetivo(Coordenada posicion) {
        this.posicion = posicion;
    }

    public Coordenada getPosicion() {
        return posicion;
    }

    @Override
    public void movimientoLaser() {
        // se destruye
    }
}
