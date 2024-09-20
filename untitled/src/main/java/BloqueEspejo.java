public class BloqueEspejo extends Bloque implements Laser{

    public BloqueEspejo(Coordenada posicion, String tipo) {
        super(posicion, tipo);
    }

    @Override
    public void moverBloque(Coordenada posicion) {
        // mover bloque

    }

    @Override
    public void interacccionLaser() {
        // refleja el laser

    }
}
