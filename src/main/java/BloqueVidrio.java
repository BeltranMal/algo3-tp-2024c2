public class BloqueVidrio extends Bloque {
    public BloqueVidrio(Piso piso) {
        super(piso, "vidrio");
    }

    @Override
    public void movimientoLaser(Laser laser) {
        String direccion = laser.getDireccion().getLast();
        Laser laserReflajado = comportamiento1(direccion, laser);
        Laser laserIntacto = comportamiento2(direccion, laser);
        laserReflajado.moverLaser();
        laserIntacto.moverLaser();
    }

    public Laser comportamiento1(String direccion, Laser laser) {

        laser.setDireccion(laser.reflejarDireccion(direccion, this.getPosicion()));

        Coordenada nuevaCoordenada = laser.cambioPosicion(this.getPosicion(), laser.getDireccion().getLast());
        laser.setPosicion(nuevaCoordenada);

        return laser;
    }

    public Laser comportamiento2(String direccion, Laser laser) {

        Coordenada nuevaPosicion = laser.cambioPosicion(this.getPosicion(), direccion);
        laser.setPosicion(nuevaPosicion);
        laser.setDireccion(direccion);
        return laser;
    }

}

