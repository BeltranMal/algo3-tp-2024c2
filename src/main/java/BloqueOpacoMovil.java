public class BloqueOpacoMovil extends Bloque {
    public BloqueOpacoMovil(Coordenada posicion) {
        super(posicion);
    }

    @Override
    public void interaccionLaser(Laser laser) {
        laser.setPosicion(new Coordenada(-1,-1));
    }
}
