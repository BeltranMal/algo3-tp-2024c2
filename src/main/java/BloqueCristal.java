public class BloqueCristal extends Bloque implements interactuarConLaser {

    public BloqueCristal(Coordenada posicion) {
        super(new Piso(posicion) , "cristal");
    }


    @Override
    public void movimientoLaser() {
        // bifurca el laser

    }
}
