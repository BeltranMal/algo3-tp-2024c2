public class BloqueOpacoFijo extends Bloque {

    public BloqueOpacoFijo(Piso piso) {
        super(piso,"opacoFijo");
    }

    @Override
    public void movimientoLaser(Laser laser) {
        laser.setPosicion(new Coordenada(-1,-1));
        laser.setDireccion(laser.getDireccion().getLast());
    }

}
