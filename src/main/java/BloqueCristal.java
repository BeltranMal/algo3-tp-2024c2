public class BloqueCristal extends Bloque {

    public BloqueCristal(Piso piso) {
        super(piso , "cristal");
    }

    @Override
    public void movimientoLaser(Laser laser) {
        // Bloque de cristal: Al ser alcanzado por un rayo, el rayo se refracta, continuando en línea recta y saliendo por el extremo opuesto del bloque, con la misma dirección de origen.
        Coordenada nuevaPosicion = laser.cambioPosicion(this.getPosicion(), "H");
        Coordenada posicionSalida = laser.cambioPosicion(nuevaPosicion, laser.getDireccion().getLast());
        laser.setPosicion(posicionSalida);
        laser.setDireccion(laser.getDireccion().getLast());
        laser.moverLaser();
    }
}
