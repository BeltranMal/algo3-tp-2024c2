public class BloqueVidrio extends Bloque implements interactuarConLaser {
    public BloqueVidrio(Coordenada posicion) {
        super(new Piso(posicion), "vidrio");
    }

    @Override
    public void movimientoLaser() {
        // refleja el lasxzxzxzxzx
    }
}
