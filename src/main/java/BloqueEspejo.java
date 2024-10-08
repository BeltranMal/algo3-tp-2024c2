public class BloqueEspejo extends Bloque {

    public BloqueEspejo(Piso piso) {
        super(piso, "espejo");
    }

    @Override
    public void movimientoLaser(Laser laser) {
        laser.setDireccion(laser.reflejarDireccion(laser.getDireccion().getLast(), this.getPosicion()));
        Coordenada nuevaCoordenada = laser.cambioPosicion(this.getPosicion(), laser.getDireccion().getLast());

        laser.setPosicion(nuevaCoordenada);
    }
}
