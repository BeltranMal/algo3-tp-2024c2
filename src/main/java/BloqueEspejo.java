public class BloqueEspejo extends Bloque implements Laser{

    public BloqueEspejo(Coordenada posicion) {
        super(posicion, "espejo");
    }



    @Override
    public void interacccionLaser() {
        // refleja el laser

    }
}
