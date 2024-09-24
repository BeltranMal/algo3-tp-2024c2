public class BloqueOpacoMovil extends Bloque implements Laser{
    public BloqueOpacoMovil(Coordenada posicion) {
        super(posicion, "opacoMovil");
    }

    @Override
    public void moverBloque(Coordenada posicion) {
        // mover bloque

    }

    @Override
    public void interacccionLaser() {
        // absorbe el laser

    }
}
