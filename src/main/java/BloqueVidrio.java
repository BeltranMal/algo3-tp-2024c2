public class BloqueVidrio extends Bloque implements interactuarConLaser {
    public BloqueVidrio(Coordenada posicion) {
        super(new Piso(posicion), "vidrio");
    }

    @Override
    public void movimientoLaser(Laser laser) {
        String direccion = laser.getDireccion();
        Laser laserReflajado = comportamiento1(direccion, laser);
        Laser laserIntacto = comportamiento2(direccion, laser);
        laserReflajado.moverLaser();
        laserIntacto.moverLaser();
    }

    public Laser comportamiento1(String direccion, Laser laser) {

        laser.setDireccion(laser.reflejarDireccion(direccion));

        Coordenada nuevaCoordenada = laser.cambioPosicion(this.getPosicion(), laser.getDireccion());
        laser.setPosicion(nuevaCoordenada);

        return laser;
    }

    public Laser comportamiento2(String direccion, Laser laser) {

        Coordenada nuevaPosicion = laser.cambioPosicion(this.getPosicion(), direccion);
        laser.setPosicion(nuevaPosicion);
        return laser;
    }

}

