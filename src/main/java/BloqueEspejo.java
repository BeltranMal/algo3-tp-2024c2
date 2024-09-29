public class BloqueEspejo extends Bloque implements interactuarConLaser {

    public BloqueEspejo(Coordenada posicion) {
        super(new Piso(posicion), "espejo");
    }

    @Override
    public void movimientoLaser(Laser laser) {
        laser.setDireccion(laser.reflejarDireccion(laser.getDireccion()));
        Coordenada nuevaCoordenada = laser.cambioPosicion(this.getPosicion(), laser.getDireccion());
        laser.setPosicion(nuevaCoordenada);

        laser.moverLaser();

    }
}
