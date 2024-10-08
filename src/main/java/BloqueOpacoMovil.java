public class BloqueOpacoMovil extends Bloque {
    public BloqueOpacoMovil(Piso piso) {
        super(piso, "opacoMovil");
    }

    @Override
    public void movimientoLaser(Laser laser) {
        laser.setPosicion(new Coordenada(-1,-1));
        laser.setDireccion(laser.getDireccion().getLast());
    }
}
