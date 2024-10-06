public class BloqueOpacoMovil extends Bloque {
    public BloqueOpacoMovil(Piso piso) {
        super(piso, "opacoMovil");
    }

    @Override
    public void movimientoLaser(Laser laser) {
        laser.setPosicion(this.getPosicion());
        laser.setDireccion(laser.getDireccion().getLast());
    }
}
