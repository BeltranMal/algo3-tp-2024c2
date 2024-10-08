public class BloqueVidrio extends Bloque {
    public BloqueVidrio(Piso piso) {
        super(piso, "vidrio");
    }

    @Override
    public void movimientoLaser(Laser laser) {
        String direccion = laser.getDireccion().getLast();
        Laser laserReflajado = espejar(direccion, laser);
        Laser laserIntacto = continuar(direccion, laser);
        laserReflajado.moverLaser();
        laserIntacto.moverLaser();
    }

    public Laser espejar(String direccion, Laser laser) {

        laser.setDireccion(laser.reflejarDireccion(direccion, this.getPosicion()));
        Coordenada nuevaCoordenada = laser.cambioPosicion(this.getPosicion(), laser.getDireccion().getLast());
        laser.setPosicion(nuevaCoordenada.convertirCoordenada());

        return laser;
    }

    public Laser continuar(String direccion, Laser laser) {

        Coordenada nuevaPosicion = laser.cambioPosicion(this.getPosicion(), direccion);
        laser.setPosicion(nuevaPosicion.convertirCoordenada());
        laser.setDireccion(direccion);
        return laser;
    }

}

