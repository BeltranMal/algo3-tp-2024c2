public class Objetivo extends Elemento implements interactuarConLaser {

    public Objetivo(Coordenada posicion) {
        super(posicion);
    }

    @Override
    public void ubicarElemento(Tablero tablero) {
        tablero.agregarElemento(this, this.getPosicion());
    }


    @Override
    public void movimientoLaser(Laser laser) {
        laser.setPosicion(getPosicion());
    }
}
