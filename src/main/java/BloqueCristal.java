public class BloqueCristal extends Bloque {

    public BloqueCristal(Coordenada posicion) {
        super(posicion);
    }

    @Override
    public void interaccionLaser(Laser laser) {
        Direccion direccionActual = laser.getDireccion();

        boolean horizontal = laser.golpeHorizontal(this.getPosicion());
        Coordenada posicionSalida = direccionActual.PosBloque(this.getPosicion(), horizontal);

        laser.setPosicion(posicionSalida);
    }
}