public class BloqueVidrio extends Bloque {
    public BloqueVidrio(Coordenada posicion) {
        super(posicion);
    }

    @Override
    public void interaccionLaser(Laser laser) {

        Direccion direccion = laser.getDireccion();
        Laser continuo = new Laser(laser.getPosicion(),direccion,laser.getNivel());
        continuo.agregarANivel();
        Coordenada nuevaPosicion = direccion.PosBloque(this.getPosicion(),!continuo.golpeHorizontal(this.getPosicion()));

       continuo.setPosicion(nuevaPosicion);

        laser.reflejarMovimiento(this.getPosicion());
    }
}

