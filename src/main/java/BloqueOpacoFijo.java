public class BloqueOpacoFijo extends Bloque {

    public BloqueOpacoFijo(Coordenada posicion) {
        super(posicion);
    }

    @Override
    public void interaccionLaser(Laser laser) {
      laser.setPosicion(new Coordenada(-1,-1));

    }

    @Override
    public boolean movible() {
        return false;
    }

}
