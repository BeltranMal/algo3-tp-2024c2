public class BloqueOpacoMovil extends Bloque {
    public BloqueOpacoMovil(Coordenada posicion) {
        super(new Piso(posicion), "opacoMovil");
    }

    @Override
    public void movimientoLaser(Laser laser) {
        laser.setDireccion(laser.getDireccion());
    }
}
