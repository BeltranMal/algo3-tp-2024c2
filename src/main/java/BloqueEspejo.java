public class BloqueEspejo extends Bloque {

    public BloqueEspejo(Coordenada posicion) {
        super(posicion);
    }

    @Override
    public void interaccionLaser(Laser laser) {
        laser.reflejarMovimiento( this.getPosicion());
    }
}
