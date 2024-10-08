public class BloqueCristal extends Bloque {

    public BloqueCristal(Piso piso) {
        super(piso , "cristal");
    }

    @Override
    public void movimientoLaser(Laser laser) {

        Coordenada nuevaPosicion = laser.cambioPosicion(this.getPosicion(), "H");
        Coordenada posicionSalida = laser.cambioPosicion(nuevaPosicion, laser.getDireccion().getLast());

        laser.setPosicion(posicionSalida.convertirCoordenada());
        laser.setDireccion(laser.getDireccion().getLast());

    }
}
