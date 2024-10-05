public class BloqueOpacoFijo extends Bloque {

    public BloqueOpacoFijo(Piso piso) {
        super(piso,"opacoFijo");
    }

    @Override
    public void movimientoLaser(Laser laser) {
        laser.setDireccion(laser.getDireccion());
    }

}
