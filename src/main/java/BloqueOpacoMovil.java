public class BloqueOpacoMovil extends Bloque {
    public BloqueOpacoMovil(Piso piso) {
        super(piso, "opacoMovil");
    }

    @Override
    public void movimientoLaser(Laser laser) {
        laser.setDireccion(laser.getDireccion());
    }
}
